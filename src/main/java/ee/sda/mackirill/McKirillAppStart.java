package ee.sda.mackirill;

import ee.sda.mackirill.controllers.AbstractController;
import ee.sda.mackirill.controllers.ApplicationContext;
import ee.sda.mackirill.controllers.Factory;
import ee.sda.mackirill.controllers.client.ClientController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.enums.PersonTypeEnum;
import ee.sda.mackirill.strings.BaseString;

/**
 *  McKirill app starting point
 */

public class McKirillAppStart {
    private static ApplicationContext applicationContext;


    public static void main(String[] args) {
        try {
            applicationContext = new ApplicationContext();
            System.out.println(BaseString.WELCOME);
            /**TODO: Log in/ Register.
             * Return Person Object
             * Depends PersonType select Controller
             * Now it select By select
             **/
            Person person = applicationContext.getSession().get(Person.class, 12);
            /*if (person == null) {
                Session session = applicationContext.getSession();
                PersonType client = session.byNaturalId(PersonType.class).using("type",PersonTypeEnum.CLIENT).load();
                session.beginTransaction();
                person = new Person("name", "emal", "password", "23452345", client);
                session.saveOrUpdate(person);
               1 session.getTransaction().commit();
            }*/
            //person.getPersonType().setType(PersonTypeEnum.MANAGER);
            AbstractController controller = Factory.getController(person);
            controller.start();

        } catch (Exception ex) {
            System.out.println("Application catch exception");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        } finally {
            if(applicationContext.getSession() != null) {
                applicationContext.getSession().close();
            }
        }
    }




}
