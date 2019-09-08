package ee.sda.mackirill.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name= "ordered_menu_item")
public class OrderedMenuItem {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @OneToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "total_price", nullable = false)
    private BigDecimal sum;

    public OrderedMenuItem () {
        
    }

    public OrderedMenuItem(Menu menu, Integer quantity, BigDecimal sum) {
        this.menu = menu;
        this.quantity = quantity;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
