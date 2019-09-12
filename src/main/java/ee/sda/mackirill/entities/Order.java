package ee.sda.mackirill.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "get_all_orders",
                query = "from Order"
        ),
        @NamedQuery(
                name = "get_all_order_by_status",
                query = "from Order where status.name = :name"
        )
})
@Entity
@Table(name = "order_booking")
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
    @OneToMany(mappedBy = "order")
    private List<OrderedMenuItem> orderedMenuItems = new ArrayList<>();
    private BigDecimal totalSum;
    @ManyToOne
    @JoinColumn(name = "payment_id")
    private PaymentType paymentType;
    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public Order() {
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

    public ee.sda.mackirill.entities.Table getTable() {
        return table;
    }

    public void setTable(ee.sda.mackirill.entities.Table table) {
        this.table = table;
    }

    public List<OrderedMenuItem> getOrderedMenuItems() {
        return orderedMenuItems;
    }

    public void setOrderedMenuItems(List<OrderedMenuItem> orderedMenuItems) {
        this.orderedMenuItems = orderedMenuItems;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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
