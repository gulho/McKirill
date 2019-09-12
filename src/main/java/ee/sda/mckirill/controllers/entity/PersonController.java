package ee.sda.mckirill.controllers.entity;

import ee.sda.mckirill.entities.Person;

public class PersonController extends AbstractEntityController {

    public static void savePerson(Person person) {
        session.beginTransaction();
        session.saveOrUpdate(person);
        session.getTransaction().commit();
    }

    public static Person getById(int id) {
        return session.get(Person.class, id);
    }
}
