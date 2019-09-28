package ee.sda.mckirill.entities;

import javax.persistence.*;

@NamedQueries(value = {
        @NamedQuery(
                name = "get_all_tables",
                query = "from Table"
        ),
        @NamedQuery(
                name = "get_suitable_table",
                query = "from Table where tableSize >= :size AND is_available = true"
        )
})

@Entity
@javax.persistence.Table(name = "restaurant_table")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "size")
    private int tableSize;
    private boolean is_available;

    public Table() {
        this.is_available = true;
    }

    public Table(int tableSize, boolean is_available) {
        this.tableSize = tableSize;
        this.is_available = is_available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int size) {
        this.tableSize = size;
    }

    public boolean isIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

}
