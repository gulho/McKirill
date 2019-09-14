package ee.sda.mckirill.util;

import ee.sda.mckirill.controllers.ApplicationContext;
import ee.sda.mckirill.controllers.models.PersonController;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.PersonType;
import ee.sda.mckirill.enums.PersonTypeEnum;
import org.hibernate.Session;

public class UserRegistration {
    private Person person;
    private PersonType personType;
    private Session session = ApplicationContext.getSession();
    private PersonController personController = PersonController.of();

    UserRegistration(){}

    public UserRegistration(String name, String email, String password, String phoneNumber, PersonTypeEnum userType){
        /*this.personType = new PersonType();
        personType.setType(userType);*/
        this.person = new Person(name, email, password, phoneNumber, ApplicationContext.getPersonTypeType().getClient());
    }

    public Person commitRegistration(){

        PersonController.of().savePerson(person);
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
