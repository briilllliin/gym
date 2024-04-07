package briillliin.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
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


}
