package ee.sda.mackirill.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PersonType {
    @Id
    @GeneratedValue
    private int id;
    private String type;

    public PersonType(){
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
