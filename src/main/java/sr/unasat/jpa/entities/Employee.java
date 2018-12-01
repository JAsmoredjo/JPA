package sr.unasat.jpa.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", nullable = false)
    private char gender;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToMany(mappedBy = "employees")
    private Set<McDonalds> mcDonalds = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<McDonalds> getMcDonalds() {
        return mcDonalds;
    }

    public void setMcDonalds(Set<McDonalds> mcDonalds) {
        this.mcDonalds = mcDonalds;
    }

    public void addMcDonalds(McDonalds mcDonalds) {
        if (!mcDonalds.getEmployees().contains(this)) {
            mcDonalds.getEmployees().add(this);
        }
        if (!this.mcDonalds.contains(mcDonalds)) {
            this.mcDonalds.add(mcDonalds);
        }
    }

    public void removeMcDonalds(McDonalds mcDonalds) {
        if (mcDonalds.getEmployees().contains(this)) {
            mcDonalds.getEmployees().remove(this);
        }
        if (this.mcDonalds.contains(mcDonalds)) {
            this.mcDonalds.remove(mcDonalds);
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
