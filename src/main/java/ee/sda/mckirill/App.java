package ee.sda.mckirill;

import ee.sda.mckirill.controllers.ApplicationContext;
import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.util.Validation;

import java.util.Arrays;
import java.util.Optional;

/**
 * McKirill app starting point
 */

public class App {
    private static ApplicationContext applicationContext;


    public static void main(String[] args) {
        try {
            applicationContext = new ApplicationContext();
            System.out.println(BaseString.WELCOME);

            System.out.print("Email: ");
            String email = applicationContext.getScanner().nextLine();

            System.out.print("Password: ");
            String password = applicationContext.getScanner().nextLine();

            Validation validation = new Validation(email, password);
            Optional<Person> personOptional = validation.validate();

            if (personOptional.isPresent()) {
                Factory.getController(personOptional.get(), ControllersEnum.MAIN).start();
            } else {
                System.out.println(BaseString.NOT_LOGIN);
                System.out.println(BaseString.EXIT);
            }

            System.out.println(BaseString.EXIT);
        } catch (Exception ex) {
            System.out.println("Application catch exception");
            System.out.println(ex.getMessage());
            System.out.println(Arrays.toString(ex.getStackTrace()));
        } finally {
            if (applicationContext.getSession() != null) {
                applicationContext.getSession().close();
            }
        }
    }


}
