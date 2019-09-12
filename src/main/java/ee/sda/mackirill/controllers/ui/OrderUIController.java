package ee.sda.mackirill.controllers.ui;

import ee.sda.mackirill.controllers.entity.OrderController;
import ee.sda.mackirill.controllers.types.OrderStatusType;
import ee.sda.mackirill.entities.Order;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.strings.BaseString;
import ee.sda.mackirill.strings.OrderStrings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

public class OrderUIController extends AbstractUIController {
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
                        System.out.println(orderDate.get().toString());
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
                        System.out.println(orderTime);
                        break;
                    } else {
                        System.out.println(OrderStrings.CLIENT_ORDER_TIME_IN_INVALID);
                    }
                }

                //TODO: Add check is time is valid
                Order order = new Order();
                order.setPerson(person);
                order.setStatus(OrderStatusType.getOpen());
                order.setTimeToOrder(LocalDateTime.of(orderDate.get(), orderTime.get()));

                while (true) {
                    System.out.println(OrderStrings.CLIENT_ORDER_PEOPLES_COUNT_SELECT);
                    order.setPeoples(scanner.nextInt());
                    if (order.getPeoples() > 0 && order.getPeoples() <= 15) {
                        break;
                    } else {
                        System.out.println(OrderStrings.CLIENT_ORDER_PEOPLES_COUNT_INVALID);
                    }
                }
                //TODO:Add pre-order food selection
                order.setCreateDate(LocalDateTime.now());
                OrderController.save(order);
                System.out.println(OrderStrings.CLIENT_ORDER_CONFIRM);
        }
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
}
