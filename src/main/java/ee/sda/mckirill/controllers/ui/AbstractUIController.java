package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.ApplicationContext;
import ee.sda.mckirill.controllers.types.OrderStatusType;
import ee.sda.mckirill.controllers.types.PaymentTypeType;
import ee.sda.mckirill.controllers.types.PersonTypeType;
import ee.sda.mckirill.entities.Person;

import java.math.BigDecimal;
import java.util.Scanner;

public abstract class AbstractUIController {
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

    public static BigDecimal getBigDecimal(String headerString, String belowZeroError) {
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

}
