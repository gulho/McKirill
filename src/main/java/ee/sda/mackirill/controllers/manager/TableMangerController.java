package ee.sda.mackirill.controllers.manager;

import ee.sda.mackirill.controllers.AbstractController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.entities.Table;
import ee.sda.mackirill.strings.BaseString;

public class TableMangerController extends AbstractController {
    public TableMangerController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        while(true) {
            switch (scanner.nextLine()) {
                case "1":
                    break;
                case "2":
                    editTable(new Table());
                    break;
                case "0":
                    return;
                default:
                    System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }

    private void editTable(Table table) {
        System.out.println();
    }
}
