package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.ApplicationContext;
import ee.sda.mckirill.controllers.types.OrderStatusType;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.strings.BaseString;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public abstract class AbstractUIController<U> {
    protected static Person person;
    protected static Scanner scanner = ApplicationContext.getScanner();
    protected static OrderStatusType orderStatus = ApplicationContext.getOrderStatusType();
    //protected static Session session = ApplicationContext.getSession();

    public AbstractUIController(Person person) {
        this.person = person;
    }

    public abstract void start() throws Exception;

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
            if(returnEnum.isPresent()) {
                return (E)returnEnum.get();
            } else {
                System.out.println(errorString);
            }
        }
    }
}
