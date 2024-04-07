package briillliin.controller;

import briillliin.dto.TrainersDTO;
import briillliin.services.TrainersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/trainers")
@Tag(name="Контроллер тренеров", description="Операции CRUD для тренеров")
public class TrainersController {

    private final TrainersService trainersService;

    @Operation(summary = "Получить всех тренеров", description = "Получить список всех тренеров")
    @GetMapping
    public ResponseEntity<List<TrainersDTO>> getAllTrainers() {
        List<TrainersDTO> trainers = trainersService.getAll();
        return ResponseEntity.ok().body(trainers);
    }

    @Operation(summary = "Создать нового тренера", description = "Создать нового тренера")
    @PostMapping
    public ResponseEntity<TrainersDTO> newTrainer(@RequestBody TrainersDTO newTrainer) {
        TrainersDTO createdTrainer = trainersService.addTrainers(newTrainer);
        return ResponseEntity.ok().body(createdTrainer);
    }

    @Operation(summary = "Получить тренера по ID", description = "Получить тренера по его ID")
    @GetMapping("/{id}")
    public ResponseEntity<TrainersDTO> getTrainerById(@PathVariable Long id) {
        TrainersDTO trainer = trainersService.getTrainersById(id);
        return ResponseEntity.ok().body(trainer);
    }

    @Operation(summary = "Обновить тренера", description = "Обновить существующего тренера")
    @PutMapping("/{id}")
    public ResponseEntity<TrainersDTO> updateTrainer(@RequestBody TrainersDTO newTrainer, @PathVariable Long id) {
        TrainersDTO updatedTrainer = trainersService.updateTrainers(id, newTrainer);
        return ResponseEntity.ok().body(updatedTrainer);
    }

    @Operation(summary = "Удалить тренера", description = "Удалить тренера по его ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable Long id) {
        trainersService.deleteTrainersById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Найти всех тренеров по имени", description = "Поиск тренеров по части имени")
    @GetMapping("/find/{name}")
    public ResponseEntity<List<TrainersDTO>> findAllTrainersByName(@PathVariable String name) {
        List<TrainersDTO> trainers = trainersService.findTrainersByNameContaining(name);
        return ResponseEntity.ok().body(trainers);
    }
}
