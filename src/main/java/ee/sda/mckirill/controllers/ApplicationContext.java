package ee.sda.mckirill.controllers;

import ee.sda.mckirill.controllers.types.OrderStatusType;
import ee.sda.mckirill.controllers.types.PaymentTypeType;
import ee.sda.mckirill.controllers.types.PersonTypeType;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.PersonType;
import ee.sda.mckirill.enums.PersonTypeEnum;
import ee.sda.mckirill.strings.DefaultManager;
import ee.sda.mckirill.strings.DefaultWaiter;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class ApplicationContext {
    private static Session session = connectToDb();
    private static Scanner scanner = new Scanner(System.in);
    private static OrderStatusType orderStatusType = OrderStatusType.of();
    private static PersonTypeType personTypeType = PersonTypeType.of();
    private static PaymentTypeType paymentTypeType = PaymentTypeType.of();
    private static int startWorking;

    public ApplicationContext() {
        checkManagerExist();
        checkWaiterExist();

        try {
            Properties properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream("mckirill.settings.properties"));
            startWorking = Integer.valueOf(properties.getProperty("workingTimeStart"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Session connectToDb() {
        return new Configuration().configure().buildSessionFactory().openSession();
    }

    public static Session getSession() {
        return session;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static OrderStatusType getOrderStatusType() {
        return orderStatusType;
    }

    public static PersonTypeType getPersonTypeType() {
        return personTypeType;
    }

    public static PaymentTypeType getPaymentTypeType() {
        return paymentTypeType;
    }

    private void checkManagerExist() {
        if (!DatabaseController.of().getListFromNamedQuery("get_all_mangers", Person.class).isEmpty()) {
            DatabaseController.of().saveInDatabase(new Person(
                    DefaultManager.DEFAULT_MANAGER_NAME,
                    DefaultManager.DEFAULT_MANAGER_EMAIL,
                    DefaultManager.DEFAULT_MANAGER_PASSWORD,
                    DefaultManager.DEFAULT_MANAGER_PHONE_NUMBER,
                    personTypeType.getManager()));
        }
    }

    private void checkWaiterExist() {
        if (!DatabaseController.of().getListFromNamedQuery("get_all_waiters", Person.class).isEmpty()) {
            DatabaseController.of().saveInDatabase(new Person(
                    DefaultWaiter.DEFAULT_WAITER_NAME,
                    DefaultWaiter.DEFAULT_WAITER_EMAIL,
                    DefaultWaiter.DEFAULT_WAITER_PASSWORD,
                    DefaultWaiter.DEFAULT_WAITER_PHONE_NUMBER,
                    personTypeType.getWaiter()));
        }
    }
}
