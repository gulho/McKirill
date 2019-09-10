package ee.sda.mackirill.strings;

public class BaseString {
    public static final String BR = System.lineSeparator();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String PLEASE_SELECT_ACTION = "Please select action.";
    public static final String EXIT_FROM_APP = "exit: To quit from app";
    public static final String EXIT = "Exit. Goodbye!";
    public static final String WELCOME = "Welcome to MacKirill restaurant management system";
    public static final String WRONG_COMMAND = ANSI_RED + "Command is wrong. Please print correct command." + ANSI_RESET;
    public static final String O_RETURN_BACK = "0. return back.";
}
