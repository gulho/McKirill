package ee.sda.mackirill.controllers;

import ee.sda.mackirill.entities.Person;
import org.hibernate.Session;

import java.util.Scanner;

public abstract class AbstractController {
    protected static Person person;
    protected static Scanner scanner = ApplicationContext.getScanner();
    protected static Session session = ApplicationContext.getSession();

    public AbstractController(Person person) {
        this.person = person;
    }

    public abstract void start();

}
