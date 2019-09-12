package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllrsEnum;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.ManagerUIStrings;

public class ManagerUIController extends AbstractUIController {

    public ManagerUIController(Person person) {
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
                    endOfUIIntercation();
                    break;
                case "4":
                    Factory.getController(person, ControllrsEnum.TABLE).start();
                    endOfUIIntercation();
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
