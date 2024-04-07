package briillliin.services.serviceImpl;

import briillliin.controller.errors.TrainersNotFoundException;
import briillliin.dto.TrainersDTO;
import briillliin.entity.Trainers;
import briillliin.repository.TrainersRepository;
import briillliin.services.TrainersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TrainersServiceImpl implements TrainersService {

    private final TrainersRepository trainersRepository;


    @Override
    public List<TrainersDTO> getAll() {
        List<TrainersDTO> trainersDTOList = new ArrayList<>();
        List<Trainers> trainersList = trainersRepository.findAll();
        for (Trainers trainer : trainersList) {
            trainersDTOList.add(TrainersDTO.from(trainer));
        }
        return trainersDTOList;
    }

    @Override
    public TrainersDTO getTrainersById(Long id) {
        Trainers trainer = trainersRepository.findById(id)
                .orElseThrow(() -> new TrainersNotFoundException(id));
        return TrainersDTO.from(trainer);
    }

    @Override
    public TrainersDTO updateTrainers(Long id, TrainersDTO updatedTrainer) {
        Trainers existingTrainer = trainersRepository.findById(id)
                .orElseThrow(() -> new TrainersNotFoundException(id));
        existingTrainer.setName(updatedTrainer.getName());
        existingTrainer.setPassport(updatedTrainer.getPassport());
        existingTrainer.setPhone(updatedTrainer.getPhone());
        existingTrainer.setAddress(updatedTrainer.getAddress());
        return TrainersDTO.from(trainersRepository.save(existingTrainer));
    }

    @Override
    public TrainersDTO addTrainers(TrainersDTO newTrainer) {
        Trainers trainer = new Trainers();
        trainer.setName(newTrainer.getName());
        trainer.setPassport(newTrainer.getPassport());
        trainer.setPhone(newTrainer.getPhone());
        trainer.setAddress(newTrainer.getAddress());
        return TrainersDTO.from(trainersRepository.save(trainer));
    }

    @Override
    public void deleteTrainersById(Long id) {
        trainersRepository.deleteById(id);
    }

    public List<TrainersDTO> findTrainersByNameContaining(String name) {
        List<TrainersDTO> trainersDTOList = new ArrayList<>();
        List<Trainers> trainersList = trainersRepository.findByNameContaining(name);
        for (Trainers trainer : trainersList) {
            trainersDTOList.add(TrainersDTO.from(trainer));
        }
        return trainersDTOList;
    }
}
