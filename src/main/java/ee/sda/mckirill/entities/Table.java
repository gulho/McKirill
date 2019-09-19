package ee.sda.mckirill.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries(value = {
        @NamedQuery(
                name = "get_all_tables",
                query = "from Table"
        ),
        @NamedQuery(
                name = "get_suitable_table",
                query = "from Table where size >= :size AND is_available = true"
        )
})

@Entity
@javax.persistence.Table(name = "restaurant_table")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int size;
    private boolean is_available;
    @OneToMany(mappedBy = "table")
    private List<Order> orders = new ArrayList<>();

    public Table() {
        this.is_available = true;
    }

    public Table(int size, boolean is_available) {
        this.size = size;
        this.is_available = is_available;
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

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
