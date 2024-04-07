package briillliin.repository;


import briillliin.dto.UserDTO;
import briillliin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
