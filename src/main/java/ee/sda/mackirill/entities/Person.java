package ee.sda.mackirill.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String nickname;
    private String email;
    private String password;
    private String phoneNumber;
    private int type_id;
}
