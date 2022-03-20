package com.ipi.backendedecision.controllers;

import com.ipi.backendedecision.exceptions.ProjectNotFoundException;
import com.ipi.backendedecision.models.Project;
import com.ipi.backendedecision.repositories.ProjectRepository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/project")
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping("/project/{id}")
    public Project updateProject(@PathVariable Integer id, @RequestBody Project newProject) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(newProject.getName());
                    project.setTeams(newProject.getTeams());
                    return project;
                })
                .orElseGet(() -> {
                    newProject.setId(id);
                    return projectRepository.save(newProject);
                });
    }

    @DeleteMapping("/project/{id}")
    public void deleteProject(@PathVariable Integer id) {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        projectRepository.delete(project);
    }

}
