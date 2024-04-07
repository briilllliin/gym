package briillliin.services;

import briillliin.dto.ActivitiesDTO;
import briillliin.entity.Activities;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivitiesService {

    List<ActivitiesDTO> getAll();

    ActivitiesDTO getActivitiesById(Long id);

    public ActivitiesDTO updateActivities(Long id, ActivitiesDTO updatedActivity);

    void deleteActivitiesById(Long id);

    ActivitiesDTO getActivitiesByClientId(Long clientId);

    ActivitiesDTO createActivity(ActivitiesDTO newActivity);
}
