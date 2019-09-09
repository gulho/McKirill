package ee.sda.mackirill;

import ee.sda.mackirill.controllers.ApplicationContext;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.entities.PersonType;
import ee.sda.mackirill.enums.PersonTypeEnum;
import org.hibernate.ConnectionReleaseMode;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 *  McKirill app starting point
 */

public class McKirillAppStart {
    private static ApplicationContext applicationContext;


    public static void main(String[] args) {
        try {
            applicationContext = new ApplicationContext();
            /**TODO: Log in/ Register.
             * Return Person Object
             * Depends PersonType select Controller
             * Now it select By select
             **/
            Person person = applicationContext.getSession().get(Person.class, 5);
            /*switch(person.getPersonType().getType()) {
                case PersonTypeEnum.CLIENT:
                System.out.println("client");
                break;
                case PersonTypeEnum.MANAGER:
                break;
                case PersonTypeEnum.WAITER:
                break;
                default:

            }*/



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
