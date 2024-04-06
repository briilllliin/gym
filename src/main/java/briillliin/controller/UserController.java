package briillliin.controller;


import briillliin.controller.errors.UserNotFoundException;
import briillliin.entity.User;
import briillliin.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    
    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/users")
    List<User> all() {
        return userRepository.findAll();
    }


    @PostMapping("/users")
    User newUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }


    @PutMapping("/users/{id}")
    User replaceClient(@RequestBody User user, @PathVariable Long id) {

        return userRepository.findById(id)
                .map(area -> {
                    user.setLogin(user.getLogin());
                    user.setPassword(user.getPassword());
                    return userRepository.save(area);
                })
                .orElseGet(() -> {
                    user.setId(id);
                    return userRepository.save(user);
                });
    }


    @DeleteMapping("/users/{id}")
    void deleteClient(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
