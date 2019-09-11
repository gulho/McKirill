package ee.sda.mackirill.controllers.client;

import ee.sda.mackirill.controllers.AbstractController;
import ee.sda.mackirill.entities.Person;
import ee.sda.mackirill.entities.Review;
import ee.sda.mackirill.strings.BaseString;
import org.hibernate.Session;

import java.util.Scanner;

public class ClientReviewController extends AbstractController {

    public ClientReviewController(Person person) {
        super(person);
    }

    @Override
    public void start() throws Exception {
        while (true) {
            switch (scanner.nextLine()) {
                case "0":
                    return;
                default:
                    System.out.println(BaseString.WRONG_COMMAND);
            }
        }
    }

    /*public void processReview() {
        review.setScore(0);
        do {
            System.out.println("Please print your score (1-5):");
            int score = scanner.nextInt();
            if (score > 1 && score <6 ) {
               review.setScore(score);
            } else {
                System.out.println("Score is incorrect. Please print number from 1 - 5");
            }
        } while (review.getScore() != 0);
        System.out.println("Please enter our review:");
        review.setReviewText(scanner.nextLine());
        saveReview();
    }

    public void removeReview() {
        System.out.println("Do yo want ");
    }

    private void saveReview() {
        session.beginTransaction();
        session.saveOrUpdate(review);
        session.getTransaction().commit();
    }*/
}
