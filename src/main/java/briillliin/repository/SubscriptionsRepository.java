package briillliin.repository;

import briillliin.dto.SubscriptionsDTO;
import briillliin.entity.Subscriptions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubscriptionsRepository extends JpaRepository<Subscriptions, Long> {
}
