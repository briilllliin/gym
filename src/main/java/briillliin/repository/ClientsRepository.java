package briillliin.repository;


import briillliin.dto.ClientsDTO;
import briillliin.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientsRepository extends JpaRepository<Clients, Long> {
}
