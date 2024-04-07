package briillliin.services.serviceImpl;

import briillliin.controller.errors.UserNotFoundException;
import briillliin.dto.UserDTO;
import briillliin.entity.User;
import briillliin.repository.UserRepository;
import briillliin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userDTOList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            userDTOList.add(UserDTO.from(user));
        }
        return userDTOList;
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserDTO.from(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        existingUser.setLogin(updatedUser.getLogin());
        existingUser.setPassword(updatedUser.getPassword());
        return UserDTO.from(userRepository.save(existingUser));
    }

    @Override
    public UserDTO addUser(UserDTO newUser) {
        User user = new User();
        user.setLogin(newUser.getLogin());
        user.setPassword(newUser.getPassword());
        return UserDTO.from(userRepository.save(user));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
