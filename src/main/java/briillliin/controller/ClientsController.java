package briillliin.controller;

import briillliin.controller.errors.ClientsNotFoundException;
import briillliin.entity.Clients;
import briillliin.repository.ClientsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClientsController {
    
    private final ClientsRepository clientsRepository;


    public ClientsController(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }


    @GetMapping("/clients")
    List<Clients> all() {
        return clientsRepository.findAll();
    }


    @PostMapping("/clients")
    Clients newClient(@RequestBody Clients newClient) {
        return clientsRepository.save(newClient);
    }


    @GetMapping("/clients/{id}")
    Clients one(@PathVariable Long id) {

        return clientsRepository.findById(id)
                .orElseThrow(() -> new ClientsNotFoundException(id));
    }


    @PutMapping("/clients/{id}")
    Clients replaceClient(@RequestBody Clients newClient, @PathVariable Long id) {

        return clientsRepository.findById(id)
                .map(client -> {
                    client.setName(newClient.getName());
                    client.setPassport(newClient.getPassport());
                    client.setPhone(newClient.getPhone());
                    return clientsRepository.save(client);
                })
                .orElseGet(() -> {
                    newClient.setId(id);
                    return clientsRepository.save(newClient);
                });
    }


    @DeleteMapping("/clients/{id}")
    void deleteClient(@PathVariable Long id) {
        clientsRepository.deleteById(id);
    }
}
