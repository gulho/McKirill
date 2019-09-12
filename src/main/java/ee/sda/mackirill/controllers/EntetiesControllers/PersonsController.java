package ee.sda.mackirill.controllers.EntetiesControllers;

import ee.sda.mackirill.entities.Person;

public class PersonsController extends AbstractEntitiController {

    public static void savePerson(Person person) {
        session.beginTransaction();
        session.saveOrUpdate(person);
        session.getTransaction().commit();
    }
}
