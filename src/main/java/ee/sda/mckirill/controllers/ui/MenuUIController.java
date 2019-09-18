package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.entities.MenuItem;
import ee.sda.mckirill.entities.Order;
import ee.sda.mckirill.entities.OrderedMenuItem;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.MenuItemsTypeEnum;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.MenuStrings;
import ee.sda.mckirill.util.ConsoleTablePrint;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class MenuUIController extends AbstractUIController {
    public MenuUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        Map<Integer, Consumer> menuActions = new HashMap<>();
        menuActions.put(1, T -> showAllMenuItems());
        menuActions.put(2, T -> editMenu(new MenuItem()));
        menuActions.put(3, T -> System.out.println(BaseString.TODO)); //TODO
        menuActions.put(4, T -> System.out.println(BaseString.TODO)); //TODO
        selectMenuAction(MenuStrings.MANAGER_MENU_MAIN_ACTION, menuActions);
    }

    private void editMenu(MenuItem menuItem) {
        System.out.println(MenuStrings.MENU_ADD_NEW);
        menuItem.setName(selectString(MenuStrings.MENU_SET_NAME, MenuStrings.MENU_EMPTY_NAME, 50));
        menuItem.setType(selectEnum(MenuStrings.MENU_SET_TYPE, MenuStrings.MENU_WRONG_TYPE, MenuItemsTypeEnum.class));
        menuItem.setPrice(selectBigDecimal(MenuStrings.MENU_SET_PRICE, MenuStrings.MENU_PRICE_0_LOW));
        saveInDatabase(menuItem);
        System.out.println(BaseString.SAVE_IN_DB);
    }

    private void showAllMenuItems() {
        List<MenuItem> menuItems = getListFromNamedQuery("get_all_menuItems", MenuItem.class);
        ConsoleTablePrint menuTable = new ConsoleTablePrint();
        menuTable.setShowVerticalLines(true);
        menuTable.setHeaders(
                MenuStrings.TABLE_ID, MenuStrings.TABLE_MENU_ITEM_NAME, MenuStrings.TABLE_MENU_ITEM_PRICE, MenuStrings.TABLE_MENU_ITEM_TYPE);
        for (MenuItem menuItem : menuItems) {
            menuTable.addRow(menuItem.getId() + "", menuItem.getName(), menuItem.getPrice().toString() + MenuStrings.EURO, menuItem.getType().toString());
        }
        menuTable.print();
    }

    public void addAdditionalFood(Order order) {
        showAllMenuItems();
        Function<Integer, Optional<MenuItem>> getMenuItemFunction = T -> findById(MenuItem.class, T);
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
        saveInDatabase(orderedMenuItem);
        saveInDatabase(order);
    }

}
