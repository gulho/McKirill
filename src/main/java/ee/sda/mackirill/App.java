package ee.sda.mackirill;

import ee.sda.mackirill.controllers.UI.AbstractUIController;
import ee.sda.mackirill.controllers.ApplicationContext;
import ee.sda.mackirill.controllers.Factory;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.enums.ControllrsEnum;
import ee.sda.mackirill.strings.BaseString;
import ee.sda.mackirill.util.Validation;

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
                AbstractUIController controller = Factory.getController(personOptional.get(), ControllrsEnum.MAIN);
                controller.start();
            } else {
                System.out.println(BaseString.NOT_LOGIN);
                System.out.println(BaseString.EXIT);
            }

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
