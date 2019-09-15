package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.ApplicationContext;
import ee.sda.mckirill.controllers.types.OrderStatusType;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.strings.BaseString;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractUIController<U> {
    protected static Person person;
    protected static Scanner scanner = ApplicationContext.getScanner();
    protected static OrderStatusType orderStatus = ApplicationContext.getOrderStatusType();
    //protected static Session session = ApplicationContext.getSession();

    public AbstractUIController(Person person) {
        this.person = person;
    }

    public abstract void start();

    //Creater for fic Error with scanner
    public static void endOfUIInteraction() {
        if (scanner.hasNextLine())
            scanner.nextLine();
    }

    public static BigDecimal selectBigDecimal(String headerString, String belowZeroError) {
        BigDecimal returnBigDecimal;
        while (true) {
            System.out.println(headerString);
            returnBigDecimal = scanner.nextBigDecimal();
            if (returnBigDecimal.signum() >= 0) {
                break;
            } else {
                System.out.println(belowZeroError);
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
        int returnInt;
        while (true) {
            System.out.println(headerString);
            returnInt = scanner.nextInt();
            if (returnInt > 0 && returnInt <= maximumValue) {
                break;
            } else {
                System.out.println(errorString);
            }
        }
        return returnInt;
    }

    public static <E extends Enum<E>> E selectEnum(String headerString, String errorString, Class<E> enumType) {
        while (true) {
            System.out.println(headerString);
            for (Enum<E> paymentTypeEnum : enumType.getEnumConstants()) {
                System.out.println(paymentTypeEnum.toString());
            }
            String typedEnumItem = scanner.nextLine().toUpperCase();
            Optional returnEnum = Arrays.stream(enumType.getEnumConstants())
                    .filter(e -> e.name().equals(typedEnumItem))
                    .findFirst();
            if (returnEnum.isPresent()) {
                return (E) returnEnum.get();
            } else {
                System.out.println(errorString);
            }
        }
    }

    public static <R> R selectObjectById(String headerString, String errorString, Function function) {
        Optional<R> returnObject;
        while (true) {
            try {
                System.out.println(headerString);
                returnObject = (Optional<R>) function.apply(Integer.valueOf(scanner.nextInt()));
                if (returnObject.isEmpty()) {
                    System.out.println(errorString);
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println(BaseString.SELECT_ID_NOT_INTEGER);
            } finally {
                endOfUIInteraction();
            }
        }
        return returnObject.get();
    }

    public static void selectMenuAction(String headerString, Map<Integer, Consumer> actionMap) {
        while (true) {
            System.out.println(headerString);
            try {
                Integer action = scanner.nextInt();
                if (action == 0) {
                    return;
                }
                actionMap.get(action).accept(null);
            } catch (InputMismatchException e) {
                System.out.println(BaseString.SELECT_ID_NOT_INTEGER);
            } catch (NullPointerException e) {
                System.out.println(BaseString.WRONG_COMMAND);
            } finally {
                endOfUIInteraction();
            }
        }
    }
}
