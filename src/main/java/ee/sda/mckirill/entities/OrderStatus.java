package ee.sda.mckirill.entities;

import ee.sda.mckirill.enums.OrderStatusEnum;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "status")
    private List<Order> order = new ArrayList<>();

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

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
