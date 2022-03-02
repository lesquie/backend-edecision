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
public class Proposal {

    @Id
    @GeneratedValue
    private int proposalId;

    private String title;
    private String description;
    private PublicationLevel publicationLevel;
    private int votesYes;
    private int votesNo;
    private int abstentionVotes;

    @OneToMany
    private List<User> owners;
}
