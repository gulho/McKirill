package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.controllers.ApplicationContext;
import ee.sda.mckirill.entities.*;
import ee.sda.mckirill.enums.OrderStatusEnum;
import ee.sda.mckirill.enums.PaymentTypeEnum;
import ee.sda.mckirill.enums.WaiterAction;
import ee.sda.mckirill.strings.MenuStrings;
import ee.sda.mckirill.strings.OrderStrings;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class OrderUIController extends AbstractUIController {

    public OrderUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        switch (person.getPersonType().getType()) {
            case MANAGER:
                Map<Integer, Consumer> orderManagerActions = new HashMap<>();
                orderManagerActions.put(1, T -> showOrdersList(getListFromNamedQuery("get_all_orders", Order.class)));
                orderManagerActions.put(2, T -> editOrderByManager(selectOrderId(null)));
                orderManagerActions.put(3, T -> removeOrder());

                selectMenuAction(OrderStrings.MANAGER_ORDERS_MAIN_ACTION, orderManagerActions);
                break;
            case CLIENT:
                Order order = new Order();
                order.setPerson(person);
                order.setStatus(orderStatus.getOpen());
                while (true) {
                    order.setTimeToOrder(LocalDateTime.of(selectDate(OrderStrings.CLIENT_ORDER_SELECT_DATE), selectTime(OrderStrings.CLIENT_ORDER_SELECT_TIME)));
                    if (checkWorkingTime(order.getTimeToOrder())) {
                        break;
                    } else {
                        System.out.println(OrderStrings.TIME_TO_ORDER_INVALID);
                    }
                }
                order.setPeoples(selectUnsignedInteger(OrderStrings.CLIENT_ORDER_PEOPLES_COUNT_SELECT, OrderStrings.CLIENT_ORDER_PEOPLES_COUNT_INVALID, 15));
                order.setCreateDate(LocalDateTime.now());
                saveInDatabase(order);
                System.out.println(OrderStrings.CLIENT_ORDER_CONFIRM);
        }
    }

    private void editOrderByManager(Order selectOrderId) {
        while (true) {
            selectOrderId.setTimeToOrder(LocalDateTime.of(selectDate(OrderStrings.CLIENT_ORDER_SELECT_DATE), selectTime(OrderStrings.CLIENT_ORDER_SELECT_TIME)));
            if (checkWorkingTime(selectOrderId.getTimeToOrder())) {
                break;
            } else {
                System.out.println(OrderStrings.TIME_TO_ORDER_INVALID);
            }
        }
        selectOrderId.setPeoples(selectUnsignedInteger(OrderStrings.CLIENT_ORDER_PEOPLES_COUNT_SELECT, OrderStrings.CLIENT_ORDER_PEOPLES_COUNT_INVALID, 15));
        selectOrderId.setCreateDate(LocalDateTime.now());
        selectOrderId.setStatus(ApplicationContext.getOrderStatusType().getByType(
                selectEnum(OrderStrings.SELECT_ORDER_STATUS, OrderStrings.SELECT_ORDER_STATUS_WRONG, OrderStatusEnum.class)));
        saveInDatabase(selectOrderId);
    }

    private boolean checkWorkingTime(LocalDateTime timeToOrder) {
        boolean isRestaurantWorking = false;
        if (timeToOrder.isAfter(LocalDateTime.now())) {
            if (timeToOrder.getHour() >= ApplicationContext.getWorkingTime()[0] && timeToOrder.getHour() < ApplicationContext.getWorkingTime()[1]) {
                if ((Long) getListFromNamedQueryWithParameters("check_date_is_holiday", Object.class, Map.of("date", timeToOrder.toLocalDate())).get(0) == 0) {
                    isRestaurantWorking = true;
                }
            }
        }
        return isRestaurantWorking;
    }

    private void showOrdersList(List<Order> orderList) {
        System.out.println("+----+------------------------------+----------+----------+----------+");
        System.out.printf("|%4s|%30s|%10s|%10s|%10s|%n",
                OrderStrings.TABLE_ID, OrderStrings.TABLE_PERSON, OrderStrings.TABLE_PEOPLES_COUNT, OrderStrings.TABLE_STATUS, OrderStrings.TABLE_TABLE_ID);
        System.out.println("+----+------------------------------+----------+----------+----------+");
        for (Order order : orderList) {
            String tableIdString = "None";
            if (order.getTable() != null) {
                tableIdString = order.getTable().getId() + "";
            }
            System.out.printf("|%4d|%30s|%10d|%10s|%10s|%n",
                    order.getId(), order.getPerson().getName(), order.getPeoples(), order.getStatus().getName(), tableIdString);
            if (order.getOrderedMenuItems().isEmpty() == false) {
                System.out.println("+----+------------------------------+----------+----------+----------+");
                System.out.printf("|     %30s|%10s|%10s|%10s|%n",
                        MenuStrings.TABLE_MENU_ITEM_NAME,
                        MenuStrings.TABLE_MENU_ITEM_TYPE,
                        OrderStrings.TABLE_QUANTITY,
                        MenuStrings.TABLE_MENU_ITEM_PRICE);
                System.out.println("+-----------------------------------+----------+----------+----------+");
                for (OrderedMenuItem orderedMenuItem : order.getOrderedMenuItems()) {
                    System.out.printf("|     %30s|%10s|%10d|%10s|%n",
                            orderedMenuItem.getMenuItem().getName(),
                            orderedMenuItem.getMenuItem().getType().toString(),
                            orderedMenuItem.getQuantity(),
                            orderedMenuItem.getSum().toString() + OrderStrings.EURO);
                }
                System.out.println("+-----------------------------------+----------+----------+----------+");
            }
        }
        System.out.println("+----+------------------------------+----------+----------+----------+");
    }

    public Order selectOrderId(WaiterAction waiterAction) {
        List<Order> selectOrders;
        if (waiterAction == null) {
            selectOrders = getListFromNamedQuery("get_all_orders", Order.class);
        } else if (waiterAction == WaiterAction.ADD_NEW_MENU_ITEM) {
            selectOrders = getListFromNamedQuery("get_all_orders_open_serving", Order.class);
        } else {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name", OrderStatusEnum.SERVING);
            selectOrders = getListFromNamedQueryWithParameters("get_all_order_by_status", Order.class, parameters);
        }
        showOrdersList(selectOrders);
        Function<Integer, Optional<Order>> function = T -> findById(Order.class, T);
        return selectObjectById(OrderStrings.SELECT_ORDER, OrderStrings.SELECT_ORDER_WRONG_ID, function);
    }

    public void payment(Order orderToUpdate) {
        System.out.println(OrderStrings.WAITER_PAYMENT);
        endOfUIInteraction();
        orderToUpdate.setPaymentType(
                ApplicationContext.getPaymentTypeType().getByType(
                        selectEnum(OrderStrings.SELECT_PAYMENT_TYPE, OrderStrings.SELECT_PAYMENT_TYPE_WRONG, PaymentTypeEnum.class))
        );
        orderToUpdate.setTotalSum(selectPaymentAmount(orderToUpdate));
        orderToUpdate.setStatus(orderStatus.getPaid());
        if (orderToUpdate.getTable() != null) {
            Table ordersTable = orderToUpdate.getTable();
            ordersTable.setIs_available(true);
            saveInDatabase(ordersTable);
        }
        saveInDatabase(orderToUpdate);

        WaiterTip waiterTip = new WaiterTip(
                person,
                selectBigDecimal(OrderStrings.SELECT_AMOUNT_OF_TIP, OrderStrings.SELECT_AMOUNT_OF_TIP_CANT_BE_NEGATIVE),
                orderToUpdate);
        if (waiterTip.getTip().compareTo(BigDecimal.ZERO) > 0) {
            saveInDatabase(waiterTip);
        }
        System.out.println(OrderStrings.PAYMENT_TOTAL + orderToUpdate.getTotalSum().toPlainString());
        System.out.println(OrderStrings.PAYMENT_TOTAL_CHANGE + (orderToUpdate.getTotalSum().subtract(orderTotalAmount(orderToUpdate)).toPlainString()));
        System.out.println(OrderStrings.PAYMENT_TOTAL_TIP + waiterTip.getTip().toPlainString());
    }

    private BigDecimal selectPaymentAmount(Order orderToUpdate) {
        while (true) {
            System.out.println(OrderStrings.SELECT_SET_AMOUNT + "(" + orderTotalAmount(orderToUpdate).toPlainString() + ")");
            String scannerAmount = scanner.nextLine().toUpperCase();
            if (scannerAmount.equals("") || scannerAmount.equals("Y")) {
                return orderTotalAmount(orderToUpdate);
            }
            try {
                BigDecimal returnBigDecimal = new BigDecimal(scannerAmount);
                if (returnBigDecimal.compareTo(orderTotalAmount(orderToUpdate)) >= 0) {
                    return returnBigDecimal;
                } else {
                    System.out.println(OrderStrings.SELECT_PAYMENT_AMOUNT_NOT_ENOUGH);
                }
            } catch (NumberFormatException e) {
                System.out.println(OrderStrings.SELECT_PAYMENT_WRONG);
            }
        }
    }

    private BigDecimal orderTotalAmount(Order orderToUpdate) {
        BigDecimal orderTotalAmount = BigDecimal.ZERO;
        for (OrderedMenuItem orderedMenuItem : orderToUpdate.getOrderedMenuItems()) {
            orderTotalAmount = orderTotalAmount.add(orderedMenuItem.getSum());
        }
        return orderTotalAmount;
    }

    private void removeOrder() {
        Order orderToRemove = selectOrderId(null);
        deleteFromDatabase(orderToRemove);
    }
}
