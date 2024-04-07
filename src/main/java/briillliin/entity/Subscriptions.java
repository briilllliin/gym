package briillliin.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Date;
import java.util.Objects;



@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EnableAutoConfiguration
@Table(name = "subscriprions")
public class Subscriptions {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="client_id", nullable=false)
    private Clients client;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    private Activities activity;


    @Column(name = "date", nullable = false)
    private Date date;


    @Column(name = "price", nullable = false)
    private Long price;

}
