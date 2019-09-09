package ee.sda.mackirill.controllers.client;

import ee.sda.mackirill.controllers.AbstractController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.strings.BaseString;
import ee.sda.mackirill.strings.ClientUIStrings;

public class ClientController extends AbstractController {
    public ClientController(Person person) {
        super(person);
    }
    @Override
    public void start() {
        while(true) {
            printClientMainSelect();
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("order");
                    break;
                case "2":
                    System.out.println("review");
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
