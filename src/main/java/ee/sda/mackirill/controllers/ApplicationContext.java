package ee.sda.mackirill.controllers;

import ee.sda.mackirill.entities.OrderStatus;
import ee.sda.mackirill.entities.PaymentType;
import ee.sda.mackirill.entities.PersonType;
import ee.sda.mackirill.enums.OrderStatusEnum;
import ee.sda.mackirill.enums.PaymentTypeEnum;
import ee.sda.mackirill.enums.PersonTypeEnum;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.util.Optional;
import java.util.Scanner;

public class ApplicationContext {
    private static Session session;
    private static Scanner scanner = new Scanner(System.in);
    public ApplicationContext() {
        session = connectToDb();
        checkDBHaveEnumsValue();
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
}
