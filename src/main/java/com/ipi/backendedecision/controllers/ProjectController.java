package com.ipi.backendedecision.controllers;

import com.ipi.backendedecision.exceptions.ProjectNotFoundException;
import com.ipi.backendedecision.models.Project;
import com.ipi.backendedecision.models.Team;
import com.ipi.backendedecision.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/projects")
    public List<Project> all() {
        return (List<Project>) projectRepository.findAll();
    }

    @GetMapping("/project/{id}")
    public Project findById(@PathVariable Integer id) {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }

   /* @GetMapping("/project/{id}/teams")
    public List<Team> getAllTeamsForProject(@PathVariable Integer id) {
        return projectRepository.findAllTeamsInProject(id).orElseThrow(() -> new ProjectNotFoundException((id)));
    }
*/
}
