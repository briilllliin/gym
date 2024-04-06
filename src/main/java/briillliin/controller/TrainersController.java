package briillliin.controller;


import briillliin.controller.errors.TrainersNotFoundException;
import briillliin.entity.Trainers;
import briillliin.repository.TrainersRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TrainersController {

    private final TrainersRepository trainersRepository;


    public TrainersController(TrainersRepository trainersRepository) {
        this.trainersRepository = trainersRepository;
    }


    @GetMapping("/trainers")
    List<Trainers> all() {
        return trainersRepository.findAll();
    }


    @PostMapping("/trainers")
    Trainers newTrainer(@RequestBody Trainers newTrainer) {
        return trainersRepository.save(newTrainer);
    }


    @GetMapping("/trainers/{id}")
    Trainers one(@PathVariable Long id) {

        return trainersRepository.findById(id)
                .orElseThrow(() -> new TrainersNotFoundException(id));
    }


    @PutMapping("/trainers/{id}")
    Trainers replaceTrainer(@RequestBody Trainers newTrainer, @PathVariable Long id) {

        return trainersRepository.findById(id)
                .map(trainer -> {
                    trainer.setName(newTrainer.getName());
                    trainer.setPassport(newTrainer.getPassport());
                    trainer.setPhone(newTrainer.getPhone());
                    trainer.setAddress(newTrainer.getAddress());
                    return trainersRepository.save(trainer);
                })
                .orElseGet(() -> {
                    newTrainer.setId(id);
                    return trainersRepository.save(newTrainer);
                });
    }


    @DeleteMapping("/trainers/{id}")
    void deleteTrainer(@PathVariable Long id) {
        trainersRepository.deleteById(id);
    }


    @GetMapping("/trainers/find/{name}")
    public List<Trainers> findAll(@PathVariable String name) {
        return trainersRepository.findByNameContaining(name);
    }

}

