package sr.unasat.jpa.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "mc_donalds")
public class McDonalds {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "city_fk")
    private City city;

    @OneToOne
    @JoinColumn(name = "address_fk")
    private Address address;

    @ManyToMany
    @JoinTable(name = "mc_donalds_employee",
    joinColumns = @JoinColumn(name = "mc_donalds_id"),
    inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private Set<Employee> employees = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        if (!employee.getMcDonalds().contains(this)) {
            employee.getMcDonalds().add(this);
        }
        if (!this.employees.contains(employee)) {
            this.employees.add(employee);
        }
    }

    public void removeEmployee(Employee employee) {
        if (employee.getMcDonalds().contains(this)) {
            employee.getMcDonalds().remove(this);
        }
        if (this.employees.contains(employee)) {
            this.employees.remove(employee);
        }
    }

    @Override
    public String toString() {
        return "McDonalds{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", code='" + code + '\'' +
                ", city=" + city +
                ", address=" + address +
                ", employees=" + employees +
                '}';
    }
}
