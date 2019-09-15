package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.models.TableController;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.Table;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.TableStrings;

public class TableUIController extends AbstractUIController {
    public TableUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        while(true) {
            System.out.println(TableStrings.MANAGER_TABLES_MAIN_ACTION);
            switch (scanner.nextLine()) {
                case "1":
                    break;
                case "2":
                    System.out.println(TableStrings.TABLE_ADD_NEW);
                    editTable(new Table());
                    endOfUIInteraction();
                    break;
                case "0":
                    return;
                default:
                    System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }

    private void editTable(Table table) {
        table.setSize(getUnsignedInteger(TableStrings.TABLE_SELECT_SIZE, TableStrings.TABLE_WRONG_SIZE, 15));
        TableController.saveTable(table);
    }
}
