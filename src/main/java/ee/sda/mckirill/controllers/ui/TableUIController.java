package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.Table;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.TableStrings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TableUIController extends AbstractUIController {
    public TableUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        Map<Integer, Consumer> tableActions = new HashMap<>();
        tableActions.put(1, T -> System.out.println(BaseString.TODO)); //TODO
        tableActions.put(2, T -> editTable(new Table())); //TODO
        tableActions.put(3, T -> System.out.println(BaseString.TODO)); //TODO
        tableActions.put(4, T -> System.out.println(BaseString.TODO)); //TODO

        selectMenuAction(TableStrings.MANAGER_TABLES_MAIN_ACTION, tableActions);
    }

    private void editTable(Table table) {
        System.out.println(TableStrings.TABLE_ADD_NEW);
        table.setSize(selectUnsignedInteger(TableStrings.TABLE_SELECT_SIZE, TableStrings.TABLE_WRONG_SIZE, 15));
        save(table);
    }
}
