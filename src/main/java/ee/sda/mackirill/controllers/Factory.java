package ee.sda.mackirill.controllers;

import ee.sda.mackirill.controllers.client.ClientController;
import ee.sda.mackirill.controllers.manager.ManagerController;
import ee.sda.mackirill.entities.Person;

public class Factory {
    public static AbstractController getController(Person person) {
        AbstractController returnController;
        switch(person.getPersonType().getType()) {
            case MANAGER:
                returnController = new ManagerController(person);
                break;
            case CLIENT:
            default:
                returnController = new ClientController(person);
        }
        return returnController;
    }
}
