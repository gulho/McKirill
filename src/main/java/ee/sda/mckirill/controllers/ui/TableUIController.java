package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.Table;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.TableStrings;
import ee.sda.mckirill.util.ConsoleTablePrint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TableUIController extends AbstractUIController {
    public TableUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        Map<Integer, Consumer> tableActions = new HashMap<>();
        tableActions.put(1, T -> showAllTables()); //TODO
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

    private void showAllTables() {
        List<Table> tables = getListOfTables();
        ConsoleTablePrint tableTable = new ConsoleTablePrint();
        tableTable.setShowVerticalLines(true);
        tableTable.setHeaders(TableStrings.TABLE_ID, TableStrings.TABLE_SIZE, TableStrings.TABLE_IS_AVAILABLE);
        for(Table table : tables) {
            tableTable.addRow(table.getId() + "", table.getSize() + "", Boolean.valueOf(table.isIs_available()).toString());
        }
        tableTable.print();
    }
}
