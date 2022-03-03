package com.ipi.backendedecision.repositories;

import com.ipi.backendedecision.models.Proposal;
import com.ipi.backendedecision.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProposalRepository extends CrudRepository<Proposal, Integer> {

    //@Query()
    //List<User> findAllOwners(Integer proposalId);
}