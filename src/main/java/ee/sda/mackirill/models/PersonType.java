package ee.sda.mackirill.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PersonType {
    @Id
    @GeneratedValue
    private int id;
    private String type;
}
