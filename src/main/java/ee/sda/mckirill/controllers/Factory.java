package ee.sda.mckirill.controllers;

import ee.sda.mckirill.controllers.ui.*;
import ee.sda.mckirill.controllers.ui.ReviewUIController;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.PersonType;
import ee.sda.mckirill.enums.ControllrsEnum;
import ee.sda.mckirill.enums.PersonTypeEnum;

public class Factory {

    public static AbstractUIController getController(Person person, ControllrsEnum controller) throws Exception {
        AbstractUIController returnController;
        switch (controller) {
            case MAIN:
                if (person.getPersonType().getType() == PersonTypeEnum.MANAGER) {
                    returnController = new ManagerUIController(person);
                } else if (person.getPersonType().getType() == PersonTypeEnum.WAITER) {
                    returnController = new WaiterUIController(person);
                } else {
                    returnController = new ClientUIController(person);
                }
                break;
            case MENU:
                returnController = new MenuUIController(person);
                break;
            case TABLE:
                returnController = new TableUIController(person);
                break;
            case ORDER:
                returnController = new OrderUIController(person);
                break;
            case REVIEW:
                returnController = new ReviewUIController(person);
                break;
            default:
                throw new Exception("Controller is not exist in factory method");
        }
        return returnController;
    }
}
