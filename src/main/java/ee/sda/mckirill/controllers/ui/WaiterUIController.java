package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.enums.WaiterAction;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.WaiterStrings;
import ee.sda.mckirill.util.ConsoleTablePrint;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class WaiterUIController extends ClientUIController {
    public WaiterUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        switch (person.getPersonType().getType()) {
            case WAITER:
                OrderUIController orderUI = (OrderUIController) Factory.getController(person, ControllersEnum.ORDER);
                MenuUIController menuUI = (MenuUIController) Factory.getController(person, ControllersEnum.MENU);
                Map<Integer, Consumer> waiterActions = new HashMap<>();
                waiterActions.put(1, T -> menuUI.addAdditionalFood(orderUI.selectOrderId(WaiterAction.ADD_NEW_MENU_ITEM)));
                waiterActions.put(2, T -> orderUI.payment(orderUI.selectOrderId(WaiterAction.PAYMENT)));
                selectMenuAction(WaiterStrings.WAITER_MAIN_ACTION, waiterActions);
                break;
            case MANAGER:
                Map<Integer, Consumer> managerWaitersActions = new HashMap<>();
                managerWaitersActions.put(1, T -> System.out.println(BaseString.TODO));
                managerWaitersActions.put(2, T -> System.out.println(BaseString.TODO));
                managerWaitersActions.put(3, T -> System.out.println(BaseString.TODO));
                managerWaitersActions.put(4, T -> System.out.println(BaseString.TODO));

                selectMenuAction(WaiterStrings.MANAGER_WAITER_MAIN_ACTION, managerWaitersActions);
                break;
        }
    }


}
