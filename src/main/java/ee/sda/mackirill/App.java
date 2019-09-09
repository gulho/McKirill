package ee.sda.mackirill;

import ee.sda.mackirill.controllers.client.ClientReviewController;
import ee.sda.mackirill.entities.Person;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {

            System.out.println("Welcome to MacKirill restaurant system");


            while (true) {
                System.out.println("Print command:");
                System.out.println("1 Booking, 2 Manage Reviews,2  extit");
                switch (scanner.nextLine().toLowerCase()) {
                    case "1":
                        break;
                    case "2":
                        Person client = session.find(Person.class, 6);
                        ClientReviewController clientReviewManage = new ClientReviewController(session, client);
                        clientReviewManage.processReview();
                        break;
                    case "exit":
                        return;
                }
            }
        } catch (Exception e) {

        }
    }
}
