package ee.sda.mackirill;

import ee.sda.mackirill.util.PasswordField;
import ee.sda.mackirill.util.Validation;

import java.util.Scanner;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MacKirill restaurant system");
        System.out.println("Please enter your login details");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        Validation validation = new Validation(email, password);
        validation.validate();
    }
}
