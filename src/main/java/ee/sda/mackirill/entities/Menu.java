package ee.sda.mackirill.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @OneToOne(mappedBy = "menu")
    private OrderedMenuItem orderedMenuItem;
    private BigDecimal price;

    public Menu() {

    }

    public Menu(Item item, BigDecimal price) {
        this.item = item;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public OrderedMenuItem getOrderedMenuItem() {
        return orderedMenuItem;
    }

    public void setOrderedMenuItem(OrderedMenuItem orderedMenuItem) {
        this.orderedMenuItem = orderedMenuItem;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
