package com.ipi.backendedecision.repositories;

import com.ipi.backendedecision.models.Proposal;
import com.ipi.backendedecision.models.Team;
import com.ipi.backendedecision.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    //@Query()
    // List<Team> findAllTeamsForUser(Integer userId);

    //@Query()
    //List<Proposal> findAllProposalsForUser(Integer userId);

}
