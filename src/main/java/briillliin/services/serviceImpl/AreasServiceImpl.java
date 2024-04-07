package briillliin.services.serviceImpl;

import briillliin.controller.errors.AreasNotFoundException;
import briillliin.dto.AreasDTO;
import briillliin.entity.Areas;
import briillliin.repository.AreasRepository;
import briillliin.services.AreasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AreasServiceImpl implements AreasService {

    private final AreasRepository areasRepository;

    @Override
    public List<AreasDTO> getAll() {
        List<AreasDTO> areasDTOList = new ArrayList<>();
        List<Areas> areasList = areasRepository.findAll();
        for (Areas area : areasList) {
            areasDTOList.add(AreasDTO.from(area));
        }
        return areasDTOList;
    }

    @Override
    public AreasDTO getAreasById(Long id) {
        Areas area = areasRepository.findById(id)
                .orElseThrow(() -> new AreasNotFoundException(id));
        return AreasDTO.from(area);
    }

    @Override
    public AreasDTO updateAreas(Long id, AreasDTO updatedArea) {
        Areas existingArea = areasRepository.findById(id)
                .orElseThrow(() -> new AreasNotFoundException(id));
        existingArea.setName(updatedArea.getName());
        return AreasDTO.from(areasRepository.save(existingArea));
    }

    @Override
    public AreasDTO addAreas(AreasDTO newArea) {
        Areas area = new Areas();
        area.setName(newArea.getName());
        return AreasDTO.from(areasRepository.save(area));
    }

    @Override
    public void deleteAreasById(Long id) {
        areasRepository.deleteById(id);
    }
}
