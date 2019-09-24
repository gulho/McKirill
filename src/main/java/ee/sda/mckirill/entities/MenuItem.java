package ee.sda.mckirill.entities;

import ee.sda.mckirill.enums.MenuItemsTypeEnum;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name ="get_all_menuItems",
                query = "from MenuItem order by type"
        )
})
@Entity
@Table(name = "item")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MenuItemsTypeEnum type;
    private BigDecimal price;
    @OneToMany(mappedBy = "menuItem")
    private List<OrderedMenuItem> orderedMenuItems = new ArrayList<>();

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OrderedMenuItem> getOrderedMenuItems() {
        return orderedMenuItems;
    }

    public void setOrderedMenuItems(List<OrderedMenuItem> orderedMenuItem) {
        this.orderedMenuItems = orderedMenuItem;
    }

    @PreRemove
    private void preRemove() {
        getOrderedMenuItems().forEach(orderedMenuItem -> orderedMenuItem.setMenuItem(null));
    }
}
