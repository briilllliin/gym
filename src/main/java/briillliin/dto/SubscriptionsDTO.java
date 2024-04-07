package briillliin.dto;

import briillliin.entity.Clients;
import briillliin.entity.Subscriptions;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionsDTO {


    private Long subscriptionsId;

    private Long clientId;

    private Long activityId;

    private Date date;

    private Long price;

    public static SubscriptionsDTO from(Subscriptions subscriptions) {
        return SubscriptionsDTO.builder()
                .date(subscriptions.getDate())
                .price(subscriptions.getPrice())
                .activityId(subscriptions.getActivity().getId())
                .clientId(subscriptions.getClient().getId())
                .subscriptionsId(subscriptions.getId())
                .build();
    }
    public static List<SubscriptionsDTO> from(List<Subscriptions> subscriptions) {
        return subscriptions.stream().map(SubscriptionsDTO::from).toList();
    }

}
