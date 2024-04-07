package briillliin.dto;

import briillliin.entity.Activities;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivitiesDTO {

    private Long activitiesId;
    private String name;
    private Long areaId;
    private Long trainerId;

    public static ActivitiesDTO from(Activities activities) {
        return ActivitiesDTO.builder()
                .activitiesId(activities.getId())
                .name(activities.getName())
                .areaId(activities.getArea().getId())
                .trainerId(activities.getTrainer().getId())
                .build();
    }

    public static List<ActivitiesDTO> from(List<Activities> activities) {
        return activities.stream().map(ActivitiesDTO::from).toList();
    }
}



