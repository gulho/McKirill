package ee.sda.mckirill.entities;

import ee.sda.mckirill.enums.PersonTypeEnum;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person_type")
public class PersonType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NaturalId
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonTypeEnum type;
    @OneToMany(mappedBy = "personType")
    private List<Person> persons = new ArrayList<>();

    public PersonType() {
    }

    public PersonType(PersonTypeEnum type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PersonTypeEnum getType() {
        return type;
    }

    public void setType(PersonTypeEnum type) {
        this.type = type;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
