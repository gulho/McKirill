package ee.sda.mackirill.entities;

import ee.sda.mackirill.enums.OrderStatusEnum;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue
    private int id;
    @NaturalId
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum name;
    @OneToOne(mappedBy = "status")
    private Order order;

    public OrderStatus() {
    }

    public OrderStatus(OrderStatusEnum name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatusEnum getName() {
        return name;
    }

    public void setName(OrderStatusEnum name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
