package ee.sda.mackirill;

import ee.sda.mackirill.models.Person;
import ee.sda.mackirill.util.Validation;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MacKirill restaurant system");
        System.out.println("Please enter your login details");

        Validation validation = new Validation();

        System.out.println("Email: ");
        String email;
        do {
            email = scanner.nextLine();

            switch (validation.isEmailValid(email)) {
                case 1:
                    System.out.println("Invalid email format - please try again:");
                    break;
                case 2:
                    System.out.println("Invalid User, please register");
                    //Registration block
                    break;

                default:
                    System.out.println("Password: ");
                    String password = scanner.nextLine();
            }
        } while (validation.isEmailValid(email) == 1);


        // Person person = new Person(email, password);


    }
}
