package ee.sda.mackirill.entities;

import ee.sda.mackirill.enums.MenuItemsTypeEnum;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MenuItemsTypeEnum type;

    @OneToOne(mappedBy = "item")
    private Menu menu;

    public Item(){

    }

    public Item(String name, MenuItemsTypeEnum type) {
        this.name = name;
        this.type = type;
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
