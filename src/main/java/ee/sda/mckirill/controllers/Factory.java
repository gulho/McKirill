package ee.sda.mckirill.controllers;

import ee.sda.mckirill.controllers.ui.*;
import ee.sda.mckirill.controllers.ui.ReviewUIController;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.enums.PersonTypeEnum;

public class Factory {

    public static AbstractUIController getController(Person person, ControllersEnum controller) {
        switch (controller) {
            case MAIN:
                if (person.getPersonType().getType() == PersonTypeEnum.MANAGER) {
                    return new ManagerUIController(person);
                } else if (person.getPersonType().getType() == PersonTypeEnum.WAITER) {
                    return new WaiterUIController(person);
                } else {
                    return new ClientUIController(person);
                }
            case MENU:
                return new MenuUIController(person);
            case TABLE:
                return new TableUIController(person);
            case ORDER:
                return new OrderUIController(person);
            case REVIEW:
                return new ReviewUIController(person);
            case HOLIDAYS:
                return new HolidaysUIController(person);
            default:
                return null;
        }
    }
}
