package ee.sda.mckirill.controllers.ui;

import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.entities.Review;
import ee.sda.mckirill.strings.ReviewStrings;
import ee.sda.mckirill.util.ConsoleTablePrint;

import java.time.LocalDateTime;

public class ReviewUIController extends AbstractUIController {

    public ReviewUIController(Person person) {
        super(person);
    }

    @Override
    public void start() {
        switch (person.getPersonType().getType()) {
            case CLIENT:
                Review review = new Review();
                review.setScore(selectUnsignedInteger(ReviewStrings.PRINT_SCORE, ReviewStrings.PRINT_SCORE_WRONG, 5));
                review.setReviewText(selectString(ReviewStrings.PRINT_REVIEW, ReviewStrings.PRINT_REVIEW_WRONG, 5000));
                review.setPerson(person);
                review.setDate(LocalDateTime.now());
                saveInDatabase(review);
                break;
            case MANAGER:
                ConsoleTablePrint reviewConsole = new ConsoleTablePrint();
                reviewConsole.setShowVerticalLines(true);
                reviewConsole.setHeaders(ReviewStrings.TABLE_PERSON, ReviewStrings.TABLE_REVIEW_DATE, ReviewStrings.TABLE_REVIEW_SCORE, ReviewStrings.TABLE_REVIEW_TEXT);
                getListFromNamedQuery("get_all_reviews", Review.class).forEach(reviewT -> reviewConsole.addRow(
                        reviewT.getPerson().getName(), reviewT.getDate().toString(), reviewT.getScore() + "", reviewT.getReviewText()));
                reviewConsole.print();
                break;
        }
    }
}
