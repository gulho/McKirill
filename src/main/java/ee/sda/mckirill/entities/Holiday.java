package ee.sda.mckirill.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(
                name = "get_all_holidays",
                query = "from Holiday order by fromDate"
        ),
        @NamedQuery(
                name = "check_date_is_holiday",
                query = "select count (id) from Holiday where fromDate >= :date AND toDate >= :date"
        )
})
@Entity
@Table(name = "holiday")
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "from_date")
    private LocalDate fromDate;
    @Column(name = "to_date")
    private LocalDate toDate;

    public Holiday() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
