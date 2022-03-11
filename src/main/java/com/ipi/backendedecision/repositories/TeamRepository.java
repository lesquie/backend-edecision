package com.ipi.backendedecision.repositories;

import com.ipi.backendedecision.models.Team;
import com.ipi.backendedecision.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    //@Query("")
   // List<User> findAllUsersInTeam(Integer teamId);

}
