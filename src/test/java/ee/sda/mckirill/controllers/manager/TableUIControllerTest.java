package ee.sda.mckirill.controllers.manager;

import ee.sda.mckirill.controllers.ui.TableUIController;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.PersonType;
import ee.sda.mckirill.enums.PersonTypeEnum;
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