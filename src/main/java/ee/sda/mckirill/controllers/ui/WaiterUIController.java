package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.controllers.entity.OrderController;
import ee.sda.mckirill.entities.Order;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllrsEnum;
import ee.sda.mckirill.strings.OrderStrings;
import ee.sda.mckirill.strings.WaiterUIStrings;

public class WaiterUIController extends AbstractUIController {
    public WaiterUIController(Person person) {
        super(person);
    }

    @Override
    public void start() throws Exception {
        Order orderToUpdate;
        OrderUIController OrderUI = (OrderUIController) Factory.getController(person, ControllrsEnum.ORDER);
        MenuUIController MenuUI = (MenuUIController) Factory.getController(person, ControllrsEnum.MENU);
        while(true) {
            System.out.println();
            OrderUI.showWaiterOrdersList();
            System.out.println(WaiterUIStrings.WAITER_MAIN_ACTION);
            switch (scanner.nextLine()) {
                case "1":
                    orderToUpdate = OrderUI.selectOrderId();
                    break;
                case "2":
                    orderToUpdate = OrderUI.selectOrderId();
                    break;
                case "exit":
                case "e":
                    return;
            }
        }
    }
}
