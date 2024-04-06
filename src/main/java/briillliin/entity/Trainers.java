package briillliin.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Класс тренеров.
 */
@Entity
@EnableAutoConfiguration
@Table(name = "trainers")
public class Trainers {

    /**
     * Id колонки (первичный ключ - значение которое будет использоваться для обеспечения уникальности данных в текущей таблице).
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ФИО тренера.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Паспорт тренера.
     */
    @Column(name = "passport", nullable = false, unique = true)
    private String passport;

    /**
     * Телефон тренера.
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * Адрес тренера.
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * Занятия тренера
     */
    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<Activities> activities = new ArrayList<>();

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param name - ФИО
     * @param passport - паспорт
     * @param phone - телефон
     * @param address - адрес
     */
    public Trainers(String name, String passport, String phone, String address) {
        this.name = name;
        this.passport = passport;
        this.phone = phone;
        this.address = address;

    }


    public Trainers() {

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


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Trainers))
            return false;
        Trainers trainers = (Trainers) o;
        return Objects.equals(this.id, trainers.id) && Objects.equals(this.name, trainers.name)
                && Objects.equals(this.passport, trainers.passport) && Objects.equals(this.phone, trainers.phone)
                && Objects.equals(this.address, trainers.address);
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.passport, this.phone, this.address);
    }


    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", passport='" + this.passport + '\'' +
                ", phone='" + this.phone + ", address='" + this.address + '}';
    }
}
