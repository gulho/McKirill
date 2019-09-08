package ee.sda.mackirill.entities;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "item_id", nullable = false)
    private Integer type;
    @Column(name = "price", nullable = false)
    private Double quantity;
    @OneToOne
    @JoinColumn(name = "menu_id")
    private OrderedMenuItem orderedMenuItem;

    public Menu() {

    }

    public Menu(int id, Integer type, Double quantity) {
        this.id = id;
        this.type = type;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
