package briillliin.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@Entity
@EnableAutoConfiguration
@Table(name = "clients")
public class Clients {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "passport", nullable = false, unique = true)
    private String passport;


    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany(mappedBy="client")
    private List<Subscriptions> subscriptions = new ArrayList<>();

    public Clients(String name, String passport, String phone) {
        this.name = name;
        this.passport = passport;
        this.phone = phone;
    }


    public Clients() {

    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPassport() {
        return passport;
    }


    public void setPassport(String passport) {
        this.passport = passport;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Clients))
            return false;
        Clients clients = (Clients) o;
        return Objects.equals(this.id, clients.id) && Objects.equals(this.name, clients.name)
                && Objects.equals(this.passport, clients.passport) && Objects.equals(this.phone, clients.phone);
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.passport, this.phone);
    }


    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", passport='" + this.passport + '\'' +
                ", phone='" + this.phone + '}';
    }
}
