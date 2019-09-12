package ee.sda.mackirill.controllers.ui;

import ee.sda.mackirill.controllers.Factory;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.enums.ControllrsEnum;
import ee.sda.mackirill.strings.BaseString;
import ee.sda.mackirill.strings.ClientUIStrings;

public class ClientUIController extends AbstractUIController {
    public ClientUIController(Person person) {
        super(person);
    }
    @Override
    public void start() throws Exception{
        while(true) {
            printClientMainSelect();
            switch (scanner.nextLine()) {
                case "1":
                    Factory.getController(person, ControllrsEnum.ORDER).start();
                    endOfUIIntercation();
                    break;
                case "2":
                    Factory.getController(person, ControllrsEnum.CLIENT_REVIEW);
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

    private void printClientMainSelect() {
        System.out.println(ClientUIStrings.CLIENT_MAIN_ACTION);
    }
}
