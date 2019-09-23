package ee.sda.mckirill.strings;

public class WaiterStrings extends BaseString {
    public static final String WAITER_MAIN_ACTION = PLEASE_SELECT_ACTION
            + "1. Add food to Order" + BR
            + "2. Order payment" + BR
            + EXIT_FROM_APP;
    public static final String MANAGER_WAITER_MAIN_ACTION = PLEASE_SELECT_ACTION
            + "1. Show all waiters" + BR
            + "2. Add new waiter" + BR
            + "3. Edit waiter" + BR
            + "4. Delete waiter" + BR
            + O_RETURN_BACK;

    public static final String TABLE_WAITER_TIPS = "Waiter tips";

    public static final String SELECT_WAITER_NAME = GREEN + "Type Waiter name: " + RESET;
    public static final String SELECT_WAITER_NAME_ERROR = RED + "Wrong Waiter name" + RESET;
    public static final String SELECT_WAITER_EMAIL = GREEN + "Type Waiter email: " + RESET;
    public static final String SELECT_WAITER_EMAIL_ERROR = RED + "Wrong Waiter email" + RESET;
    public static final String SELECT_WAITER_PHONE_NUMBER = GREEN + "Type Waiter Phone number: " + RESET;
    public static final String SELECT_WAITER_PHONE_NUMBER_ERROR = RED + "Wrong Waiter phone number" + RESET;
    public static final String SELECT_WAITER_ID = GREEN + "Select Waiter ID" + RESET;
    public static final String SELECT_WAITER_WRONG_ID = RED + "Waiter ID is wrong" + RESET;
}
