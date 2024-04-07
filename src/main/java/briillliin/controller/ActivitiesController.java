package briillliin.controller;

import briillliin.dto.ActivitiesDTO;
import briillliin.services.serviceImpl.ActivitiesServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/activities")
@Tag(name="Контроллер мероприятий", description="Операции CRUD для мероприятий")
public class ActivitiesController {

    private final ActivitiesServiceImpl activitiesService;

    @Operation(summary = "Получить все мероприятия", description = "Получить список всех мероприятий")
    @GetMapping
    public ResponseEntity<List<ActivitiesDTO>> getAllActivities() {
        List<ActivitiesDTO> activities = activitiesService.getAll();
        return ResponseEntity.ok().body(activities);
    }

    @Operation(summary = "Создать новое мероприятие", description = "Создать новое мероприятие")
    @PostMapping
    public ResponseEntity<Void> newActivity(@RequestBody ActivitiesDTO newActivity) {
        activitiesService.createActivity(newActivity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Получить мероприятие по ID", description = "Получить мероприятие по его ID")
    @GetMapping("/{id}")
    public ResponseEntity<ActivitiesDTO> getActivityById(@PathVariable Long id) {
        ActivitiesDTO activity = activitiesService.getActivitiesById(id);
        return ResponseEntity.ok().body(activity);
    }

    @Operation(summary = "Обновить мероприятие", description = "Обновить существующее мероприятие")
    @PutMapping("/{id}")
    public ResponseEntity<ActivitiesDTO> updateActivity(@RequestBody ActivitiesDTO newActivity, @PathVariable Long id) {
        ActivitiesDTO updatedActivity = activitiesService.updateActivities(id, newActivity);
        return ResponseEntity.ok().body(updatedActivity);
    }

    @Operation(summary = "Удалить мероприятие", description = "Удалить мероприятие по его ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        activitiesService.deleteActivitiesById(id);
        return ResponseEntity.noContent().build();
    }
}
