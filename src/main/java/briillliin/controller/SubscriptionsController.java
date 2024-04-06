package briillliin.controller;


import briillliin.controller.errors.SubscriptionsNotFoundException;
import briillliin.entity.Activities;
import briillliin.entity.Clients;
import briillliin.entity.Subscriptions;
import briillliin.repository.ActivitiesRepository;
import briillliin.repository.ClientsRepository;
import briillliin.repository.SubscriptionsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class SubscriptionsController {

    private final SubscriptionsRepository subscriptionsRepository;
    private final ActivitiesRepository activitiesRepository;
    private final ClientsRepository clientsRepository;


    public SubscriptionsController(SubscriptionsRepository subscriptionsRepository, ActivitiesRepository activitiesRepository, ClientsRepository clientsRepository) {
        this.subscriptionsRepository = subscriptionsRepository;
        this.activitiesRepository = activitiesRepository;
        this.clientsRepository = clientsRepository;
    }


    @GetMapping("/subscriptions")
    List<Subscriptions> all() {
        return subscriptionsRepository.findAll();
    }


    @PostMapping("/subscriptions")
    Subscriptions newSubscription(@RequestBody Subscriptions newSubscription) {
        return subscriptionsRepository.save(newSubscription);
    }


    @GetMapping("/subscriptions/{id}")
    Subscriptions one(@PathVariable Long id) {

        return subscriptionsRepository.findById(id)
                .orElseThrow(() -> new SubscriptionsNotFoundException(id));
    }


    @PutMapping("/subscriptions/{id}")
    Subscriptions replaceClient(@RequestBody Subscriptions newSubscription, @PathVariable Long id) {

        Optional<Activities> activity = activitiesRepository.findById(newSubscription.getActivity().getId());
        Optional<Clients> client = clientsRepository.findById(newSubscription.getClient().getId());

        return subscriptionsRepository.findById(id)
                .map(subscription -> {
                    subscription.setClient(client.get());
                    subscription.setActivity(activity.get());
                    subscription.setDate(newSubscription.getDate());
                    subscription.setPrice(newSubscription.getPrice());
                    return subscriptionsRepository.save(subscription);
                })
                .orElseGet(() -> {
                    newSubscription.setId(id);
                    return subscriptionsRepository.save(newSubscription);
                });
    }


    @DeleteMapping("/subscriptions/{id}")
    void deleteClient(@PathVariable Long id) {
        subscriptionsRepository.deleteById(id);
    }
}
