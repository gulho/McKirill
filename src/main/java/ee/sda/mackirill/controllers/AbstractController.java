package ee.sda.mackirill.controllers;

import ee.sda.mackirill.entities.Person;

import java.util.Scanner;

public abstract class AbstractController {
    public static Person person;
    public static Scanner scanner = ApplicationContext.getScanner();

    public AbstractController(Person person) {
        this.person = person;
    }

    public abstract void start();
}
