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
    @Column(name = "project_id")
    private int id;

    private String name;

    @ManyToMany
    private List<Team> teams;

    public Project(String name, List<Team> teams) {
        this.name = name;
        this.teams = teams;
    }
}
