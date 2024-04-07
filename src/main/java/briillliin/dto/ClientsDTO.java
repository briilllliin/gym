package briillliin.dto;

import briillliin.entity.Clients;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientsDTO {


    private Long clientsId;

    private String name;

    private String passport;

    private String phone;

    public static ClientsDTO from(Clients clients) {
        return ClientsDTO.builder()
                .clientsId(clients.getId())
                .name(clients.getName())
                .passport(clients.getPassport())
                .phone(clients.getPhone())
                .build();
    }
    public static List<ClientsDTO> from(List<Clients> clients) {
        return clients.stream().map(ClientsDTO::from).toList();
    }

}
