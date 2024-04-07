package briillliin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;


import java.io.Serializable;
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

}
