package ee.sda.mckirill.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NamedQueries({
        @NamedQuery(
                name = "get_all_reviews",
                query = "from Review order by date desc"
        )
})
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private int score;
    @Column(name = "review_text",length = 5000)
    private String reviewText;
    private LocalDateTime date;

    public Review() {
    }

    public Review(Person person, int score, String reviewText, LocalDateTime date) {
        this.person = person;
        this.score = score;
        this.reviewText = reviewText;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person preson) {
        this.person = preson;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
