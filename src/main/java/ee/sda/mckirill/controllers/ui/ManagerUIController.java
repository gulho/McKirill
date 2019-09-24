package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.ManagerStrings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ManagerUIController extends AbstractUIController {

    public ManagerUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        Map<Integer, Consumer> managerActions = new HashMap<>();
        managerActions.put(1, T -> Factory.getController(person, ControllersEnum.ORDER).start());
        managerActions.put(2, T -> Factory.getController(person, ControllersEnum.MENU).start());
        managerActions.put(3, T -> Factory.getController(person, ControllersEnum.CLIENT).start());
        managerActions.put(4, T -> Factory.getController(person, ControllersEnum.TABLE).start());
        managerActions.put(5, T -> Factory.getController(person, ControllersEnum.WAITER).start());
        managerActions.put(6, T -> Factory.getController(person, ControllersEnum.HOLIDAYS).start());
        managerActions.put(7, T -> Factory.getController(person, ControllersEnum.STATISTICS).start());
        managerActions.put(8, T -> Factory.getController(person, ControllersEnum.REVIEW).start());

        selectMenuAction(ManagerStrings.MANAGER_MAIN_ACTION, managerActions);
    }
}
