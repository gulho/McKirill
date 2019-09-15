package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.ApplicationContext;
import ee.sda.mckirill.controllers.models.OrderController;
import ee.sda.mckirill.controllers.models.PersonController;
import ee.sda.mckirill.entities.*;
import ee.sda.mckirill.enums.PaymentTypeEnum;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.strings.MenuStrings;
import ee.sda.mckirill.strings.OrderStrings;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class OrderUIController extends AbstractUIController {
    private OrderController orderController = OrderController.of();
    public OrderUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        switch (person.getPersonType().getType()) {
            case MANAGER:
                while (true) {
                    System.out.println(OrderStrings.MANAGER_ORDERS_MAIN_ACTION);
                    switch (scanner.nextLine()) {
                        case "1":
                            showOrdersList(orderController.getList());
                            //endOfUIIntercation();
                            break;
                        case "0":
                            return;
                        default:
                            System.out.println(BaseString.WRONG_COMMAND);
                    }
                }
            case CLIENT:
                Optional<LocalDate> orderDate;
                while (true) {
                    System.out.println(OrderStrings.CLIENT_ORDER_BOOKING_DATE_SELECT);
                    orderDate = validateDate(scanner.nextLine());
                    if (orderDate.isPresent()) {
                        break;
                    } else {
                        System.out.println(OrderStrings.CLIENT_ORDER_DATE_IN_INVALID);
                    }
                }

                Optional<LocalTime> orderTime;
                while (true) {
                    System.out.println(OrderStrings.CLIENT_ORDER_TIME_SELECT);
                    orderTime = validateTime(scanner.nextLine());
                    if (orderTime.isPresent()) {
                        break;
                    } else {
                        System.out.println(OrderStrings.CLIENT_ORDER_TIME_IN_INVALID);
                    }
                }

                //TODO: Add check is time is valid
                Order order = new Order();
                order.setPerson(person);
                order.setStatus(orderStatus.getOpen());
                order.setTimeToOrder(LocalDateTime.of(orderDate.get(), orderTime.get()));
                order.setPeoples(selectUnsignedInteger(OrderStrings.CLIENT_ORDER_PEOPLES_COUNT_SELECT, OrderStrings.CLIENT_ORDER_PEOPLES_COUNT_INVALID, 15));
                //TODO:Add pre-order food selection
                order.setCreateDate(LocalDateTime.now());
                orderController.save(order);
                System.out.println(OrderStrings.CLIENT_ORDER_CONFIRM);
        }
    }

    public void showWaiterOrdersList() {
        showOrdersList(orderController.getList());
    }

    private void showOrdersList(List<Order> orderList) {
        System.out.printf("%10s%45s%20s%20s%n",
                OrderStrings.TABLE_ID,OrderStrings.TABLE_PERSON,OrderStrings.TABLE_PEOPLES_COUNT,OrderStrings.TABLE_STATUS);

        for (Order order: orderList) {
            System.out.printf("%4d%30s%10d%10s%n",
                    order.getId(),order.getPerson().getName(),order.getPeoples(),order.getStatus().getName());
            if (order.getOrderedMenuItems().isEmpty() == false) {
                System.out.printf("      %42s%19s%22s%18s%n",
                        MenuStrings.TABLE_MENU_ITEM_NAME,
                        MenuStrings.TABLE_MENU_ITEM_TYPE,
                        OrderStrings.TABLE_QUANTITY,
                        MenuStrings.TABLE_MENU_ITEM_PRICE);
                for (OrderedMenuItem orderedMenuItem: order.getOrderedMenuItems()) {
                    System.out.printf("      %30s%10s%7d%10s%n",
                            orderedMenuItem.getMenuItem().getName(),
                            orderedMenuItem.getMenuItem().getType().toString(),
                            orderedMenuItem.getQuantity(),
                            orderedMenuItem.getSum().toString() + OrderStrings.EURO);
                }
            }
        }
    }

    public Order selectOrderId() {
        Optional<Order> returnOrder;
        while (true) {
            System.out.println(OrderStrings.SELECT_ORDER);
            returnOrder = orderController.findById(scanner.nextInt());
            if(returnOrder.isEmpty()){
                System.out.println(OrderStrings.SELECT_ORDER_WRONG_ID);
            } else {
                break;
            }
        }
        return returnOrder.get();
    }

    private Optional<LocalDate> validateDate(String dateStr) {
        LocalDate orderDate = null;
        try {
            String[] dateStrings = dateStr.split("\\.");
            if (dateStrings.length == 3) {
                orderDate = LocalDate.of(
                        Integer.valueOf(dateStrings[2]),
                        Integer.valueOf(dateStrings[1]),
                        Integer.valueOf(dateStrings[0]));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(orderDate);
    }

    private Optional<LocalTime> validateTime(String timeStr) {
        LocalTime orderTime = null;
        try {
            String[] timeStrings = timeStr.split("\\.");
            if (timeStrings.length == 2) {
                orderTime = LocalTime.of(
                        Integer.valueOf(timeStrings[0]),
                        Integer.valueOf(timeStrings[1]));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(orderTime);
    }

    public void payment(Order orderToUpdate) {
        System.out.println(OrderStrings.WAITER_PAYMENT);
        endOfUIInteraction();
        orderToUpdate.setPaymentType(
                ApplicationContext.getPaymentTypeType().getByType(
                        selectEnum(OrderStrings.SELECT_PAYMENT_TYPE,OrderStrings.SELECT_PAYMENT_TYPE_WRONG,PaymentTypeEnum.class))
        );
        orderToUpdate.setTotalSum(selectPaymentAmount(orderToUpdate));
        orderToUpdate.setStatus(orderStatus.getPaid());
        orderController.save(orderToUpdate);

        WaiterTip waiterTip = new WaiterTip(
                person,
                selectBigDecimal(OrderStrings.SELECT_AMOUNT_OF_TIP,OrderStrings.SELECT_AMOUNT_OF_TIP_CANT_BE_NEGATIVE),
                orderToUpdate);
        if (waiterTip.getTip().compareTo(BigDecimal.ZERO) > 0) {
            PersonController.of().saveWaiterTip(waiterTip);
        }
        System.out.println(OrderStrings.PAYMENT_TOTAL + orderToUpdate.getTotalSum().toPlainString());
        System.out.println(OrderStrings.PAYMENT_TOTAL_CHANGE + (orderToUpdate.getTotalSum().subtract(orderTotalAmount(orderToUpdate)).toPlainString()));
        System.out.println(OrderStrings.PAYMENT_TOTAL_TIP + waiterTip.getTip().toPlainString());
    }

    private BigDecimal selectPaymentAmount(Order orderToUpdate) {
        while (true) {
            System.out.println(OrderStrings.SELECT_SET_AMOUNT + "(" + orderTotalAmount(orderToUpdate).toPlainString() + ")");
            String scannerAmount = scanner.nextLine().toUpperCase();
            if(scannerAmount.equals("") || scannerAmount.equals("Y")) {
                return orderTotalAmount(orderToUpdate);
            }
            try{
                BigDecimal returnBigDecimal = new BigDecimal(scannerAmount);
                if(returnBigDecimal.compareTo(orderTotalAmount(orderToUpdate)) >= 0) {
                    return returnBigDecimal;
                }else {
                    System.out.println(OrderStrings.SELECT_PAYMENT_AMOUNT_NOT_ENOUGH);
                }
            } catch (NumberFormatException e) {
                System.out.println(OrderStrings.SELECT_PAYMENT_WRONG);
            }
        }
    }

    private BigDecimal orderTotalAmount(Order orderToUpdate) {
        BigDecimal orderTotalAmount = BigDecimal.ZERO;
        for(OrderedMenuItem orderedMenuItem: orderToUpdate.getOrderedMenuItems()) {
            orderTotalAmount = orderTotalAmount.add(orderedMenuItem.getSum());
        }
        return orderTotalAmount;
    }
}
