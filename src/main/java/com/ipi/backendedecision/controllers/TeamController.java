package com.ipi.backendedecision.controllers;

import com.ipi.backendedecision.repositories.TeamRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }



}
