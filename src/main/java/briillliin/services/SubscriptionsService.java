package briillliin.services;

import briillliin.dto.ClientsDTO;
import briillliin.dto.SubscriptionsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubscriptionsService {

    List<SubscriptionsDTO> getAll();

    SubscriptionsDTO getSubscriptionsById(Long id);

    SubscriptionsDTO updateSubscriptions(Long id, SubscriptionsDTO subscriptions);

    SubscriptionsDTO addSubscriptions(SubscriptionsDTO subscriptions);

    void deleteSubscriptionsById(Long id);
}
