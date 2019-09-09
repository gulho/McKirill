package ee.sda.mackirill.entities;

import ee.sda.mackirill.enums.PersonTypeEnum;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "person_type")
public class PersonType {
    @Id
    @GeneratedValue
    private int id;
    @NaturalId
    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonTypeEnum type;
    @OneToOne(mappedBy = "personType", cascade = CascadeType.ALL)
    private Person person;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
