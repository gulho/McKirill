package ee.sda.mackirill.controllers;

import ee.sda.mackirill.controllers.client.ClientController;
import ee.sda.mackirill.controllers.client.ClientReviewController;
import ee.sda.mackirill.controllers.manager.ManagerController;
import ee.sda.mackirill.controllers.manager.MenuManagerController;
import ee.sda.mackirill.controllers.manager.OrderManageController;
import ee.sda.mackirill.controllers.manager.TableMangerController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.enums.ControllrsEnum;
import ee.sda.mackirill.enums.PersonTypeEnum;

public class Factory {

    public static AbstractController getController(Person person, ControllrsEnum controller) throws Exception {
        AbstractController returnController;
        switch (controller) {
            case MAIN:
                if(person.getPersonType().getType() == PersonTypeEnum.MANAGER) {
                    returnController = new ManagerController(person);
                } else {
                    returnController = new ClientController(person);
                }
                break;
            case MENU:
                returnController = new MenuManagerController(person);
                break;
            case TABLE:
                returnController = new TableMangerController(person);
                break;
            case ORDER:
                returnController = new OrderManageController(person);
                break;
            /*case CLIENT_ORDER:
                //TODO
                //returnController =
                break;*/
            case CLIENT_REVIEW:
                returnController = new ClientReviewController(person);
                break;
            default:
                throw new Exception("Controller is not exist in factory method");
        }
        return returnController;
    }
}
