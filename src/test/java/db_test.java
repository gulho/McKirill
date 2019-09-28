import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.PersonType;
import ee.sda.mckirill.enums.PersonTypeEnum;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.Ignore;

@Ignore
public class db_test {
    public static void main(String[] args) {
        PersonType personType = new PersonType(PersonTypeEnum.CLIENT);
        Person person = new Person("name1", "emal@dasf", "pass", "37253245", personType);


        personType.getPersons().add(person);

        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            session.beginTransaction();

            session.saveOrUpdate(personType);
            session.saveOrUpdate(person);

            session.getTransaction().commit();
        }
    }

}
