package ee.sda.mckirill.controllers.types;

import ee.sda.mckirill.controllers.models.AbstractEntityController;
import ee.sda.mckirill.entities.OrderStatus;
import ee.sda.mckirill.enums.OrderStatusEnum;

import java.util.Optional;

public class OrderStatusType extends AbstractEntityController {
    private static OrderStatusType orderStatusType;

    private OrderStatus openStatus;
    private OrderStatus servingStatus;
    private OrderStatus paidStatus;
    private OrderStatus closedStatus;
    private OrderStatus rejectedStatus;

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

    private void saveOrderStatus(OrderStatus orderStatus) {
        session.beginTransaction();
        session.saveOrUpdate(orderStatus);
        session.getTransaction().commit();
    }

    private OrderStatus getByEnum(OrderStatusEnum statusType) {
        Optional<OrderStatus> orderStatus = session.byNaturalId(OrderStatus.class).using("name", statusType).loadOptional();
        if (orderStatus.isEmpty()) {
            saveOrderStatus(new OrderStatus(statusType));
            orderStatus = Optional.of(getByEnum(statusType));
        }
        return orderStatus.get();
    }

}
