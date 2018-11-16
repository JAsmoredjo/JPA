package sr.unasat.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "mc_donalds")
public class McDonalds {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "city_fk")
    private City city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "McDonalds{" +
                "id=" + id +
                ", address=" + address +
                ", phoneNumber=" + phoneNumber +
                ", code=" + code +
                ", " + city.toString() + '}';
    }
}
