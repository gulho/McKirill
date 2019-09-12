package ee.sda.mackirill.controllers.entity;

import ee.sda.mackirill.entities.Order;

import java.util.List;

public class OrderController extends AbstractEntityController {
    public static void save(Order order) {
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
    }

    public static List<Order> getList() {
        return session.createNamedQuery("get_all_orders").getResultList();
    }
}
