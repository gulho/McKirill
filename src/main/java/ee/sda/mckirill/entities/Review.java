package ee.sda.mckirill.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person preson;
    private int score;
    @Column(name = "review_text",length = 5000)
    private String reviewText;
    private LocalDateTime date;

    public Review() {
    }

    public Review(Person preson, int score, String reviewText, LocalDateTime date) {
        this.preson = preson;
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

    public Person getPreson() {
        return preson;
    }

    public void setPreson(Person preson) {
        this.preson = preson;
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
