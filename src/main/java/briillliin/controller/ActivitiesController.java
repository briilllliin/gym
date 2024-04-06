package briillliin.controller;

import briillliin.controller.errors.ActivitiesNotFoundException;
import briillliin.entity.Activities;
import briillliin.entity.Areas;
import briillliin.entity.Trainers;
import briillliin.repository.ActivitiesRepository;
import briillliin.repository.AreasRepository;
import briillliin.repository.TrainersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ActivitiesController {
    
    private final ActivitiesRepository activitiesRepository;
    private final TrainersRepository trainersRepository;
    private final AreasRepository areasRepository;


    public ActivitiesController(ActivitiesRepository activitiesRepository, TrainersRepository trainersRepository,
                                AreasRepository areasRepository) {
        this.activitiesRepository = activitiesRepository;
        this.trainersRepository = trainersRepository;
        this.areasRepository = areasRepository;
    }

    @GetMapping("/activities")
    List<Activities> all() {
        return activitiesRepository.findAll();
    }


    @PostMapping("/activities")
    public ResponseEntity<?> newActivity(@RequestBody Activities newActivity) {
        activitiesRepository.save(newActivity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/activities/{id}")
    Activities one(@PathVariable Long id) {

        return activitiesRepository.findById(id)
                .orElseThrow(() -> new ActivitiesNotFoundException(id));
    }


    @PutMapping("/activities/{id}")
    Activities replaceClient(@RequestBody Activities newActivity, @PathVariable Long id) {

        Optional<Trainers> trainer = trainersRepository.findById(newActivity.getTrainer().getId());
        Optional<Areas> area = areasRepository.findById(newActivity.getArea().getId());

        return activitiesRepository.findById(id)
                .map(activity -> {
                    activity.setName(newActivity.getName());
                    activity.setTrainer(trainer.get());
                    activity.setArea(area.get());
                    return activitiesRepository.save(activity);
                })
                .orElseGet(() -> {
                    newActivity.setId(id);
                    return activitiesRepository.save(newActivity);
                });
    }


    @DeleteMapping("/activities/{id}")
    void deleteClient(@PathVariable Long id) {
        activitiesRepository.deleteById(id);
    }
}
