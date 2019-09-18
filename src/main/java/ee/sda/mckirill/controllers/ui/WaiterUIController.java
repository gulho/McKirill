package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Order;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.enums.OrderStatusEnum;
import ee.sda.mckirill.enums.WaiterAction;
import ee.sda.mckirill.strings.WaiterUIStrings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class WaiterUIController extends AbstractUIController {
    public WaiterUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        OrderUIController orderUI = (OrderUIController) Factory.getController(person, ControllersEnum.ORDER);
        MenuUIController menuUI = (MenuUIController) Factory.getController(person, ControllersEnum.MENU);
        Map<Integer, Consumer> waiterActions = new HashMap<>();
        waiterActions.put(1, T -> menuUI.addAdditionalFood(orderUI.selectOrderId(WaiterAction.ADD_NEW_MENU_ITEM)));
        waiterActions.put(2, T -> orderUI.payment(orderUI.selectOrderId(WaiterAction.PAYMENT)));

        selectMenuAction(WaiterUIStrings.WAITER_MAIN_ACTION, waiterActions);
    }
}
