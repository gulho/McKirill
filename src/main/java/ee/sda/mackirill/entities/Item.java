package ee.sda.mackirill.entities;

import javax.persistence.*;

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

    public Item(int id, Integer name, Double type) {
        this.id = id;
        this.name = name;
        this.type = type;
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
