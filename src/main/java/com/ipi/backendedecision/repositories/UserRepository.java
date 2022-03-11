package com.ipi.backendedecision.repositories;

import com.ipi.backendedecision.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u from User u WHERE u.username = ?1 and u.password = ?2")
    Optional<User> findUserByUsernameAndPassword(String username, String password);
}
