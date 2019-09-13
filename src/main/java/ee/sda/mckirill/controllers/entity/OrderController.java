package ee.sda.mckirill.controllers.entity;

import ee.sda.mckirill.entities.Order;

import java.util.List;
import java.util.Optional;

public class OrderController extends AbstractEntityController {
    public static void save(Order order) {
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
    }

    public static List<Order> getList() {
        return session.createNamedQuery("get_all_orders").getResultList();
    }

    public static Optional<Order> findById(int id) {
        return Optional.ofNullable(session.get(Order.class, id));
    }
}
