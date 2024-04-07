package briillliin.services.serviceImpl;

import briillliin.controller.errors.ActivitiesNotFoundException;
import briillliin.controller.errors.SubscriptionsNotFoundException;
import briillliin.controller.errors.TrainersNotFoundException;
import briillliin.dto.ActivitiesDTO;
import briillliin.dto.ClientsDTO;
import briillliin.dto.SubscriptionsDTO;
import briillliin.entity.Activities;
import briillliin.entity.Clients;
import briillliin.entity.Subscriptions;
import briillliin.entity.Trainers;
import briillliin.repository.ActivitiesRepository;
import briillliin.repository.ClientsRepository;
import briillliin.repository.SubscriptionsRepository;
import briillliin.services.SubscriptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubscriptionsServiceImpl implements SubscriptionsService {

    private final SubscriptionsRepository subscriptionsRepository;
    private final ActivitiesRepository activitiesRepository;
    private final ClientsRepository clientsRepository;

    @Override
    public List<SubscriptionsDTO> getAll() {
        List<SubscriptionsDTO> subscriptionsDTOList = new ArrayList<>();
        List<Subscriptions> subscriptionsList = subscriptionsRepository.findAll();
        for (Subscriptions subscription : subscriptionsList) {
            subscriptionsDTOList.add(SubscriptionsDTO.from(subscription));
        }
        return subscriptionsDTOList;
    }

    @Override
    public SubscriptionsDTO getSubscriptionsById(Long id) {
        Subscriptions subscription = subscriptionsRepository.findById(id)
                .orElseThrow(() -> new SubscriptionsNotFoundException(id));
        return SubscriptionsDTO.from(subscription);
    }

    @Override
    public SubscriptionsDTO updateSubscriptions(Long id, SubscriptionsDTO updatedSubscription) {
        Optional<Activities> activity = activitiesRepository.findById(updatedSubscription.getActivityId());
        Optional<Clients> client = clientsRepository.findById(updatedSubscription.getClientId());

        Subscriptions existingSubscription = subscriptionsRepository.findById(id)
                .orElseThrow(() -> new SubscriptionsNotFoundException(id));
        existingSubscription.setClient(client.get());
        existingSubscription.setActivity(activity.get());
        existingSubscription.setDate(updatedSubscription.getDate());
        existingSubscription.setPrice(updatedSubscription.getPrice());

        return SubscriptionsDTO.from(subscriptionsRepository.save(existingSubscription));
    }

    @Override
    public SubscriptionsDTO addSubscriptions(SubscriptionsDTO newSubscription) {
        Subscriptions subscription = new Subscriptions();
        Clients client = clientsRepository.findById(newSubscription.getClientId())
                .orElseThrow(() -> new TrainersNotFoundException(newSubscription.getClientId()));
        Activities activity = activitiesRepository.findById(newSubscription.getClientId())
                .orElseThrow(() -> new ActivitiesNotFoundException(newSubscription.getClientId()));
        subscription.setClient(client);
        subscription.setActivity(activity);
        subscription.setDate(newSubscription.getDate());
        subscription.setPrice(newSubscription.getPrice());

        return SubscriptionsDTO.from(subscriptionsRepository.save(subscription));
    }

    @Override
    public void deleteSubscriptionsById(Long id) {
        subscriptionsRepository.deleteById(id);
    }
}
