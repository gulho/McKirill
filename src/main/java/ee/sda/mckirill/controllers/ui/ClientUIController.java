package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.strings.ClientUIStrings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ClientUIController extends AbstractUIController {
    public ClientUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        Map<Integer, Consumer> clientActions = new HashMap<>();
        clientActions.put(1, T -> Factory.getController(person, ControllersEnum.ORDER).start());
        clientActions.put(2, T -> Factory.getController(person, ControllersEnum.REVIEW).start());

        selectMenuAction(ClientUIStrings.CLIENT_MAIN_ACTION, clientActions);
    }
}
