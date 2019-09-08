package ee.sda.mackirill.entities;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name= "ordered_menu_item")
public class OrderedMenuItem {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
