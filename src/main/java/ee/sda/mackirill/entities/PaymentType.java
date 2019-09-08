package ee.sda.mackirill.entities;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "payment_type")
public class PaymentType {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "payment_name", nullable = false)
    private String paymentName;
    @OneToOne(mappedBy = "paymentType")
    private Order order;

    public PaymentType() {
    }

    public PaymentType(String paymentName) {
        this.paymentName = paymentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
