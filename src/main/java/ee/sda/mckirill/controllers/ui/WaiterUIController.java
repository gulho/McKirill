package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllrsEnum;

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
