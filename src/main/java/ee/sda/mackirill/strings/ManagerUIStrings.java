package ee.sda.mackirill.strings;

public class ManagerUIStrings extends BaseString {
    public static final String MANAGER_MAIN_ACTION = PLEASE_SELECT_ACTION + BR
            + "1. Manage orders." + BR
            + "2. Manage Menu items" + BR
            + "3. Manage Clients." + BR
            + "4. Manage Tables. " + BR
            + "5. Manage Waiters" + BR
            + "6. Manage holidays" + BR
            + "7. Statistics" + BR
            + EXIT_FROM_APP;

    //Orders strings
    public static final String MANAGER_ORDERS_MAIN_ACTION = PLEASE_SELECT_ACTION + BR
            + "1. Show all orders" + BR
            + "2. Edit Order" + BR
            + "3. Delete Order" + BR
            + O_RETURN_BACK;

    //Tables strings
    public static final String MANAGER_TABLES_MAIN_ACTION = PLEASE_SELECT_ACTION + BR
            + "1. Show all Tables" + BR
            + "2. Add new Table" + BR
            + "3. Edit Table" + BR
            + "4. Remove Table" + BR
            + O_RETURN_BACK;

    public static final String TABLE_ADD_NEW = "Add new table.";
    public static final String TABLE_SELECT_SIZE = "Print Table size (1 - 15): ";
    public static final String TABLE_WRONG_SIZE = RED + "Table size is wrong! It should be between 1 and 15." + RESET;

    //Menu strings
    public static final String MANAGER_MENU_MAIN_ACTION = PLEASE_SELECT_ACTION + BR
            + "1. Show all menu items" + BR
            + "2. Add new Menu item" + BR
            + "3. Edit Menu item" + BR
            + "4. Remove Menu item" + BR
            + O_RETURN_BACK;

    public static final String MENU_ADD_NEW = RED + GREEN_BACKGROUND +"Add new Menu item" + RESET;
    public static final String MENU_SET_NAME = GREEN + "Print Menu item name:" + RESET;
    public static final String MENU_EMPTY_NAME = RED + "Menu item name can not be empty!" + RESET;
    public static final String MENU_SET_TYPE = GREEN + "Print Menu item type:" + RESET;
    public static final String MENU_WRONG_TYPE = RED + "Type is incorrect" + RESET;
    public static final String MENU_SET_PRICE = GREEN + "Print Menu item price:" + RESET;
    public static final String MENU_PRICE_0_LOW = RED + "Price can not be lower that 0" + RESET;
}
