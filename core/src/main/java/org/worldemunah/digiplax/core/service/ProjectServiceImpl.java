package org.worldemunah.digiplax.core.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.worldemunah.digiplax.api.dao.PlaqueDao;
import org.worldemunah.digiplax.api.dao.ProjectDao;
import org.worldemunah.digiplax.api.model.ProjectModel;
import org.worldemunah.digiplax.api.service.ProjectService;
import org.worldemunah.digiplax.persistence.main.Plaque;
import org.worldemunah.digiplax.persistence.main.Project;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Project: Ariel
 * Date: 5/29/2019
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private PlaqueDao plaqueDao;

    private Map<String, Long> projectsMap;

    @PostConstruct
    private void initCache() {
        List<Project> projects = projectDao.listAll();
        Map<String, Long> currentData = new HashMap<>(projects.size());
        for (Project project : projects) {
            currentData.put(project.getUrlParam(), project.getId());
        }

        projectsMap = currentData;
    }

    @Override
    public Long getProjectId(String projectUrlParam) {
        return projectsMap.get(projectUrlParam);
    }

    @Override
    public List<ProjectModel> getProjects() {
        List<Project> projects = projectDao.listAll();
        return projects.stream()
                .map(project -> new ProjectModel(
                        project.getId(),
                        project.getName(),
                        project.getTimezone(),
                        project.getUrlParam()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createProject(ProjectModel projectModel) {
        String name = projectModel.getName();
        Project project = projectDao.findByName(name);
        if (project != null) {
            throw new RuntimeException("Project with this name already exists");
        }

        project = new Project(name);
        project.setTimezone(projectModel.getTimezone());
        project.setUrlParam(projectModel.getUrlParam());
        project.setDateCreated(new Date());
        project.setUserKey(1L);

        projectDao.insertEntity(project);

        initCache();
    }

    @Override
    public void updateProject(Long id, ProjectModel projectModel) {
        Project project = projectDao.findById(id);
        if (project == null) {
            throw new RuntimeException("Project with id " + id + " does not exist");
        }

        String name = projectModel.getName();
        if (StringUtils.isNotEmpty(name)) {
            project.setName(name);
        }

        String urlParam = projectModel.getUrlParam();
        if (StringUtils.isNotEmpty(urlParam)) {
            project.setUrlParam(urlParam);
        }

        project.setTimezone(projectModel.getTimezone());

        projectDao.updateEntity(project);

        initCache();
    }

    @Override
    public void deleteProject(Long id) {
        List<Plaque> plaques = plaqueDao.listByProject(id);
        for (Plaque plaque : plaques) {
            plaqueDao.deleteEntity(plaque);
        }

        Project project = projectDao.findById(id);
        if (project == null) {
            throw new RuntimeException("Prroject with id " + id + " does not exist");
        }

        projectDao.deleteEntity(project);

        initCache();
    }
}
