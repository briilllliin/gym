package briillliin.dto;

import briillliin.entity.Areas;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreasDTO {


    private Long areasId;

    private String name;

    public static AreasDTO from(Areas areas) {
        return AreasDTO.builder()
                .areasId(areas.getId())
                .name(areas.getName())
                .build();
    }
    public static List<AreasDTO> from(List<Areas> areas) {
        return areas.stream().map(AreasDTO::from).toList();
    }

}
