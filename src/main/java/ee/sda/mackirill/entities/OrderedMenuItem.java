package ee.sda.mackirill.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "ordered_menu_item")
public class OrderedMenuItem {
    @Id
    @GeneratedValue
    private int id;
}
