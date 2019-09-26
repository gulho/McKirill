package ee.sda.mckirill.controllers.types;

import ee.sda.mckirill.controllers.DatabaseController;
import ee.sda.mckirill.entities.OrderStatus;
import ee.sda.mckirill.enums.OrderStatusEnum;

import java.util.Optional;

public class OrderStatusType extends DatabaseController {
    private static OrderStatusType orderStatusType;

    private OrderStatus openStatus;
    private OrderStatus servingStatus;
    private OrderStatus paidStatus;
    private OrderStatus closedStatus;
    private OrderStatus rejectedStatus;

    private DatabaseController databaseController = DatabaseController.of();

    private OrderStatusType() {
    }

    public static OrderStatusType of() {
        if (orderStatusType == null) {
            orderStatusType = new OrderStatusType();
        }
        return orderStatusType;
    }

    public OrderStatus getOpen() {
        if (openStatus == null) {
            openStatus = getByType(OrderStatusEnum.OPEN);
        }
        return openStatus;
    }

    public OrderStatus getServing() {
        if (servingStatus == null) {
            servingStatus = getByType(OrderStatusEnum.SERVING);
        }
        return servingStatus;
    }

    public OrderStatus getPaid() {
        if (paidStatus == null) {
            paidStatus = getByType(OrderStatusEnum.PAID);
        }
        return paidStatus;
    }

    public OrderStatus getClosed() {
        if (closedStatus == null) {
            closedStatus = getByType(OrderStatusEnum.CLOSED);
        }
        return closedStatus;
    }

    public OrderStatus getRejected() {
        if (rejectedStatus == null) {
            rejectedStatus = getByType(OrderStatusEnum.REJECTED);
        }
        return rejectedStatus;
    }

    public OrderStatus getByType(OrderStatusEnum statusType) {
        Optional<OrderStatus> orderStatus = findByNaturalId(OrderStatus.class, "name", statusType);
        if (orderStatus.isEmpty()) {
            saveInDatabase(new OrderStatus(statusType));
            orderStatus = Optional.of(getByType(statusType));
        }
        return orderStatus.get();
    }

}
