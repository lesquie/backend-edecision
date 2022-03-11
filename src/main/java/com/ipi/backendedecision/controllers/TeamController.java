package com.ipi.backendedecision.controllers;

import com.ipi.backendedecision.exceptions.TeamNotFoundException;
import com.ipi.backendedecision.models.Team;
import com.ipi.backendedecision.repositories.TeamRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public List<Team> all() {
        return (List<Team>) teamRepository.findAll();
    }

    @GetMapping("/team/{id}")
    public Team findOne(@PathVariable Integer id) {
        return teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    }

    @PostMapping("/team")
    public Team add(@RequestBody Team team) {
        return teamRepository.save(team);
    }

    @PutMapping("/team/{id}")
    public Team updateTeam(@PathVariable Integer id, @RequestBody Team newTeam) {
        return teamRepository.findById(id).map(team -> {
            team.setTeamName(newTeam.getTeamName());
            team.setType(newTeam.getType());
            team.setUsers(newTeam.getUsers());
            return teamRepository.save(team);
        }).orElseGet(() -> {
            newTeam.setTeamId(id);
            return teamRepository.save(newTeam);
        });
    }

    @DeleteMapping("/team/{id}")
    public void delete(@PathVariable Integer id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
        teamRepository.delete(team);
    }

}
