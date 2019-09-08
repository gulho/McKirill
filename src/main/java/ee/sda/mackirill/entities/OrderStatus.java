package ee.sda.mackirill.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_status")
public class OrderStatus {
    @Id
    @GeneratedValue
    private int id;
    private String name;
}
