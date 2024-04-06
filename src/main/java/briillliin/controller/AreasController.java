package briillliin.controller;


import briillliin.controller.errors.AreasNotFoundException;
import briillliin.entity.Areas;
import briillliin.repository.AreasRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AreasController {
    
    private final AreasRepository areasRepository;

    public AreasController(AreasRepository areasRepository) {
        this.areasRepository = areasRepository;
    }


    @GetMapping("/areas")
    List<Areas> all() {
        return areasRepository.findAll();
    }

    @PostMapping("/areas")
    Areas newArea(@RequestBody Areas newArea) {
        return areasRepository.save(newArea);
    }


    @GetMapping("/areas/{id}")
    Areas one(@PathVariable Long id) {

        return areasRepository.findById(id)
                .orElseThrow(() -> new AreasNotFoundException(id));
    }


    @PutMapping("/areas/{id}")
    Areas replaceClient(@RequestBody Areas newArea, @PathVariable Long id) {

        return areasRepository.findById(id)
                .map(area -> {
                    area.setName(newArea.getName());
                    return areasRepository.save(area);
                })
                .orElseGet(() -> {
                    newArea.setId(id);
                    return areasRepository.save(newArea);
                });
    }


    @DeleteMapping("/areas/{id}")
    void deleteClient(@PathVariable Long id) {
        areasRepository.deleteById(id);
    }
}
