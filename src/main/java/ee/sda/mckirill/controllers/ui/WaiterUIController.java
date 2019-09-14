package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Order;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.strings.WaiterUIStrings;

public class WaiterUIController extends AbstractUIController {
    public WaiterUIController(Person person) {
        super(person);
    }

    @Override
    public void start() throws Exception {
        Order orderToUpdate;
        OrderUIController orderUI = (OrderUIController) Factory.getController(person, ControllersEnum.ORDER);
        MenuUIController menuUI = (MenuUIController) Factory.getController(person, ControllersEnum.MENU);
        while(true) {
            System.out.println();
            orderUI.showWaiterOrdersList();
            System.out.println(WaiterUIStrings.WAITER_MAIN_ACTION);
            switch (scanner.nextLine()) {
                case "1":
                    orderToUpdate = orderUI.selectOrderId();
                    menuUI.addAdditionalFood(orderToUpdate);
                    break;
                case "2":
                    orderToUpdate = orderUI.selectOrderId();
                    orderUI.payment(orderToUpdate);
                    break;
                case "exit":
                case "e":
                    return;
            }
        }
    }
}
