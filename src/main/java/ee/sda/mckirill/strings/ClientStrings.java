package ee.sda.mckirill.strings;

public class ClientStrings extends BaseString {
    public static final String CLIENT_MAIN_ACTION = PLEASE_SELECT_ACTION
            + "1. Reserve a table." + BR
            + "2. Write review." + BR
            + EXIT_FROM_APP;

    public static final String MANAGER_CLIENT_MAIN_ACTION = PLEASE_SELECT_ACTION
            + "1. Show all Clients." + BR
            + "2. Edit Client." + BR
            + "3. Delete Client." + BR
            + O_RETURN_BACK;

    public static final String SELECT_CLIENT_NAME = GREEN + "Type Client name: " + RESET;
    public static final String SELECT_CLIENT_NAME_ERROR = RED + "Wrong Client name" + RESET;
    public static final String SELECT_CLIENT_EMAIL = GREEN + "Type Client email: " + RESET;
    public static final String SELECT_CLIENT_EMAIL_ERROR = RED + "Wrong Client email" + RESET;
    public static final String SELECT_CLIENT_PHONE_NUMBER = GREEN + "Type Client Phone number: " + RESET;
    public static final String SELECT_CLIENT_PHONE_NUMBER_ERROR = RED + "Wrong Client phone number" + RESET;
    public static final String SELECT_CLIENT_ID = GREEN + "Select Client ID" + RESET;
    public static final String SELECT_CLIENT_WRONG_ID = RED + "Client ID is wrong" + RESET;
    public static final String SELECT_PASSWORD = GREEN + "Type password" + RESET;
    public static final String SELECT_PASSWORD_WRONG = RED + "Password wrong" + RESET;
}
