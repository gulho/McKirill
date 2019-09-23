package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.ApplicationContext;
import ee.sda.mckirill.controllers.DatabaseController;
import ee.sda.mckirill.controllers.types.OrderStatusType;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.strings.BaseString;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractUIController extends DatabaseController {
    protected static Person person;
    protected static Scanner scanner = ApplicationContext.getScanner();
    protected static OrderStatusType orderStatus = ApplicationContext.getOrderStatusType();
    //protected static Session session = ApplicationContext.getSession();

    public AbstractUIController(Person person) {
        this.person = person;
    }

    public abstract void start();

    //Created for fix Error with scanner
    public static void endOfUIInteraction() {
        /*if (scanner.hasNextLine())
            scanner.nextLine();*/
        while (scanner.hasNext("\n")) {
            scanner.next();
        }
    }

    public static BigDecimal selectBigDecimal(String headerString, String belowZeroError) {
        BigDecimal returnBigDecimal;
        while (true) {
            endOfUIInteraction();
            System.out.println(headerString);
            try {
                returnBigDecimal = BigDecimal.valueOf(Double.valueOf(scanner.nextLine()));
                if (returnBigDecimal.signum() >= 0) {
                    break;
                } else {
                    System.out.println(belowZeroError);
                }
            } catch (NumberFormatException e) {
                System.out.println(BaseString.NUMBER_FORMAT_EXCEPTION);
            }
        }
        return returnBigDecimal;
    }

    public static String selectString(String headerString, String emptyError, int lengthStr) {
        String returnString;
        while (true) {
            System.out.println(headerString);
            returnString = scanner.nextLine();
            if (!returnString.isEmpty() && returnString.length() <= lengthStr) {
                break;
            } else if (returnString.length() > lengthStr) {
                System.out.println(BaseString.SELECT_STRING_TO_LONG + lengthStr);
            } else {
                System.out.println(emptyError);
            }
        }
        return returnString;
    }

    public static int selectUnsignedInteger(String headerString, String errorString, int maximumValue) {
        while (true) {
            System.out.println(headerString);
            endOfUIInteraction();
            try {
                int returnInt = Integer.valueOf(scanner.nextLine());
                if (returnInt > 0 && returnInt <= maximumValue) {
                    return returnInt;
                } else {
                    System.out.println(errorString);
                }
            } catch (NumberFormatException e) {
                System.out.println(BaseString.SELECT_ID_NOT_INTEGER);
            }
        }
    }

    public static <E extends Enum<E>> E selectEnum(String headerString, String errorString, Class<E> enumType) {
        while (true) {
                System.out.println(headerString);
                for (Enum<E> paymentTypeEnum : enumType.getEnumConstants()) {
                    System.out.println(paymentTypeEnum.toString());
                }
            try {
                endOfUIInteraction();
                String typedEnumItem = scanner.nextLine().toUpperCase();
                Optional returnEnum = Arrays.stream(enumType.getEnumConstants())
                        .filter(e -> e.name().equals(typedEnumItem))
                        .findFirst();
                if (returnEnum.isPresent()) {
                    return (E) returnEnum.get();
                } else {
                    throw new NoSuchElementException("Not Enum");
                }
            } catch (NoSuchElementException e) {
                System.out.println(errorString);
            }
        }
    }

    public static <R> R selectObjectById(String headerString, String errorString, Function function) {
        Optional<R> returnObject;
        while (true) {
            System.out.println(headerString);
            endOfUIInteraction();
            try {
                Integer selectedInt = Integer.valueOf(scanner.nextLine());
                if(selectedInt <= 0) {
                    throw new NumberFormatException();
                }
                returnObject = (Optional<R>) function.apply(selectedInt);
                if (returnObject.isEmpty()) {
                    System.out.println(errorString);
                } else {
                    return returnObject.get();
                }
            } catch (NumberFormatException e) {
                System.out.println(BaseString.SELECT_ID_NOT_INTEGER);
            }
        }
    }

    public static void selectMenuAction(String headerString, Map<Integer, Consumer> actionMap) {
        while (true) {
            System.out.println(headerString);
            endOfUIInteraction();
            try {
                Integer action = Integer.valueOf(scanner.nextLine());
                if (action == 0) {
                    return;
                }
                actionMap.get(action).accept(null);
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println(BaseString.SELECT_ID_NOT_INTEGER);
            } catch (NullPointerException e) {
                System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }

    public static LocalDate selectDate(String selectDateString) {
        while(true) {
            try {
                System.out.println(selectDateString);
                String[] dateStrings = selectString(BaseString.SELECT_DATE, BaseString.SELECT_DATE_INVALID, 10).split("\\.");
                if (dateStrings.length == 3) {
                    return LocalDate.of(
                            Integer.valueOf(dateStrings[2]),
                            Integer.valueOf(dateStrings[1]),
                            Integer.valueOf(dateStrings[0]));
                } else {
                    throw new DateTimeException("Date is wrong");
                }
            } catch (DateTimeException e) {
                System.out.println(BaseString.SELECT_DATE_INVALID);
            }
        }
    }

    public static LocalTime selectTime(String selectTimeString) {
        while (true) {
            try {
                System.out.println(selectTimeString);
                String[] timeStrings = selectString(BaseString.SELECT_TIME, BaseString.SELECT_TIME_INVALID, 5).split("\\.");
                if (timeStrings.length == 2) {
                    return LocalTime.of(
                            Integer.valueOf(timeStrings[0]),
                            Integer.valueOf(timeStrings[1]));
                } else {
                    throw new DateTimeException("Time is incorrect");
                }
            } catch (DateTimeException e) {
                System.out.println(BaseString.SELECT_TIME_INVALID);
            }
        }
    }
}
