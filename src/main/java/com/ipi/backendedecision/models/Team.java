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
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private int id;

    private String teamName;

    private TeamType type;

    @ManyToMany
    private List<User> users;

    public Team(String teamName, TeamType type, List<User> users) {
        this.teamName = teamName;
        this.type = type;
        this.users = users;
    }
}
