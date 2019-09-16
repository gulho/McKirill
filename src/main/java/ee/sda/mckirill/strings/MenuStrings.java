package ee.sda.mckirill.strings;

public class MenuStrings extends BaseString {
    public static final String MANAGER_MENU_MAIN_ACTION = PLEASE_SELECT_ACTION + BR
            + "1. Show all menu items" + BR
            + "2. Add new Menu item" + BR
            + "3. Edit Menu item" + BR
            + "4. Remove Menu item" + BR
            + O_RETURN_BACK;

    public static final String TABLE_MENU_ITEM_NAME = "Menu item Name";
    public static final String TABLE_MENU_ITEM_PRICE = "Price";
    public static final String TABLE_MENU_ITEM_TYPE = "Type";

    public static final String MENU_ADD_NEW = RED + GREEN_BACKGROUND + "Add new Menu item" + RESET;
    public static final String MENU_SET_NAME = GREEN + "Print Menu item name:" + RESET;
    public static final String MENU_EMPTY_NAME = RED + "Menu item name can not be empty!" + RESET;
    public static final String MENU_SET_TYPE = GREEN + "Print Menu item type:" + RESET;
    public static final String MENU_WRONG_TYPE = RED + "Type is incorrect" + RESET;
    public static final String MENU_SET_PRICE = GREEN + "Print Menu item price:" + RESET;
    public static final String MENU_PRICE_0_LOW = RED + "Price can not be lower that 0" + RESET;

    public static final String MENU_ITEM_SELECT = GREEN + "Select Menu item ID:" + RESET;
    public static final String MENU_ITEM_SELECT_WRONG = RED + "Wrong Menu item ID" + RESET;

    public static final String MENU_ITEM_SELECT_COUNT = GREEN + "Select count of menu items" + RESET;
    public static final String MENU_ITEM_SELECT_COUNT_WRONG = RED + "Menu item count is wrong" + RESET;
}
