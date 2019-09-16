package ee.sda.mckirill;

import ee.sda.mckirill.controllers.ApplicationContext;
import ee.sda.mckirill.controllers.Factory;
import ee.sda.mckirill.controllers.ui.AbstractUIController;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.ControllersEnum;
import ee.sda.mckirill.strings.BaseString;
import ee.sda.mckirill.util.Validation;

import java.util.Optional;
import java.util.Scanner;

/**
 *  McKirill app starting point
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
                AbstractUIController controller = Factory.getController(personOptional.get(), ControllersEnum.MAIN);
                controller.start();
            } else {
                System.out.println(BaseString.NOT_LOGIN);
                System.out.println(BaseString.EXIT);
            }

            //Person person = PersonController.getById(13);
            //Person person = PersonController.getById(17);
            //Factory.getController(person, ControllersEnum.MAIN).start();
        } catch (Exception ex) {
            System.out.println("Application catch exception");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace().toString());
        } finally {
            if(applicationContext.getSession() != null) {
                applicationContext.getSession().close();
            }
        }
    }

    public static int numb() {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(a+b);
        return a+b;
    }




}
