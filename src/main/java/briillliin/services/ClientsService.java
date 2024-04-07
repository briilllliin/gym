package briillliin.services;

import briillliin.dto.AreasDTO;
import briillliin.dto.ClientsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientsService {

    List<ClientsDTO> getAll();

    ClientsDTO getClientsById(Long id);

    ClientsDTO updateClients(Long id, ClientsDTO clients);

    ClientsDTO addClients(ClientsDTO clients);

    void deleteClientsById(Long id);

}