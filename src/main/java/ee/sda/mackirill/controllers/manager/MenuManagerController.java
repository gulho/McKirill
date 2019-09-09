package ee.sda.mackirill.controllers.manager;

import ee.sda.mackirill.controllers.AbstractController;
import ee.sda.mackirill.entities.Menu;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.strings.BaseString;
import ee.sda.mackirill.strings.ManagerUIStrings;

public class MenuManagerController extends AbstractController {
    public MenuManagerController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        while (true) {
            System.out.println(ManagerUIStrings.MANAGER_MENU_MAIN_ACTION);
            switch (scanner.nextLine()) {
                case "1":
                    break;
                case "2":
                    editMenu(new Menu());
                    break;
                default:
                    System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }

    private void editMenu(Menu menu) {
        while (true) {
            System.out.println(ManagerUIStrings.MENU_SET_NAME);
            menu.getItem().setName(scanner.nextLine());
            if(!menu.getItem().getName().isEmpty()) {
                break;
            } else {
                System.out.println(ManagerUIStrings.MENU_EMPTY_NAME);
            }
        }
    }
}
