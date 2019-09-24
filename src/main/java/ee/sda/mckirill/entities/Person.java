
package ee.sda.mckirill.entities;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "get_all_clients",
                query = "from Person where personType.type = ee.sda.mckirill.enums.PersonTypeEnum.CLIENT"
        ),
        @NamedQuery(
                name = "get_all_waiters",
                query = "from Person where personType.type = ee.sda.mckirill.enums.PersonTypeEnum.WAITER"
        )
})
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "person_type_id")
    private PersonType personType;

    @OneToMany(mappedBy = "person")
    private List<WaiterTip> waiterTipList = new ArrayList<>();

    public Person() {
    }

    public Person(String name, String email, String password, String phoneNumber, PersonType personType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.personType = personType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public List<WaiterTip> getWaiterTipList() {
        return waiterTipList;
    }

    public void setWaiterTipList(List<WaiterTip> waiterTipList) {
        this.waiterTipList = waiterTipList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", personType=" + personType +
                '}';
    }
}
