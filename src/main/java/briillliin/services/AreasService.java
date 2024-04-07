package briillliin.services;

import briillliin.dto.ActivitiesDTO;
import briillliin.dto.AreasDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AreasService {

    List<AreasDTO> getAll();

    AreasDTO getAreasById(Long id);

    AreasDTO updateAreas(Long id, AreasDTO areas);

    AreasDTO addAreas(AreasDTO areas);

    void deleteAreasById(Long id);

}
