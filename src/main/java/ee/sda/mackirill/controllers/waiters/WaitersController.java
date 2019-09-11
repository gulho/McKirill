package ee.sda.mackirill.controllers.waiters;

import ee.sda.mackirill.controllers.AbstractController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.strings.BaseString;

public class WaitersController extends AbstractController {
    public WaitersController(Person person) {
        super(person);
    }

    @Override
    public void start() throws Exception {
        while (true) {
            switch (scanner.nextLine()) {
                case "0":
                    return;
                default:
                    System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }
}
