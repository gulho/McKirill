package ee.sda.mackirill.controllers.manager;

import ee.sda.mackirill.controllers.AbstractController;
import ee.sda.mackirill.entities.MenuItem;
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
                    editMenu(new MenuItem());
                    break;
                default:
                    System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }

    private void editMenu(MenuItem menuItem) {
        while (true) {
            System.out.println(ManagerUIStrings.MENU_SET_NAME);
            menuItem.setName(scanner.nextLine());
            if(!menuItem.getName().isEmpty()) {
                break;
            } else {
                System.out.println(ManagerUIStrings.MENU_EMPTY_NAME);
            }
        }
    }
}
