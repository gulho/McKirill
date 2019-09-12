package ee.sda.mackirill.controllers.UI;

import ee.sda.mackirill.controllers.ApplicationContext;
import ee.sda.mackirill.entities.Person;
import org.hibernate.Session;

import java.util.Scanner;

public abstract class AbstractUIController {
    protected static Person person;
    protected static Scanner scanner = ApplicationContext.getScanner();
    //protected static Session session = ApplicationContext.getSession();

    public AbstractUIController(Person person) {
        this.person = person;
    }

    public abstract void start() throws Exception;

}
