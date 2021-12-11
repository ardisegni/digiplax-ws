package org.worldemunah.digiplax.api.service;

import org.springframework.web.multipart.MultipartFile;
import org.worldemunah.digiplax.api.model.PlaqueModel;
import org.worldemunah.digiplax.api.model.ScheduledPlaqueModel;

import java.util.List;

/**
 * User: Ariel
 * Date: 4/14/2019
 */
public interface PlaqueService {

    List<PlaqueModel> importExcelFile(MultipartFile file);

    void createPlaque(PlaqueModel plaque);

    void updatePlaque(Long id, PlaqueModel plaque);

    void deletePlaque(Long id);

    List<PlaqueModel> getPlaques(Long projectId);

    void createOrUpdatePlaque(PlaqueModel plaque);

    List<ScheduledPlaqueModel> getActiveScheduledPlaques();

    List<ScheduledPlaqueModel> getPastScheduledPlaques();

    void createScheduledPlaque(ScheduledPlaqueModel scheduledPlaqueModel);

    void updateScheduledPlaque(Long id, ScheduledPlaqueModel scheduledPlaque);

    void deleteScheduledPlaque(Long id);
}
