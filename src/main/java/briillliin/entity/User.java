package briillliin.entity;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.util.Objects;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "user_table")
@EnableAutoConfiguration
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String login;


    @Column
    private String password;

}
