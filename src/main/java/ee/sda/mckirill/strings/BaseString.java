package ee.sda.mckirill.strings;

public class BaseString extends ConsoleColors {
    public static final String BR = System.lineSeparator();
    public static final char EURO =  0x20AC;

    public static final String PLEASE_SELECT_ACTION = "Please select action." + BR;
    public static final String EXIT_FROM_APP = "exit: To quit from app";
    public static final String EXIT = "Exit. Goodbye!";
    public static final String WELCOME = "Welcome to MacKirill restaurant management system";
    public static final String WRONG_COMMAND = RED + "Command is wrong. Please print correct command." + RESET;
    public static final String O_RETURN_BACK = "0. return back.";
    public static final String SAVE_IN_DB = GREEN + "Saved in databse" + RESET;

    public static final String NOT_LOGIN = RED + "Yo do not log in" + RESET;

    public static final String TABLE_ID = PURPLE + "ID" + RESET;
    public static final String TABLE_PERSON = GREEN + " Person name" + RESET;

    public static final String SELECT_ID_NOT_INTEGER = RED + "Typed value not integer" + RESET;

}
