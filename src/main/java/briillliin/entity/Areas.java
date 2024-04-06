package briillliin.entity;


import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@Entity
@EnableAutoConfiguration
@Table(name = "areas")
public class Areas implements Serializable {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false, unique = true)
    private String name;


    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Activities> activities = new ArrayList<>();


    public Areas(String name) {
        this.name = name;
    }


    public Areas() {}


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


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Areas))
            return false;
        Areas areas = (Areas) o;
        return Objects.equals(this.id, areas.id) && Objects.equals(this.name, areas.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }


    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
}
