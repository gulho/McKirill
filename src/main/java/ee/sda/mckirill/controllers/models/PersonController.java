package ee.sda.mckirill.controllers.models;

import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.WaiterTip;

public class PersonController extends AbstractEntityController {
    private static PersonController personController;

    private PersonController() {
    }

    public static PersonController of() {
        return new PersonController();
    }

    public void savePerson(Person person) {
        session.beginTransaction();
        session.saveOrUpdate(person);
        session.getTransaction().commit();
    }

    public void saveWaiterTip(WaiterTip waiterTip) {
        session.beginTransaction();
        session.save(waiterTip);
        session.getTransaction().commit();
    }

    public Person getById(int id) {
        return session.get(Person.class, id);
    }
}
