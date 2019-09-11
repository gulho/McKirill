package ee.sda.mackirill.controllers.manager;

import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.entities.PersonType;
import ee.sda.mackirill.enums.PersonTypeEnum;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TableMangerControllerTest {

    @Test
    void start() {
        var person = new Person("name", "email", "password","324532", new PersonType(PersonTypeEnum.CLIENT));
        var tableManagerController = new TableMangerController(person);
        tableManagerController.start();
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);

    }
}