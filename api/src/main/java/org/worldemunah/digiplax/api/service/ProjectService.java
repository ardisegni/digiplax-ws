package org.worldemunah.digiplax.api.service;

import org.worldemunah.digiplax.api.model.ProjectModel;

import java.util.List;

/**
 * Project: Ariel
 * Date: 5/29/2019
 */
public interface ProjectService {

    Long getProjectId(String projectUrlParam);

    List<ProjectModel> getProjects();

    void createProject(ProjectModel projectModel);

    void updateProject(Long id, ProjectModel projectModel);

    void deleteProject(Long id);
}
