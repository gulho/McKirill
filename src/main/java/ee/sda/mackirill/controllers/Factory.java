package ee.sda.mackirill.controllers;

import ee.sda.mackirill.controllers.UI.*;
import ee.sda.mackirill.controllers.UI.ReviewUIController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.enums.ControllrsEnum;
import ee.sda.mackirill.enums.PersonTypeEnum;

public class Factory {

    public static AbstractUIController getController(Person person, ControllrsEnum controller) throws Exception {
        AbstractUIController returnController;
        switch (controller) {
            case MAIN:
                if(person.getPersonType().getType() == PersonTypeEnum.MANAGER) {
                    returnController = new ManagerUIController(person);
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
            /*case CLIENT_ORDER:
                //TODO
                //returnController =
                break;*/
            case CLIENT_REVIEW:
                returnController = new ReviewUIController(person);
                break;
            default:
                throw new Exception("Controller is not exist in factory method");
        }
        return returnController;
    }
}
