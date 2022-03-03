package com.ipi.backendedecision.repositories;

import com.ipi.backendedecision.models.Project;
import com.ipi.backendedecision.models.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Integer> {

    /*@Query("SELECT * FROM PROJECT_PROJECT_TEAMS WHERE  ")
    Optional<List<Team>> findAllTeamsInProject(Integer projectId);*/
}
