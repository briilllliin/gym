package briillliin.controller;

import briillliin.dto.SubscriptionsDTO;
import briillliin.services.SubscriptionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subscriptions")
@Tag(name="Контроллер абонементов", description="Операции CRUD для абонементов")
public class SubscriptionsController {

    private final SubscriptionsService subscriptionsService;

    @Operation(summary = "Получить все абонементы", description = "Получить список всех абонементов")
    @GetMapping
    public ResponseEntity<List<SubscriptionsDTO>> getAllSubscriptions() {
        List<SubscriptionsDTO> subscriptions = subscriptionsService.getAll();
        return ResponseEntity.ok().body(subscriptions);
    }

    @Operation(summary = "Создать новый абонемент", description = "Создать новый абонемент")
    @PostMapping
    public ResponseEntity<SubscriptionsDTO> newSubscription(@RequestBody SubscriptionsDTO newSubscription) {
        SubscriptionsDTO createdSubscription = subscriptionsService.addSubscriptions(newSubscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubscription);
    }

    @Operation(summary = "Получить абонемент по ID", description = "Получить абонемент по его ID")
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionsDTO> getSubscriptionById(@PathVariable Long id) {
        SubscriptionsDTO subscription = subscriptionsService.getSubscriptionsById(id);
        return ResponseEntity.ok().body(subscription);
    }

    @Operation(summary = "Обновить абонемент", description = "Обновить существующий абонемент")
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionsDTO> updateSubscription(@RequestBody SubscriptionsDTO newSubscription, @PathVariable Long id) {
        SubscriptionsDTO updatedSubscription = subscriptionsService.updateSubscriptions(id, newSubscription);
        return ResponseEntity.ok().body(updatedSubscription);
    }

    @Operation(summary = "Удалить абонемент", description = "Удалить абонемент по его ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionsService.deleteSubscriptionsById(id);
        return ResponseEntity.noContent().build();
    }
}
