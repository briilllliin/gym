package briillliin.controller;

import briillliin.dto.AreasDTO;
import briillliin.services.AreasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/areas")
@Tag(name="Контроллер филиалов", description="Операции CRUD для филиалов")
public class AreasController {

    private final AreasService areasService;

    @Operation(summary = "Получить все филиалы", description = "Получить список всех филиалов")
    @GetMapping
    public ResponseEntity<List<AreasDTO>> getAllAreas() {
        List<AreasDTO> areas = areasService.getAll();
        return ResponseEntity.ok().body(areas);
    }

    @Operation(summary = "Создать новый филиал", description = "Создать новый филиал")
    @PostMapping
    public ResponseEntity<AreasDTO> newArea(@RequestBody AreasDTO newArea) {
        AreasDTO createdArea = areasService.addAreas(newArea);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArea);
    }

    @Operation(summary = "Получить филиал по ID", description = "Получить филиал по его ID")
    @GetMapping("/{id}")
    public ResponseEntity<AreasDTO> getAreaById(@PathVariable Long id) {
        AreasDTO area = areasService.getAreasById(id);
        return ResponseEntity.ok().body(area);
    }

    @Operation(summary = "Обновить филиал", description = "Обновить существующий филиал")
    @PutMapping("/{id}")
    public ResponseEntity<AreasDTO> updateArea(@RequestBody AreasDTO newArea, @PathVariable Long id) {
        AreasDTO updatedArea = areasService.updateAreas(id, newArea);
        return ResponseEntity.ok().body(updatedArea);
    }

    @Operation(summary = "Удалить филиал", description = "Удалить филиал по его ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArea(@PathVariable Long id) {
        areasService.deleteAreasById(id);
        return ResponseEntity.noContent().build();
    }
}
