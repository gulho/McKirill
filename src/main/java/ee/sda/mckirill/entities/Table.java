package ee.sda.mckirill.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@javax.persistence.Table(name = "restaurant_table")
public class Table {
    @Id
    @GeneratedValue
    private int id;
    private int size;
    private boolean is_avalible;
    @OneToMany(mappedBy = "table")
    private List<Order> orders = new ArrayList<>();

    public Table() {
    }

    public Table(int size, boolean is_avalible) {
        this.size = size;
        this.is_avalible = is_avalible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isIs_avalible() {
        return is_avalible;
    }

    public void setIs_avalible(boolean is_avalible) {
        this.is_avalible = is_avalible;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
