package ee.sda.mackirill.controllers.UI;

import ee.sda.mackirill.controllers.UI.AbstractUIController;
import ee.sda.mackirill.entities.OrderStatus;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.strings.BaseString;
import ee.sda.mackirill.strings.ClientUIStrings;
import ee.sda.mackirill.strings.ManagerUIStrings;
import ee.sda.mackirill.strings.OrderStrings;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
                while (true) {
                    System.out.println(OrderStrings.CLIENT_ORDER_BOOKING_DATE_SELECT);
                    Optional<LocalDate> orderDate = validateDate(scanner.nextLine());
                    if(orderDate.isPresent()) {
                        System.out.println(orderDate.get().toString());
                        return;
                    }
                }
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
}
