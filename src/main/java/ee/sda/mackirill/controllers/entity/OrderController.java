package ee.sda.mackirill.controllers.entity;

import ee.sda.mackirill.entities.Order;

public class OrderController extends AbstractEntityController {
    public static void save(Order order) {
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
    }
}
