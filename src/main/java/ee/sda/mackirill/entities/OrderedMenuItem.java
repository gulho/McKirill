package ee.sda.mackirill.entities;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name= "ordered_menu_item")
public class OrderedMenuItem {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "menu_id", nullable = false)
    private Integer menu;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "total_price", nullable = false)
    private Double sum;

    @OneToOne
    @JoinColumn(name = "List<order_id>")
    private Order order;

    public OrderedMenuItem () {
        
    }

    public OrderedMenuItem(int id, Integer menu, Integer quantity, Double sum, Order order) {
        this.id = id;
        this.menu = menu;
        this.quantity = quantity;
        this.sum = sum;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getMenu() {
        return menu;
    }

    public void setMenu(Integer menu) {
        this.menu = menu;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
