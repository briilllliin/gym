package briillliin.controller;

import briillliin.dto.ClientsDTO;
import briillliin.services.ClientsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
@Tag(name="Контроллер клиентов", description="Операции CRUD для клиентов")
public class ClientsController {

    private final ClientsService clientsService;

    @Operation(summary = "Получить всех клиентов", description = "Получить список всех клиентов")
    @GetMapping
    public ResponseEntity<List<ClientsDTO>> getAllClients() {
        List<ClientsDTO> clients = clientsService.getAll();
        return ResponseEntity.ok().body(clients);
    }

    @Operation(summary = "Создать нового клиента", description = "Создать нового клиента")
    @PostMapping
    public ResponseEntity<ClientsDTO> newClient(@RequestBody ClientsDTO newClient) {
        ClientsDTO createdClient = clientsService.addClients(newClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @Operation(summary = "Получить клиента по ID", description = "Получить клиента по его ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClientsDTO> getClientById(@PathVariable Long id) {
        ClientsDTO client = clientsService.getClientsById(id);
        return ResponseEntity.ok().body(client);
    }

    @Operation(summary = "Обновить клиента", description = "Обновить существующего клиента")
    @PutMapping("/{id}")
    public ResponseEntity<ClientsDTO> updateClient(@RequestBody ClientsDTO newClient, @PathVariable Long id) {
        ClientsDTO updatedClient = clientsService.updateClients(id, newClient);
        return ResponseEntity.ok().body(updatedClient);
    }

    @Operation(summary = "Удалить клиента", description = "Удалить клиента по его ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientsService.deleteClientsById(id);
        return ResponseEntity.noContent().build();
    }
}
