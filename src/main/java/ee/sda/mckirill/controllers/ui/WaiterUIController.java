package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.WaiterTip;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.enums.WaiterAction;
import ee.sda.mckirill.strings.WaiterStrings;
import ee.sda.mckirill.util.ConsoleTablePrint;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class WaiterUIController extends AbstractUIController {
    public WaiterUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        switch (person.getPersonType().getType()) {
            case WAITER:
                OrderUIController orderUI = (OrderUIController) Factory.getController(person, ControllersEnum.ORDER);
                MenuUIController menuUI = (MenuUIController) Factory.getController(person, ControllersEnum.MENU);
                Map<Integer, Consumer> waiterActions = new HashMap<>();
                waiterActions.put(1, T -> menuUI.addAdditionalFood(orderUI.selectOrderId(WaiterAction.ADD_NEW_MENU_ITEM)));
                waiterActions.put(2, T -> orderUI.payment(orderUI.selectOrderId(WaiterAction.PAYMENT)));
                selectMenuAction(WaiterStrings.WAITER_MAIN_ACTION, waiterActions);
                break;
            case MANAGER:
                Map<Integer, Consumer> managerWaitersActions = new HashMap<>();
                managerWaitersActions.put(1, T -> showWaiterTable());
                managerWaitersActions.put(2, T -> editPerson(new Person()));
                managerWaitersActions.put(3, T -> editPerson(selectWaiter()));
                managerWaitersActions.put(4, T -> removeWaiter(selectWaiter()));

                selectMenuAction(WaiterStrings.MANAGER_WAITER_MAIN_ACTION, managerWaitersActions);
                break;
        }
    }

    private void showWaiterTable() {
        ConsoleTablePrint clientTable = new ConsoleTablePrint();
        clientTable.setShowVerticalLines(true);
        clientTable.setHeaders(
                WaiterStrings.TABLE_ID, WaiterStrings.TABLE_PERSON, WaiterStrings.TABLE_EMAIL, WaiterStrings.TABLE_PHONE_NUMBER,
                WaiterStrings.TABLE_WAITER_TIPS);
        for (Person person : getListFromNamedQuery("get_all_waiters", Person.class)) {
            BigDecimal waiterTIP = BigDecimal.ZERO;
            for (WaiterTip waiterTip : person.getWaiterTipList()) {
                waiterTIP = waiterTIP.add(waiterTip.getTip());
            }
            clientTable.addRow(person.getId() + "", person.getName(), person.getEmail(), person.getPhoneNumber(), waiterTIP.toPlainString());
        }
        clientTable.print();
    }

    private Person selectWaiter() {
        showWaiterTable();
        Function<Integer, Optional<Person>> menuItemFunction = R -> findById(Person.class, R);
        return selectObjectById(WaiterStrings.SELECT_WAITER_ID, WaiterStrings.SELECT_WAITER_WRONG_ID, menuItemFunction);
    }

    private void editPerson(Person person) {
        person.setPersonType(personTypeType.getWaiter());
        person.setName(selectString(WaiterStrings.SELECT_WAITER_NAME, WaiterStrings.SELECT_WAITER_NAME_ERROR, 255));
        person.setEmail(selectString(WaiterStrings.SELECT_WAITER_EMAIL, WaiterStrings.SELECT_WAITER_EMAIL_ERROR, 255));
        person.setPhoneNumber(selectString(WaiterStrings.SELECT_WAITER_PHONE_NUMBER, WaiterStrings.SELECT_WAITER_PHONE_NUMBER_ERROR, 255));
        saveInDatabase(person);
    }

    private void removeWaiter(Person person) {
        deleteFromDatabase(person);
    }

}
