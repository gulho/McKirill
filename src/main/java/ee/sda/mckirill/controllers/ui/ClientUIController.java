package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.strings.ClientStrings;
import ee.sda.mckirill.util.ConsoleTablePrint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

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
                managerClientAction.put(1, T -> showClientTable());
                managerClientAction.put(2, T -> editPerson(selectClient()));
                managerClientAction.put(3, T -> removeClient(selectClient()));

                selectMenuAction(ClientStrings.MANAGER_CLIENT_MAIN_ACTION, managerClientAction);
                break;
        }
    }

    private void showClientTable() {
        ConsoleTablePrint clientTable = new ConsoleTablePrint();
        clientTable.setShowVerticalLines(true);
        clientTable.setHeaders(ClientStrings.TABLE_ID, ClientStrings.TABLE_PERSON, ClientStrings.TABLE_EMAIL, ClientStrings.TABLE_PHONE_NUMBER);
        for (Person person : getListFromNamedQuery("get_all_clients", Person.class)) {
            clientTable.addRow(person.getId() + "", person.getName(), person.getEmail(), person.getPhoneNumber());
        }
        clientTable.print();
    }

    private Person selectClient() {
        showClientTable();
        Function<Integer, Optional<Person>> menuItemFunction = R -> findById(Person.class, R);
        return selectObjectById(ClientStrings.SELECT_CLIENT_ID, ClientStrings.SELECT_CLIENT_WRONG_ID, menuItemFunction);
    }

    private void editPerson(Person person) {
        person.setName(selectString(ClientStrings.SELECT_CLIENT_NAME, ClientStrings.SELECT_CLIENT_NAME_ERROR, 255));
        person.setEmail(selectString(ClientStrings.SELECT_CLIENT_EMAIL, ClientStrings.SELECT_CLIENT_EMAIL_ERROR, 255));
        person.setPhoneNumber(selectString(ClientStrings.SELECT_CLIENT_PHONE_NUMBER, ClientStrings.SELECT_CLIENT_PHONE_NUMBER_ERROR, 255));
        saveInDatabase(person);
    }

    private void removeClient(Person person) {
        deleteFromDatabase(person);
    }
}
