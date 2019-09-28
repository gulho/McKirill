package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.Table;
import ee.sda.mckirill.strings.TableStrings;
import ee.sda.mckirill.util.ConsoleTablePrint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class TableUIController extends AbstractUIController {
    public TableUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        Map<Integer, Consumer> tableActions = new HashMap<>();
        tableActions.put(1, T -> showAllTables());
        tableActions.put(2, T -> {
            System.out.println(TableStrings.TABLE_ADD_NEW);
            editTable(new Table());
        });
        tableActions.put(3, T -> editTable(selectTable()));
        tableActions.put(4, T -> deleteTable(selectTable()));

        selectMenuAction(TableStrings.MANAGER_TABLES_MAIN_ACTION, tableActions);
    }

    private Table selectTable() {
        showAllTables();
        Function<Integer, Optional<Table>> tableSelectFunction = T -> findById(Table.class, T);
        return selectObjectById(TableStrings.SELECT_TABLE, TableStrings.SELECT_TABLE_WRONG_ID, tableSelectFunction);
    }

    private void editTable(Table table) {
        table.setTableSize(selectUnsignedInteger(TableStrings.TABLE_SELECT_SIZE, TableStrings.TABLE_WRONG_SIZE, 15));
        saveInDatabase(table);
    }

    private void showAllTables() {
        List<Table> tables = getListFromNamedQuery("get_all_tables", Table.class);
        ConsoleTablePrint tableTable = new ConsoleTablePrint();
        tableTable.setShowVerticalLines(true);
        tableTable.setHeaders(TableStrings.TABLE_ID, TableStrings.TABLE_SIZE, TableStrings.TABLE_IS_AVAILABLE);
        for (Table table : tables) {
            tableTable.addRow(table.getId() + "", table.getTableSize() + "", Boolean.valueOf(table.isIs_available()).toString());
        }
        tableTable.print();
    }

    private void deleteTable(Table table) {
        deleteFromDatabase(table);
    }
}
