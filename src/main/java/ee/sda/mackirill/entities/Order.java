package ee.sda.mackirill.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private int peoples;
    private LocalDateTime timeToOrder;
    @ManyToOne
    @JoinColumn(name = "table_id")
    private ee.sda.mackirill.entities.Table table;
    //private List<OrderedMenuItem> orderedMenuItems;
    private BigDecimal totalSum;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private PaymentType paymentType;
   // private OrderStatus status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public Order(int id) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeoples() {
        return peoples;
    }

    public void setPeoples(int peoples) {
        this.peoples = peoples;
    }

    public LocalDateTime getTimeToOrder() {
        return timeToOrder;
    }

    public void setTimeToOrder(LocalDateTime timeToOrder) {
        this.timeToOrder = timeToOrder;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
