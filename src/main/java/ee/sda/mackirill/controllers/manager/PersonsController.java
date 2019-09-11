package ee.sda.mackirill.controllers.manager;

import ee.sda.mackirill.controllers.AbstractController;
import ee.sda.mackirill.entities.Person;

public class PersonsController extends AbstractController {
    public PersonsController(Person person) {
        super(person);
    }

    @Override
    public void start() throws Exception {

    }

    public static void savePerson(Person person) {
        session.beginTransaction();
        session.saveOrUpdate(person);
        session.getTransaction().commit();
    }
}
