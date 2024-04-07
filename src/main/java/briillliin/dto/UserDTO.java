package briillliin.dto;

import briillliin.entity.Trainers;
import briillliin.entity.User;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {


    private Long userId;

    private String login;

    private String password;

    public static UserDTO from(User user) {
        return UserDTO.builder()
                .userId(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
    public static List<UserDTO> from(List<User> users) {
        return users.stream().map(UserDTO::from).toList();
    }

}