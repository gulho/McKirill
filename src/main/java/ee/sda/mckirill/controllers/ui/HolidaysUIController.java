package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.HolidaysStrings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class HolidaysUIController extends AbstractUIController {
    public HolidaysUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        Map<Integer, Consumer> holidaysActionMenu = new HashMap<>();
        holidaysActionMenu.put(1, T -> System.out.println(BaseString.TODO));
        holidaysActionMenu.put(2, T -> System.out.println(BaseString.TODO));
        holidaysActionMenu.put(3, T -> System.out.println(BaseString.TODO));
        holidaysActionMenu.put(4, T -> System.out.println(BaseString.TODO));

        selectMenuAction(HolidaysStrings.HOLIDAYS_MENU_MAIN_ACTION, holidaysActionMenu);
    }
}
