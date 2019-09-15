package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.DatabaseController;
import ee.sda.mckirill.entities.MenuItem;
import ee.sda.mckirill.entities.Order;
import ee.sda.mckirill.entities.OrderedMenuItem;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.MenuItemsTypeEnum;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.MenuStrings;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class MenuUIController extends AbstractUIController {
    private DatabaseController databaseController = DatabaseController.of();

    public MenuUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        Map<Integer, Consumer> menuActions = new HashMap<>();
        menuActions.put(1, T -> System.out.println(BaseString.TODO)); //TODO
        menuActions.put(2, T -> editMenu(new MenuItem()));
        menuActions.put(3, T -> System.out.println(BaseString.TODO)); //TODO
        menuActions.put(4, T -> System.out.println(BaseString.TODO)); //TODO
    }

    private void editMenu(MenuItem menuItem) {
        System.out.println(MenuStrings.MENU_ADD_NEW);
        menuItem.setName(selectString(MenuStrings.MENU_SET_NAME, MenuStrings.MENU_EMPTY_NAME, 50));
        menuItem.setType(selectEnum(MenuStrings.MENU_SET_TYPE, MenuStrings.MENU_WRONG_TYPE, MenuItemsTypeEnum.class));
        menuItem.setPrice(selectBigDecimal(MenuStrings.MENU_SET_PRICE, MenuStrings.MENU_PRICE_0_LOW));
        databaseController.save(menuItem);
        System.out.println(BaseString.SAVE_IN_DB);
    }

    private void showAllMenuItems() {
        List<MenuItem> menuItems = databaseController.getListOfMenuItems();
        System.out.printf("%10s%45s%20s%20s%n",
                MenuStrings.TABLE_ID, MenuStrings.TABLE_MENU_ITEM_NAME, MenuStrings.TABLE_MENU_ITEM_PRICE, MenuStrings.TABLE_MENU_ITEM_TYPE);
        for (MenuItem menuItem : menuItems) {
            System.out.printf("%4d%30s%10s%10s%n",
                    menuItem.getId(), menuItem.getName(), menuItem.getPrice().toString(), menuItem.getType());
        }
    }

    public void addAdditionalFood(Order order) {
        showAllMenuItems();
        Function<Integer, Optional<MenuItem>> getMenuItemFunction = T -> databaseController.findById(MenuItem.class, T);
        MenuItem menuItem = selectObjectById(MenuStrings.MENU_ITEM_SELECT, MenuStrings.MENU_ITEM_SELECT_WRONG, getMenuItemFunction);
        Integer count = selectUnsignedInteger(MenuStrings.MENU_ITEM_SELECT_COUNT, MenuStrings.MENU_ITEM_SELECT_COUNT_WRONG, 100);
        OrderedMenuItem orderedMenuItem = new OrderedMenuItem(
                menuItem,
                count,
                menuItem.getPrice().multiply(BigDecimal.valueOf(count)),
                order
        );
        order.getOrderedMenuItems().add(orderedMenuItem);
        order.setStatus(orderStatus.getServing());
        databaseController.save(orderedMenuItem);
        databaseController.save(order);
    }

}
