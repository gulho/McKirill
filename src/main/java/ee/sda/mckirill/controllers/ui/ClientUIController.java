package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.strings.ClientStrings;
import ee.sda.mckirill.util.ConsoleTablePrint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ClientUIController extends AbstractUIController {
    public ClientUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        switch (person.getPersonType().getType()) {
            case CLIENT:
                Map<Integer, Consumer> clientActions = new HashMap<>();
                clientActions.put(1, T -> Factory.getController(person, ControllersEnum.ORDER).start());
                clientActions.put(2, T -> Factory.getController(person, ControllersEnum.REVIEW).start());

                selectMenuAction(ClientStrings.CLIENT_MAIN_ACTION, clientActions);
                break;
            case MANAGER:
                Map<Integer, Consumer> managerClientAction = new HashMap<>();
                managerClientAction.put(1, T -> showPersonsTable());
                managerClientAction.put(2, T -> System.out.println(ClientStrings.TODO));
                managerClientAction.put(3, T -> System.out.println(ClientStrings.TODO));

                selectMenuAction(ClientStrings.MANAGER_CLIENT_MAIN_ACTION, managerClientAction);
                break;
        }
    }

    private void showPersonsTable() {
        ConsoleTablePrint clientTable = new ConsoleTablePrint();
        clientTable.setShowVerticalLines(true);
        clientTable.setHeaders(ClientStrings.TABLE_ID, ClientStrings.TABLE_PERSON, ClientStrings.TABLE_EMAIL, ClientStrings.TABLE_PHONE_NUMBER);
        for (Person person: getListFromNamedQuery("get_all_clients", Person.class)) {
            clientTable.addRow(person.getId()+"", person.getName(), person.getEmail(), person.getPhoneNumber());
        }
        clientTable.print();
    }
}
