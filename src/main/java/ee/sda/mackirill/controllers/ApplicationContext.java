package ee.sda.mackirill.controllers;

import ee.sda.mackirill.controllers.EntetiesControllers.PersonsController;
import ee.sda.mackirill.entities.OrderStatus;
import ee.sda.mackirill.entities.PaymentType;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.entities.PersonType;
import ee.sda.mackirill.enums.OrderStatusEnum;
import ee.sda.mackirill.enums.PaymentTypeEnum;
import ee.sda.mackirill.enums.PersonTypeEnum;
import ee.sda.mackirill.strings.DefaultManager;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import javax.persistence.NoResultException;
import java.util.Optional;
import java.util.Scanner;

public class ApplicationContext {
    private static Session session;
    private static Scanner scanner = new Scanner(System.in);
    public ApplicationContext() {
        session = connectToDb();
        checkDBHaveEnumsValue();
        checkManagerExist();
    }

    private Session connectToDb() {
        return new Configuration().configure().buildSessionFactory().openSession();
    }

    public static Session getSession() {
        return session;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    private void checkDBHaveEnumsValue () {
        session.beginTransaction();
        for (PersonTypeEnum personTypeEnum: PersonTypeEnum.values()) {
            Optional<PersonType> personType = session.byNaturalId(PersonType.class)
                    .using("type", personTypeEnum).loadOptional();
            if(personType.isEmpty()) {
                session.saveOrUpdate(new PersonType(personTypeEnum));

            }
        }

        for(OrderStatusEnum orderStatusEnum: OrderStatusEnum.values()) {
            Optional<OrderStatus> orderStatus = session.byNaturalId(OrderStatus.class)
                    .using("name", orderStatusEnum).loadOptional();
            if(orderStatus.isEmpty()) {
                session.saveOrUpdate(new OrderStatus(orderStatusEnum));
            }
        }

        for (PaymentTypeEnum paymentTypeEnum: PaymentTypeEnum.values()) {
            Optional<PaymentType> paymentType = session.byNaturalId(PaymentType.class)
                    .using("paymentName", paymentTypeEnum).loadOptional();
            if(paymentType.isEmpty()) {
                session.saveOrUpdate(new PaymentType(paymentTypeEnum));
            }
        }
        session.getTransaction().commit();
    }

    private void checkManagerExist() {
        try {
            Person manager = session
                    .createQuery("select p from Person p, PersonType pt where p.personType = pt.id AND pt.type = :type ", Person.class)
                    .setParameter("type", PersonTypeEnum.MANAGER).getSingleResult();
            System.out.println(manager);
        } catch (NoResultException nr) {
            PersonType managerPerson = session.byNaturalId(PersonType.class).using("type", PersonTypeEnum.MANAGER).load();
            PersonsController.savePerson(new Person(
                    DefaultManager.DEFAULT_MANAGER_NAME,
                    DefaultManager.DEFAULT_MANAGER_EMAIL,
                    DefaultManager.DEFAULT_MANAGER_PASSWORD,
                    DefaultManager.DEFAULT_MANAGER_PHONE_NUMBER,
                    managerPerson));
        }

    }
}
