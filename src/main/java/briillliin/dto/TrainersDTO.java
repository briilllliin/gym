package briillliin.dto;

import briillliin.entity.Subscriptions;
import briillliin.entity.Trainers;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainersDTO {


    private Long trainersId;

    private String address;

    private String name;

    private String passport;

    private String phone;

    public static TrainersDTO from(Trainers trainers) {
        return TrainersDTO.builder()
                .trainersId(trainers.getId())
                .address(trainers.getAddress())
                .name(trainers.getName())
                .passport(trainers.getPassport())
                .phone(trainers.getPhone())
                .build();
    }
    public static List<TrainersDTO> from(List<Trainers> trainers) {
        return trainers.stream().map(TrainersDTO::from).toList();
    }

}