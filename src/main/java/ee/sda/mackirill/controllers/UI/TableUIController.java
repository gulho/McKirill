package ee.sda.mackirill.controllers.UI;

import ee.sda.mackirill.controllers.EntityControllers.TableController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.entities.Table;
import ee.sda.mackirill.strings.BaseString;
import ee.sda.mackirill.strings.ManagerUIStrings;
import ee.sda.mackirill.strings.TableStrings;

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
                    break;
                case "0":
                    return;
                default:
                    System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }

    private void editTable(Table table) {
        int tableSize = 0;
        while (true) {
            System.out.println(TableStrings.TABLE_SELECT_SIZE);
            tableSize = scanner.nextInt();
            if(tableSize > 0 && tableSize <= 15) {
                break;
            } else {
                System.out.println(TableStrings.TABLE_WRONG_SIZE);
            }
        }
        table.setSize(tableSize);
        TableController.saveTable(table);
    }
}
