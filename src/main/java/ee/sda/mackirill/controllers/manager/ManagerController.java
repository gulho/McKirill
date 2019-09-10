package ee.sda.mackirill.controllers.manager;

import ee.sda.mackirill.controllers.AbstractController;
import ee.sda.mackirill.controllers.Factory;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.enums.ControllrsEnum;
import ee.sda.mackirill.strings.BaseString;
import ee.sda.mackirill.strings.ManagerUIStrings;

public class ManagerController extends AbstractController {

    public ManagerController(Person person) {
        super(person);
    }

    @Override
    public void start() throws Exception {
        while (true) {
            System.out.println(ManagerUIStrings.MANAGER_MAIN_ACTION);
            switch (scanner.nextLine()) {
                case "1":
                    Factory.getController(person, ControllrsEnum.ORDER).start();
                    break;
                case "2":
                    Factory.getController(person, ControllrsEnum.MENU).start();
                    break;
                case "4":
                    Factory.getController(person, ControllrsEnum.TABLE).start();
                    break;
                case "exit":
                case "e":
                    System.out.println(BaseString.EXIT);
                    return;
                default:
                    System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }
}
