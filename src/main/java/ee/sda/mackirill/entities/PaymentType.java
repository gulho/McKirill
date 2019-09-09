package ee.sda.mackirill.entities;

import ee.sda.mackirill.enums.PaymentTypeEnum;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.persistence.Table;

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
    @OneToOne(mappedBy = "paymentType")
    private Order order;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
