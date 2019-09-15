package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.ManagerUIStrings;

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
        managerActions.put(3, T -> System.out.println(BaseString.TODO)); //TODO
        managerActions.put(4, T -> Factory.getController(person, ControllersEnum.TABLE).start());
        managerActions.put(5, T -> System.out.println(BaseString.TODO)); //TODO
        managerActions.put(6, T -> System.out.println(BaseString.TODO)); //TODO
        managerActions.put(7, T -> System.out.println(BaseString.TODO)); //TODO

        selectMenuAction(ManagerUIStrings.MANAGER_MAIN_ACTION, managerActions);
    }
}
