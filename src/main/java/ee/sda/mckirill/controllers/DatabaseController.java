package ee.sda.mckirill.controllers;

import com.google.protobuf.MapEntry;
import ee.sda.mckirill.entities.MenuItem;
import ee.sda.mckirill.entities.Order;
import ee.sda.mckirill.entities.Table;
import ee.sda.mckirill.enums.OrderStatusEnum;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;
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

    public <T> void saveInDatabase(T... objectToSaves) {
        session.beginTransaction();
        for (Object objectToSave : objectToSaves){
            session.saveOrUpdate(objectToSave);
        }
        session.getTransaction().commit();
    }

    public <T> void deleteFromDatabase(T objectToDelete) {
        session.beginTransaction();
        session.delete(objectToDelete);
        session.getTransaction().commit();
    }

    public <T> Optional<T> findById(Class<T> objectClass, int id) {
        return Optional.ofNullable(session.get(objectClass, id));
    }

    public <T, R> Optional<T> findByNaturalId(Class<T> objectClass, String naturalIdType, R naturalId) {
        return session.byNaturalId(objectClass).using(naturalIdType, naturalId).loadOptional();
    }

    public <T> List<T> getListFromNamedQuery(String queryName, Class<T> objectClass) {
        return session.createNamedQuery(queryName, objectClass).getResultList();
    }
    public <T> List<T> getListFromNamedQueryWithParameters(String queryName, Class<T> objectClass, Map<String, Object> parameters) {
        Query query = session.createNamedQuery(queryName, objectClass);
        parameters.forEach((S,O) -> query.setParameter(S, O));
        return query.getResultList();
    }
}
