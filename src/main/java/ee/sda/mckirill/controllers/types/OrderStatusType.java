package ee.sda.mckirill.controllers.types;

import ee.sda.mckirill.controllers.entity.AbstractEntityController;
import ee.sda.mckirill.entities.OrderStatus;
import ee.sda.mckirill.enums.OrderStatusEnum;

public class OrderStatusType extends AbstractEntityController {
    private static OrderStatus openStatus;
    private static OrderStatus servingStatus;
    private static OrderStatus paidStatus;
    private static OrderStatus closedStatus;
    private static OrderStatus rejectedStatus;

    public static OrderStatus getOpen() {
        if(openStatus == null) {
            openStatus = getByEnum(OrderStatusEnum.OPEN);
        }
        return openStatus;
    }
    public static OrderStatus getServing() {
        if(servingStatus == null) {
            servingStatus = getByEnum(OrderStatusEnum.SERVING);
        }
        return servingStatus;
    }
    public static OrderStatus getPaid() {
        if(paidStatus == null) {
            paidStatus = getByEnum(OrderStatusEnum.PAID);
        }
        return paidStatus;
    }
    public static OrderStatus getClosed() {
        if(closedStatus == null) {
            closedStatus = getByEnum(OrderStatusEnum.CLOSED);
        }
        return closedStatus;
    }
    public static OrderStatus getRejected() {
        if(rejectedStatus == null) {
            rejectedStatus = getByEnum(OrderStatusEnum.REJECTED);
        }
        return rejectedStatus;
    }

    public static void saveOrderStatus(OrderStatus orderStatus) {
        session.beginTransaction();
        session.saveOrUpdate(orderStatus);
        session.getTransaction().commit();
    }

    private static OrderStatus getByEnum(OrderStatusEnum statusType) {
        return session.byNaturalId(OrderStatus.class).using("name", statusType).load();
    }
}
