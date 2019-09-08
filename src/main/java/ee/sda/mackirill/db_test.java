package ee.sda.mackirill;

import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.entities.PersonType;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class db_test {
    public static void main(String[] args) {
        PersonType personType = new PersonType("client");
        Person person = new Person("name1", "emal@dasf", "pass", "37253245", personType);

        personType.setPerson(person);

        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            session.beginTransaction();

            session.saveOrUpdate(personType);
            session.saveOrUpdate(person);

            session.getTransaction().commit();
        }
    }

}
