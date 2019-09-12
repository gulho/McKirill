package ee.sda.mackirill.entities;

import ee.sda.mackirill.enums.PaymentTypeEnum;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment_type")
public class PaymentType {
    @Id
    @GeneratedValue
    private int id;
    @NaturalId
    @Column(name = "payment_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentTypeEnum paymentName;
    @OneToMany(mappedBy = "paymentType")
    private List<Order> order = new ArrayList<>();

    public PaymentType() {
    }

    public PaymentType(PaymentTypeEnum paymentName) {
        this.paymentName = paymentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PaymentTypeEnum getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(PaymentTypeEnum paymentName) {
        this.paymentName = paymentName;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
