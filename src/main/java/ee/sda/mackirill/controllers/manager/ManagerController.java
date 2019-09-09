package ee.sda.mackirill.controllers.manager;

import ee.sda.mackirill.controllers.AbstractController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.strings.BaseString;
import ee.sda.mackirill.strings.ManagerUIStrings;

public class ManagerController extends AbstractController {

    public ManagerController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        while (true) {
            System.out.println(ManagerUIStrings.MANAGER_MAIN_ACTION);
            switch (scanner.nextLine()) {
                case "1":
                    System.out.println("order");
                    break;
                case "2":
                    System.out.println("review");
                    break;
                case "4":
                    TableMangerController tableMangerController = new TableMangerController(person);
                    tableMangerController.start();
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
