package ee.sda.mckirill.entities;

import ee.sda.mckirill.enums.MenuItemsTypeEnum;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;

@NamedQueries({
        @NamedQuery(
                name ="get_all_menuItems",
                query = "from MenuItem"
        )
})
@Entity
@Table(name = "item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MenuItemsTypeEnum type;
    @OneToOne(mappedBy = "menuItem")
    private OrderedMenuItem orderedMenuItem;
    private BigDecimal price;

    public MenuItem(){}

    public MenuItem(String name, MenuItemsTypeEnum type, BigDecimal price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuItemsTypeEnum getType() {
        return type;
    }

    public void setType(MenuItemsTypeEnum type) {
        this.type = type;
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
