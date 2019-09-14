package ee.sda.mckirill.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "waiter_tip")
public class WaiterTip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private BigDecimal tip = BigDecimal.ZERO;
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public WaiterTip() {
    }

    public WaiterTip(Person person, BigDecimal tip, Order order) {
        this.person = person;
        this.tip = tip;
        this.order = order;
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

    public void setPerson(Person person) {
        this.person = person;
    }

    public BigDecimal getTip() {
        return tip;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
