package briillliin.services;

import briillliin.dto.TrainersDTO;
import briillliin.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {


    List<UserDTO> getAll();

    UserDTO getUserById(Long id);

    UserDTO updateUser(Long id, UserDTO user);

    UserDTO addUser(UserDTO user);

    void deleteUserById(Long id);




}
