package ee.sda.mackirill.controllers.manager;

import ee.sda.mackirill.controllers.UI.TableUIController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.entities.PersonType;
import ee.sda.mackirill.enums.PersonTypeEnum;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

class TableUIControllerTest {

    @Test
    void start() {
        var person = new Person("name", "email", "password","324532", new PersonType(PersonTypeEnum.CLIENT));
        var tableManagerController = new TableUIController(person);
        tableManagerController.start();
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);

    }
}