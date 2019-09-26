package ee.sda.mckirill.strings;

public class OrderStrings extends BaseString {
    public static final String MANAGER_ORDERS_MAIN_ACTION = PLEASE_SELECT_ACTION
            + "1. Show all Orders" + BR
            + "2. Edit Order" + BR
            + "3. Delete Order" + BR
            + O_RETURN_BACK;

    public static final String TABLE_PEOPLES_COUNT = "Peoples";
    public static final String TABLE_STATUS = "Status";
    public static final String TABLE_QUANTITY = "Quantity";
    public static final String TABLE_TABLE_ID = "Table";

    public static final String SELECT_ORDER = GREEN + "Select order ID" + RESET;
    public static final String SELECT_ORDER_WRONG_ID = RED + "Order ID is wrong" + RESET;

    public static final String CLIENT_ORDER_SELECT_DATE = GREEN + "Desired date" + RESET;
    public static final String CLIENT_ORDER_SELECT_TIME = GREEN + "Desired time" + RESET;
    public static final String CLIENT_ORDER_PEOPLES_COUNT_SELECT = "Please type numbers of people(1-15):";
    public static final String CLIENT_ORDER_PEOPLES_COUNT_INVALID = RED + "Peoples count is invalid" + RESET;

    public static final String CLIENT_ORDER_CONFIRM = GREEN + "Your order has been accepted successfully." + RESET;

    public static final String WAITER_PAYMENT = GREEN + "Apply payment" + RESET;
    public static final String SELECT_PAYMENT_TYPE = GREEN + "Select payment type" + RESET;
    public static final String SELECT_PAYMENT_TYPE_WRONG = RED + "Payment type not selected" + RESET;
    public static final String SELECT_SET_AMOUNT = GREEN + "Set amount of payment" + RESET;
    public static final String SELECT_PAYMENT_WRONG = RED + "Amount of payment is wrong" + RESET;
    public static final String SELECT_PAYMENT_AMOUNT_NOT_ENOUGH = RED + "Amount is not enough" + RESET;
    public static final String SELECT_AMOUNT_OF_TIP = GREEN + "Set amount of TIP" + RESET;
    public static final String SELECT_AMOUNT_OF_TIP_CANT_BE_NEGATIVE = RED + "Tip can't be negative" + RESET;
    public static final String PAYMENT_TOTAL = GREEN + "Payment total:" + RESET;
    public static final String PAYMENT_TOTAL_CHANGE = GREEN + "Total change:" + RESET;
    public static final String PAYMENT_TOTAL_TIP = GREEN + "Total tip:" + RESET;
    public static final String TIME_TO_ORDER_INVALID = RED + "Date to Order is invalid. Please select another day." + RESET;
    public static final String SELECT_ORDER_STATUS = GREEN + "Select order status:" + RESET;
    public static final String SELECT_ORDER_STATUS_WRONG = RED + "Order status is wrong" + RESET;
}
