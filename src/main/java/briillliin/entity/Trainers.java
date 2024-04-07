package briillliin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EnableAutoConfiguration
@Table(name = "trainers")
public class Trainers {


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


    @Column(name = "address", nullable = false)
    private String address;


    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<Activities> activities = new ArrayList<>();


}
