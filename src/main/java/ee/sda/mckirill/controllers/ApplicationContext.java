package ee.sda.mckirill.controllers;

import ee.sda.mckirill.controllers.entity.PersonController;
import ee.sda.mckirill.controllers.types.OrderStatusType;
import ee.sda.mckirill.controllers.types.PaymentTypeType;
import ee.sda.mckirill.controllers.types.PersonTypeType;
import ee.sda.mckirill.entities.OrderStatus;
import ee.sda.mckirill.entities.PaymentType;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.PersonType;
import ee.sda.mckirill.enums.OrderStatusEnum;
import ee.sda.mckirill.enums.PaymentTypeEnum;
import ee.sda.mckirill.enums.PersonTypeEnum;
import ee.sda.mckirill.strings.DefaultManager;
import ee.sda.mckirill.strings.DefaultWaiter;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import java.util.Optional;
import java.util.Scanner;

public class ApplicationContext {
    private static Session session = connectToDb();
    private static Scanner scanner = new Scanner(System.in);
    private static OrderStatusType orderStatusType = OrderStatusType.of();
    private static PersonTypeType personTypeType = PersonTypeType.of();
    private static PaymentTypeType paymentTypeType = PaymentTypeType.of();

    public ApplicationContext() {
        checkManagerExist();
        checkWaiterExist();
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
        try {
            Person manager = session
                    .createQuery("select p from Person p, PersonType pt where p.personType = pt.id AND pt.type = :type ", Person.class)
                    .setParameter("type", PersonTypeEnum.MANAGER).getSingleResult();
        } catch (NoResultException nr) {
            PersonType managerPerson = session.byNaturalId(PersonType.class).using("type", PersonTypeEnum.MANAGER).load();
            PersonController.savePerson(new Person(
                    DefaultManager.DEFAULT_MANAGER_NAME,
                    DefaultManager.DEFAULT_MANAGER_EMAIL,
                    DefaultManager.DEFAULT_MANAGER_PASSWORD,
                    DefaultManager.DEFAULT_MANAGER_PHONE_NUMBER,
                    personTypeType.getManager()));
        }
    }

    private void checkWaiterExist() {
        try {
            Person waiter = session
                    .createQuery("select p from Person p, PersonType pt where p.personType = pt.id AND pt.type = :type ", Person.class)
                    .setParameter("type", PersonTypeEnum.WAITER).getSingleResult();
        } catch (NoResultException nr) {
            PersonController.savePerson(new Person(
                    DefaultWaiter.DEFAULT_WAITER_NAME,
                    DefaultWaiter.DEFAULT_WAITER_EMAIL,
                    DefaultWaiter.DEFAULT_WAITER_PASSWORD,
                    DefaultWaiter.DEFAULT_WAITER_PHONE_NUMBER,
                    personTypeType.getWaiter()));
        }
    }
}
