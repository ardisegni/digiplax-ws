package org.worldemunah.digiplax.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.worldemunah.digiplax.api.model.ProjectModel;
import org.worldemunah.digiplax.api.service.ProjectService;

/**
 * Project: Ariel
 * Date: 5/29/2019
 */
@RestControllerAdvice
@CrossOrigin
@RequestMapping(value = "/api")
public class ProjectsController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ProjectsController.class);

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/getProjects")
    public ResponseEntity<?> getProjects() {
        return ResponseEntity.ok(projectService.getProjects());
    }

    @PostMapping(value = "/createProject")
    public ResponseEntity<?> createProject(@RequestBody ProjectModel projectModel) {
        projectService.createProject(projectModel);
        return ResponseEntity.ok("Success");
    }

    @PutMapping(value = "/updateProject/{id}")
    public ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody ProjectModel projectModel) {
        projectService.updateProject(id, projectModel);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping(value = "/deleteProject/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Success");
    }
}
