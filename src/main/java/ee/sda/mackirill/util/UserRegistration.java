package ee.sda.mackirill.util;

import ee.sda.mackirill.controllers.ApplicationContext;
import ee.sda.mackirill.controllers.entity.PersonController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.entities.PersonType;
import ee.sda.mackirill.enums.PersonTypeEnum;
import org.hibernate.Session;

public class UserRegistration {
    private Person person;
    private PersonType personType;
    private Session session = ApplicationContext.getSession();

    UserRegistration(){}

    public UserRegistration(String name, String email, String password, String phoneNumber, PersonTypeEnum userType){
        /*this.personType = new PersonType();
        personType.setType(userType);*/
        personType = session.byNaturalId(PersonType.class).using("type", userType).load();
        this.person = new Person(name, email, password, phoneNumber, personType);
    }

    public Person commitRegistration(){

        PersonController.savePerson(person);
        return person;

        /*try(Session session = new Configuration().configure().buildSessionFactory().openSession()) {
            session.beginTransaction();

            session.saveOrUpdate(person);
            session.saveOrUpdate(personType);

            session.getTransaction().commit();

        } catch (Exception e){
            e.printStackTrace();
        }*/
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
