package com.ipi.backendedecision.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Proposal {

    @Id
    @GeneratedValue
    @Column(name = "proposal_id")
    private int proposalId;

    private String title;
    private String description;
    private PublicationLevel publicationLevel;

    private LocalDate creationDate;
    private LocalDate closingDate;

    private boolean isActive;

    @ManyToMany
    private List<User> owners;

    @ManyToMany
    private List<Comment> comments;

    public Proposal(String title, String description, PublicationLevel publicationLevel, LocalDate closingDate, List<User> owners, List<Comment> comments) {
        this.title = title;
        this.description = description;
        this.publicationLevel = publicationLevel;
        this.creationDate = LocalDate.now();
        this.isActive = true;
        this.closingDate = closingDate;
        this.owners = owners;
        this.comments = comments;
    }

}
