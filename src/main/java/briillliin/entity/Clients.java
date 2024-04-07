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

}
