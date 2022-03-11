package com.ipi.backendedecision.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue
    private int projectId;

    private String name;

    @ManyToMany
    private List<Team> projectTeams;

    public Project(String name, List<Team> projectTeams) {
        this.name = name;
        this.projectTeams = projectTeams;
    }
}
