package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.strings.BaseString;

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
