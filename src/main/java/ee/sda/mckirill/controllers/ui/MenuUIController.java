package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.models.MenuController;
import ee.sda.mckirill.controllers.models.OrderController;
import ee.sda.mckirill.entities.MenuItem;
import ee.sda.mckirill.entities.Order;
import ee.sda.mckirill.entities.OrderedMenuItem;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.MenuItemsTypeEnum;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.MenuStrings;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class MenuUIController extends AbstractUIController {
    private MenuController menuController = MenuController.of();
    private OrderController orderController = OrderController.of();

    public MenuUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        while (true) {
            System.out.println(MenuStrings.MANAGER_MENU_MAIN_ACTION);
            String actionSelect = scanner.nextLine();
            switch (actionSelect) {
                case "1":
                    break;
                case "2":
                    System.out.println(MenuStrings.MENU_ADD_NEW);
                    editMenu(new MenuItem());
                    endOfUIInteraction();
                    break;
                case "0":
                    return;
                default:
                    System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }

    private void editMenu(MenuItem menuItem) {
        while (true) {
            System.out.println(MenuStrings.MENU_SET_NAME);
            menuItem.setName(scanner.nextLine());
            if (!menuItem.getName().isEmpty()) {
                break;
            } else {
                System.out.println(MenuStrings.MENU_EMPTY_NAME);
            }
        }
        while (true) {
            System.out.println(MenuStrings.MENU_SET_TYPE);
            for (MenuItemsTypeEnum menuItemsTypeEnum : MenuItemsTypeEnum.values()) {
                System.out.println(menuItemsTypeEnum.toString());
            }
            try {
                menuItem.setType(MenuItemsTypeEnum.valueOf(scanner.nextLine().toUpperCase()));
                break;
            } catch (Exception e) {
                System.out.println(MenuStrings.MENU_WRONG_TYPE);
            }
        }
        while (true) {
            System.out.println(MenuStrings.MENU_SET_PRICE);
            menuItem.setPrice(scanner.nextBigDecimal());
            if (menuItem.getPrice().signum() >= 0) {
                break;
            } else {
                System.out.println(MenuStrings.MENU_PRICE_0_LOW);
            }
        }
        menuController.saveMenuItem(menuItem);
        System.out.println(BaseString.SAVE_IN_DB);
    }

    private void showAllMenuItems() {
        List<MenuItem> menuItems = menuController.getListOfMenuItems();
        System.out.printf("%10s%45s%20s%20s%n",
                MenuStrings.TABLE_ID, MenuStrings.TABLE_MENU_ITEM_NAME, MenuStrings.TABLE_MENU_ITEM_PRICE, MenuStrings.TABLE_MENU_ITEM_TYPE);
        for (MenuItem menuItem : menuItems) {
            System.out.printf("%4d%30s%10s%10s%n",
                    menuItem.getId(), menuItem.getName(), menuItem.getPrice().toString(), menuItem.getType());
        }
    }

    private MenuItem selectMenuItem() {
        Optional<MenuItem> returnMenuItem;
        while (true) {
            System.out.println(MenuStrings.MENU_ITEM_SELECT);
            int scannerSelect;
            while (true) {
                try {
                    scannerSelect = Integer.valueOf(scanner.nextLine());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(MenuStrings.SELECT_ID_NOT_INTEGER);
                }

            }
            returnMenuItem = menuController.findById(scannerSelect);
            if(returnMenuItem.isEmpty()) {
                System.out.println(MenuStrings.MENU_ITEM_SELECT_WRONG);
            } else {
                break;
            }
        }
        return returnMenuItem.get();
    }

    private int selectMenuItemCount() {
        int returnCount;
        while(true) {
            System.out.println(MenuStrings.MENU_ITEM_SELECT_COUNT);
            returnCount = scanner.nextInt();
            if(returnCount < 0 ){
                System.out.println(MenuStrings.MENU_ITEM_SELECT_COUNT_WRONG);
            } else {
                break;
            }
        }
        return returnCount;
    }

    public void addAdditionalFood(Order order) {
        showAllMenuItems();
        MenuItem menuItem = selectMenuItem();
        Integer count = selectMenuItemCount();
        order.getOrderedMenuItems().add(new OrderedMenuItem(
                menuItem,
                count,
                menuItem.getPrice().multiply(BigDecimal.valueOf(count))
        ));
        order.setStatus(orderStatus.getServing());
        orderController.save(order);
    }

}
