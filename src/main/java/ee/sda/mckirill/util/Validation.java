package ee.sda.mckirill.util;

import ee.sda.mckirill.controllers.ApplicationContext;
import ee.sda.mckirill.entities.Person;
import ee.sda.mckirill.enums.PersonTypeEnum;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Validation {
    private String email;
    private String password;
    private Session session = ApplicationContext.getSession();
    private Scanner scanner = ApplicationContext.getScanner();

    public Validation() {
    }

    public Validation(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Optional<Person> validate() {
        //Scanner scanner = new Scanner(System.in);
        Person returnPerson = null;
        do {
            switch (isEmailValid(email)) {
                case 1:
                    System.out.print("Invalid email format - please try again:");
                    email = scanner.nextLine();
                    System.out.println();
                    break;
                case 2:
                    System.out.println("Non-existing User, please provide additional details to register a new account");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    /*System.out.println("Password: ");
                    password = scanner.nextLine();*/
                    System.out.print("Phone number: ");
                    String phoneNumber = scanner.nextLine();

                    UserRegistration userRegistration = new UserRegistration(name, email, password, phoneNumber, PersonTypeEnum.CLIENT);
                    returnPerson = userRegistration.commitRegistration();
                    break;

                default:
                    if (isPasswordValid(email, password)) {
                        System.out.println("Welcome!");
                        returnPerson = session
                                .createQuery("from Person where email = :email ", Person.class)
                                .setParameter("email", email).getSingleResult();
                        //System.out.println(returnPerson);
                    } else {
                        int numberOfAttempts = 1;
                        boolean isPasswordInvalid = true;
                        while (numberOfAttempts <= 3) {
                            System.out.print("Invalid password, please try again (" + numberOfAttempts + "/3): ");
                            numberOfAttempts++;
                            password = scanner.nextLine();
                            System.out.println();
                            if (isPasswordValid(email, password)) {
                                System.out.println("Welcome!");
                                isPasswordInvalid = false;
                                returnPerson = session
                                        .createQuery("from Person where email = :email ", Person.class)
                                        .setParameter("email", email).getSingleResult();
                                break;
                            }
                        }
                        if (isPasswordInvalid) {
                            System.out.println("Too many failed attempts - good bye!");
                        }
                    }
            }
        } while (isEmailValid(email) == 1 || isEmailValid(email) == 2);
        return Optional.ofNullable(returnPerson);
    }

    public int isEmailValid(String email) {
        int result = 0; // user exists, proceed

        if (email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$") == false) {
            result = 1; // email format is invalid
            return result;
        } else if (doesUserExist(email) == false) {
            result = 2; // user does not exist, needs registration
            return result;
        }
        return result;
    }

    public boolean isPasswordValid(String email, String password) {
        //try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
        String[] emailParts = email.split("@");

        String hql = "SELECT password FROM Person WHERE email LIKE '" + emailParts[0] + "_" + emailParts[1] + "'";
        Query query = session.createQuery(hql);
        List<String> passwords = query.list();

        for (String passwordRecord : passwords) {
            if (password.equalsIgnoreCase(passwordRecord)) {
                return true;
            }
        }
        /*} catch (HibernateException e) {
            e.printStackTrace();
        }*/
        return false;
    }


    public boolean doesUserExist(String email) {
        //try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
        Query query = session.createQuery("SELECT email FROM Person");
        List<String> emailList = query.list();
        for (String emailRecord : emailList) {
            if (email.equalsIgnoreCase(emailRecord)) {
                return true;
            }
        }
        /*} catch (Exception e) {
            e.printStackTrace();
        }*/
        return false;
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
}
