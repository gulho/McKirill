package ee.sda.mackirill.controllers.UI;

import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.strings.BaseString;

public class WaitersUIController extends AbstractUIController {
    public WaitersUIController(Person person) {
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
