package briillliin.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@Entity
@EnableAutoConfiguration
@Table(name = "activities")
public class Activities implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "trainer_id", referencedColumnName = "id", nullable = false)
    private Trainers trainer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "area_id", referencedColumnName = "id", nullable = false)
    private Areas area;

    @OneToMany(mappedBy = "activity", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Subscriptions> subscriptions = new ArrayList<>();


    public Activities(String name, Trainers trainer, Areas area) {
        this.name = name;
        this.trainer = trainer;
        this.area = area;
    }


    public Activities() {

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


    public Trainers getTrainer() {
        return trainer;
    }


    public void setTrainer(Trainers trainer) {
        this.trainer = trainer;
    }


    public Areas getArea() {
        return area;
    }


    public void setArea(Areas area) {
        this.area = area;
    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Activities))
            return false;
        Activities activities = (Activities) o;
        return Objects.equals(this.id, activities.id) && Objects.equals(this.name, activities.name)
                && Objects.equals(this.trainer, activities.trainer) && Objects.equals(this.area, activities.area);
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.trainer, this.area);
    }


    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' +
                ", trainer_id='" + this.trainer + '\'' + ", area_id='" + this.area + '\'' + '}';
    }
}
