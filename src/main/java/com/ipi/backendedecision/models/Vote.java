package com.ipi.backendedecision.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private User user;

    @OneToOne
    @JsonIgnore
    private Proposal proposal;

    private VoteType voteType;

    public Vote(User user, Proposal proposal, VoteType voteType) {
        this.user = user;
        this.proposal = proposal;
        this.voteType = voteType;
    }
}
