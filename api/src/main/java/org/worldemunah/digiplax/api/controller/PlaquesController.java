package org.worldemunah.digiplax.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.worldemunah.digiplax.api.model.PlaqueModel;
import org.worldemunah.digiplax.api.model.ScheduledPlaqueModel;
import org.worldemunah.digiplax.api.service.PlaqueService;

import javax.validation.Valid;
import java.util.List;

/**
 * User: Ariel
 * Date: 4/1/2019
 */
@RestControllerAdvice
@CrossOrigin
@RequestMapping("/api")
public class PlaquesController extends BaseController {

    @Autowired
    private PlaqueService plaqueService;

    @PostMapping(value = "/uploadPlaquesFile")
    public ResponseEntity<?> uploadPlaquesFile(@RequestParam MultipartFile file) {
        return ResponseEntity.ok(plaqueService.importExcelFile(file));
    }

    @GetMapping(value = "/getPlaques")
    public ResponseEntity<?> getPlaques(@RequestParam Long project) {
        List<PlaqueModel> plaques = plaqueService.getPlaques(project);
        return ResponseEntity.ok(plaques);
    }

    @GetMapping(value = "/getActiveScheduledPlaques")
    public ResponseEntity<?> getActiveScheduledPlaques() {
        List<ScheduledPlaqueModel> plaques = plaqueService.getActiveScheduledPlaques();
        return ResponseEntity.ok(plaques);
    }

    @GetMapping(value = "/getPastScheduledPlaques")
    public ResponseEntity<?> getPastScheduledPlaques() {
        List<ScheduledPlaqueModel> plaques = plaqueService.getPastScheduledPlaques();
        return ResponseEntity.ok(plaques);
    }

    @PostMapping(value = "/createPlaque")
    public ResponseEntity<?> createPlaque(@RequestBody @Valid PlaqueModel plaque) {
        plaqueService.createPlaque(plaque);
        return ResponseEntity.ok().body("Success");
    }

    @PutMapping(value = "/updatePlaque/{id}")
    public ResponseEntity<?> updatePlaque(@RequestBody @Valid PlaqueModel plaque, @PathVariable Long id) {
        plaqueService.updatePlaque(id, plaque);
        return ResponseEntity.ok().body("Success");
    }

    @DeleteMapping(value = "/deletePlaque/{id}")
    public ResponseEntity<?> deletePlaque(@PathVariable Long id) {
        plaqueService.deletePlaque(id);
        return ResponseEntity.ok().body("Success");
    }

    @PostMapping(value = "/createOrUpdatePlaque")
    public ResponseEntity<?> createOrUpdatePlaque(@RequestBody @Valid PlaqueModel plaque) {
        plaqueService.createOrUpdatePlaque(plaque);
        return ResponseEntity.ok().body("Success");
    }

    @PostMapping(value = "/schedulePlaque")
    public ResponseEntity<?> schedulePlaque(@RequestBody @Valid ScheduledPlaqueModel scheduledPlaque) {
        plaqueService.createScheduledPlaque(scheduledPlaque);
        return ResponseEntity.ok().body("Success");
    }

    @PutMapping(value = "/updateScheduledPlaque/{id}")
    public ResponseEntity<?> updateScheduledPlaque(@RequestBody @Valid ScheduledPlaqueModel scheduledPlaque,
                                                   @PathVariable Long id) {
        plaqueService.updateScheduledPlaque(id, scheduledPlaque);
        return ResponseEntity.ok().body("Success");
    }

    @DeleteMapping(value = "/deleteScheduledPlaque/{id}")
    public ResponseEntity<?> deleteScheduledPlaque(@PathVariable Long id) {
        plaqueService.deleteScheduledPlaque(id);
        return ResponseEntity.ok().body("Success");
    }
}
