package briillliin.services;

import briillliin.dto.SubscriptionsDTO;
import briillliin.dto.TrainersDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainersService {


    List<TrainersDTO> getAll();

    TrainersDTO getTrainersById(Long id);

    TrainersDTO updateTrainers(Long id, TrainersDTO trainers);

    TrainersDTO addTrainers(TrainersDTO trainers);

    void deleteTrainersById(Long id);
    List<TrainersDTO> findTrainersByNameContaining(String name);


}
