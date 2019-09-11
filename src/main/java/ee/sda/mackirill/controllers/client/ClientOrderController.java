package ee.sda.mackirill.controllers.client;

import ee.sda.mackirill.controllers.AbstractController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.strings.BaseString;

public class ClientOrderController extends AbstractController {
    public ClientOrderController(Person person) {
        super(person);
    }

    @Override
    public void start() throws Exception {
        while (true) {
            System.out.println();
            switch (scanner.nextLine()) {

            }
        }
    }
}
