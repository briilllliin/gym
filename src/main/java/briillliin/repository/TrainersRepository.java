package briillliin.repository;


import briillliin.dto.TrainersDTO;
import briillliin.entity.Trainers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TrainersRepository extends JpaRepository<Trainers, Long> {
    List<Trainers> findByNameContaining(String name);
}
