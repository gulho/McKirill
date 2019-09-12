package ee.sda.mackirill.controllers.ui;

import ee.sda.mackirill.controllers.Factory;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.enums.ControllrsEnum;

public class WaiterUIController extends AbstractUIController {
    public WaiterUIController(Person person) {
        super(person);
    }

    @Override
    public void start() throws Exception {
        while(true) {
            System.out.println();
            Factory.getController(person, ControllrsEnum.ORDER).start();
        }
    }
}
