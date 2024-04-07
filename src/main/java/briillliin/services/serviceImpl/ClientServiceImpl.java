package briillliin.services.serviceImpl;

import briillliin.controller.errors.ClientsNotFoundException;
import briillliin.dto.ClientsDTO;
import briillliin.entity.Clients;
import briillliin.repository.ClientsRepository;
import briillliin.services.ClientsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientsService {

    private final ClientsRepository clientsRepository;

    @Override
    public List<ClientsDTO> getAll() {
        List<ClientsDTO> clientsDTOList = new ArrayList<>();
        List<Clients> clientsList = clientsRepository.findAll();
        for (Clients client : clientsList) {
            clientsDTOList.add(ClientsDTO.from(client));
        }
        return clientsDTOList;
    }

    @Override
    public ClientsDTO getClientsById(Long id) {
        Clients client = clientsRepository.findById(id)
                .orElseThrow(() -> new ClientsNotFoundException(id));
        return ClientsDTO.from(client);
    }

    @Override
    public ClientsDTO updateClients(Long id, ClientsDTO updatedClient) {
        Clients existingClient = clientsRepository.findById(id)
                .orElseThrow(() -> new ClientsNotFoundException(id));
        existingClient.setName(updatedClient.getName());
        existingClient.setPassport(updatedClient.getPassport());
        existingClient.setPhone(updatedClient.getPhone());
        return ClientsDTO.from(clientsRepository.save(existingClient));
    }

    @Override
    public ClientsDTO addClients(ClientsDTO newClient) {
        Clients client = new Clients();
        client.setName(newClient.getName());
        client.setPassport(newClient.getPassport());
        client.setPhone(newClient.getPhone());
        return ClientsDTO.from(clientsRepository.save(client));
    }

    @Override
    public void deleteClientsById(Long id) {
        clientsRepository.deleteById(id);
    }
}
