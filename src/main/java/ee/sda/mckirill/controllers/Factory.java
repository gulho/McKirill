package ee.sda.mckirill.controllers;

import ee.sda.mckirill.controllers.ui.*;
import ee.sda.mckirill.controllers.ui.ReviewUIController;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.enums.PersonTypeEnum;

public class Factory {

    public static AbstractUIController getController(Person person, ControllersEnum controller) {
        AbstractUIController returnController = null;
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
        }
        return returnController;
    }
}
