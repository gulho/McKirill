package ee.sda.mackirill.entities;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "name", nullable = false)
    private Integer name;
    @Column(name = "type", nullable = false)
    private Double type;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Menu menu;

    public Item(){

    }

    public Item(int id, Integer name, Double type, Menu menu) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.menu = menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Double getType() {
        return type;
    }

    public void setType(Double type) {
        this.type = type;
    }
}
