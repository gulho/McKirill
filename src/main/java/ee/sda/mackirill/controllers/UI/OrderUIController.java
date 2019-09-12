package ee.sda.mackirill.controllers.UI;

import ee.sda.mackirill.controllers.UI.AbstractUIController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.strings.BaseString;
import ee.sda.mackirill.strings.ManagerUIStrings;

public class OrderUIController extends AbstractUIController {
    public OrderUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        while(true) {
            System.out.println(ManagerUIStrings.MANAGER_ORDERS_MAIN_ACTION);
            switch (scanner.nextLine()) {
                case "1":
                    break;
                case "0":
                    return;
                default:
                    System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }
}
