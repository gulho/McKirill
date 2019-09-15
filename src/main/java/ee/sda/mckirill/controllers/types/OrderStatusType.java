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
            openStatus = getByEnum(OrderStatusEnum.OPEN);
        }
        return openStatus;
    }

    public OrderStatus getServing() {
        if (servingStatus == null) {
            servingStatus = getByEnum(OrderStatusEnum.SERVING);
        }
        return servingStatus;
    }

    public OrderStatus getPaid() {
        if (paidStatus == null) {
            paidStatus = getByEnum(OrderStatusEnum.PAID);
        }
        return paidStatus;
    }

    public OrderStatus getClosed() {
        if (closedStatus == null) {
            closedStatus = getByEnum(OrderStatusEnum.CLOSED);
        }
        return closedStatus;
    }

    public OrderStatus getRejected() {
        if (rejectedStatus == null) {
            rejectedStatus = getByEnum(OrderStatusEnum.REJECTED);
        }
        return rejectedStatus;
    }

    private OrderStatus getByEnum(OrderStatusEnum statusType) {
        Optional<OrderStatus> orderStatus = findByNaturalId(OrderStatus.class, "name", statusType);
        if (orderStatus.isEmpty()) {
            save(new OrderStatus(statusType));
            orderStatus = Optional.of(getByEnum(statusType));
        }
        return orderStatus.get();
    }

}
