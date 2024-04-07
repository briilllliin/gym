package briillliin.controller;

import briillliin.dto.UserDTO;
import briillliin.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name="Контроллер пользователей", description="Операции CRUD для пользователей")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Получить всех пользователей", description = "Получить список всех пользователей")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @Operation(summary = "Создать нового пользователя", description = "Создать нового пользователя")
    @PostMapping
    public ResponseEntity<UserDTO> newUser(@RequestBody UserDTO user) {
        UserDTO createdUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Получить пользователя по ID", description = "Получить пользователя по его ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Обновить пользователя", description = "Обновить существующего пользователя")
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user, @PathVariable Long id) {
        UserDTO updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok().body(updatedUser);
    }

    @Operation(summary = "Удалить пользователя", description = "Удалить пользователя по его ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
