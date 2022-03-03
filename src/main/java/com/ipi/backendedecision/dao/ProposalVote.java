package com.ipi.backendedecision.dao;

import com.ipi.backendedecision.models.Proposal;
import com.ipi.backendedecision.models.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProposalVote {
    private Proposal proposal;
    private List<Vote> votes;
    private int totalVotes;
}
