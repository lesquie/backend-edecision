package com.ipi.backendedecision.repositories;

import com.ipi.backendedecision.models.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends CrudRepository<Vote, Integer> {

    @Query(value = "SELECT v FROM Vote v WHERE v.proposal.proposalId = ?1")
    Optional<List<Vote>> findAllVotesForProposal(Integer proposalId);
}
