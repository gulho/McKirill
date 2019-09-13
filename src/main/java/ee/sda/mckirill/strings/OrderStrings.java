package ee.sda.mckirill.strings;

public class OrderStrings extends BaseString {
    public static final String MANAGER_ORDERS_MAIN_ACTION = PLEASE_SELECT_ACTION
            + "1. Show all orders" + BR
            + "2. Edit Order" + BR
            + "3. Delete Order" + BR
            + O_RETURN_BACK;

    public static String TABLE_PEOPLES_COUNT = GREEN + "Peoples" + RESET;
    public static String TABLE_STATUS = GREEN + "Status" + RESET;

    public static final String SELECT_ORDER = GREEN + "Select order ID" + RESET;
    public static final String SELECT_ORDER_WRONG_ID = RED + "Order ID is wrong" + RESET;

    public static final String CLIENT_ORDER_BOOKING_DATE_SELECT = "Please type date(d.m.yyyy): ";
    public static final String CLIENT_ORDER_DATE_IN_INVALID = RED + "Order date is invalid." + RESET;

    public static final String CLIENT_ORDER_TIME_SELECT = "Please type time (H.m):";
    public static final String CLIENT_ORDER_TIME_IN_INVALID = RED + "Order time is invalid." + RESET;

    public static final String CLIENT_ORDER_PEOPLES_COUNT_SELECT = "Please type numbers of people(1-15):";
    public static final String CLIENT_ORDER_PEOPLES_COUNT_INVALID = RED + "Peoples count is invalid" + RESET;

    public static final String CLIENT_ORDER_CONFIRM = GREEN + "Your order has been accepted successfully." + RESET;


}
