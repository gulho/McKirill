package ee.sda.mckirill.controllers.models;

import ee.sda.mckirill.entities.Order;

import java.util.List;
import java.util.Optional;

public class OrderController extends AbstractEntityController {
    private static OrderController orderController;
    private OrderController() {
    }

    public static OrderController of() {
        if(orderController == null) {
            orderController = new OrderController();
        }
        return orderController;
    }

    public void save(Order order) {
        session.beginTransaction();
        session.save(order);
        session.getTransaction().commit();
    }

    public List<Order> getList() {
        return session.createNamedQuery("get_all_orders").getResultList();
    }

    public Optional<Order> findById(Integer id) {
        return Optional.ofNullable(session.get(Order.class, id));
    }
}
