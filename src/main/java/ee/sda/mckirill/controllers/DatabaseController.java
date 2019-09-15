package ee.sda.mckirill.controllers;

import ee.sda.mckirill.entities.MenuItem;
import ee.sda.mckirill.entities.Order;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class DatabaseController {
    private static Session session = ApplicationContext.getSession();
    private static DatabaseController databaseController;

    public DatabaseController() {
    }

    public static DatabaseController of() {
        if (databaseController == null) {
            databaseController = new DatabaseController();
        }
        return databaseController;
    }

    public <T> void save(T objectToSave) {
        session.beginTransaction();
        session.save(objectToSave);
        session.getTransaction().commit();
    }

    public <T> Optional<T> findById(Class<T> objectClass, int id) {
        return Optional.ofNullable(session.get(objectClass, id));
    }

    public <T, R> Optional<T> findByNaturalId(Class<T> objectClass, String naturalIdType, R naturalId) {
        return session.byNaturalId(objectClass).using(naturalIdType, naturalId).loadOptional();
    }

    public List<MenuItem> getListOfMenuItems() {
        return session.createNamedQuery("get_all_menuItems", MenuItem.class).getResultList();
    }

    public List<Order> getOrdersList() {
        return session.createNamedQuery("get_all_orders").getResultList();
    }

}
