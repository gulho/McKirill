package ee.sda.mackirill.util;

import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.entities.PersonType;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class UserRegistration {
    private Person person;
    private PersonType personType;

    UserRegistration(){}

    public UserRegistration(String name, String email, String password, String phoneNumber, String userType){
        this.personType = new PersonType();
        personType.setType(userType);
        this.person = new Person(name, email, password, phoneNumber, personType);
    }

    public void commitRegistration(){

        try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            session.beginTransaction();

            session.saveOrUpdate(person);
            session.saveOrUpdate(personType);

            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }
}
