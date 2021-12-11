package org.worldemunah.digiplax.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.worldemunah.digiplax.api.dao.PlaqueDao;
import org.worldemunah.digiplax.api.dao.ScheduledPlaqueDao;
import org.worldemunah.digiplax.api.model.StreamingModel;
import org.worldemunah.digiplax.api.service.ProjectService;
import org.worldemunah.digiplax.api.service.StreamingService;
import org.worldemunah.digiplax.persistence.main.Plaque;
import org.worldemunah.digiplax.persistence.main.ScheduledPlaque;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: Ariel
 * Date: 5/29/2019
 */
@Service
public class StreamingServiceImpl implements StreamingService {

    @Autowired
    private PlaqueDao plaqueDao;

    @Autowired
    private ScheduledPlaqueDao scheduledPlaqueDao;

    @Autowired
    private ProjectService projectService;

    @Override
    public List<StreamingModel> getStreamingPlaques(String projectUrlParam) {
        Long projectId = projectService.getProjectId(projectUrlParam);
        if (projectId == null) {
            return Collections.emptyList();
        }

        List<Plaque> streamingPlaques = plaqueDao.listByProject(projectId);
        return streamingPlaques
                .stream()
                .map(plaque -> new StreamingModel(plaque.getPlaqueText(), plaque.getPlaqueHtmlText()))
                .collect(Collectors.toList());
    }

    @Override
    public List<StreamingModel> getScheduledPlaques(String project) {
        Long projectId = projectService.getProjectId(project);
        List<ScheduledPlaque> scheduledPlaques = scheduledPlaqueDao.listActive(projectId);
        return scheduledPlaques
                .stream()
                .map(scheduledPlaque -> {
                    Plaque plaque = scheduledPlaque.getPlaque();
                    return new StreamingModel(plaque.getPlaqueText(), plaque.getPlaqueHtmlText());
                })
                .collect(Collectors.toList());
    }
}
