package briillliin.services.serviceImpl;

import briillliin.controller.errors.ActivitiesNotFoundException;
import briillliin.controller.errors.AreasNotFoundException;
import briillliin.controller.errors.TrainersNotFoundException;
import briillliin.dto.ActivitiesDTO;
import briillliin.entity.Activities;
import briillliin.entity.Areas;
import briillliin.entity.Trainers;
import briillliin.repository.ActivitiesRepository;
import briillliin.repository.AreasRepository;
import briillliin.repository.TrainersRepository;
import briillliin.services.ActivitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ActivitiesServiceImpl implements ActivitiesService {

    private final ActivitiesRepository activitiesRepository;
    private final TrainersRepository trainersRepository;
    private final AreasRepository areasRepository;


    @Override
    public List<ActivitiesDTO> getAll() {
        List<Activities> activities = activitiesRepository.findAll();
        return ActivitiesDTO.from(activities);
    }

    @Override
    public ActivitiesDTO getActivitiesById(Long id) {
        Activities activity = activitiesRepository.findById(id)
                .orElseThrow(() -> new ActivitiesNotFoundException(id));
        return ActivitiesDTO.from(activity);
    }

    @Override
    public ActivitiesDTO updateActivities(Long id, ActivitiesDTO updatedActivityDTO) {
        Activities existingActivity = activitiesRepository.findById(id)
                .orElseThrow(() -> new ActivitiesNotFoundException(id));

        Trainers trainer = trainersRepository.findById(updatedActivityDTO.getTrainerId())
                .orElseThrow(() -> new TrainersNotFoundException(updatedActivityDTO.getTrainerId()));

        Areas area = areasRepository.findById(updatedActivityDTO.getAreaId())
                .orElseThrow(() -> new AreasNotFoundException(updatedActivityDTO.getAreaId()));

        existingActivity.setName(updatedActivityDTO.getName());
        existingActivity.setTrainer(trainer);
        existingActivity.setArea(area);

        Activities updatedActivity = activitiesRepository.save(existingActivity);

        return ActivitiesDTO.from(updatedActivity);
    }

    @Override
    public void deleteActivitiesById(Long id) {
        if (!activitiesRepository.existsById(id)) {
            throw new ActivitiesNotFoundException(id);
        }
        activitiesRepository.deleteById(id);
    }

    @Override
    public ActivitiesDTO createActivity(ActivitiesDTO newActivityDTO) {
        Activities newActivity = Activities.builder()
                .name(newActivityDTO.getName())
                .build();

        Trainers trainer = trainersRepository.findById(newActivityDTO.getTrainerId())
                .orElseThrow(() -> new TrainersNotFoundException(newActivityDTO.getTrainerId()));

        Areas area = areasRepository.findById(newActivityDTO.getAreaId())
                .orElseThrow(() -> new AreasNotFoundException(newActivityDTO.getAreaId()));

        newActivity.setTrainer(trainer);
        newActivity.setArea(area);

        Activities savedActivity = activitiesRepository.save(newActivity);

        return ActivitiesDTO.from(savedActivity);
    }

    @Override
    public ActivitiesDTO getActivitiesByClientId(Long clientId) {
        return null;
    }


}
