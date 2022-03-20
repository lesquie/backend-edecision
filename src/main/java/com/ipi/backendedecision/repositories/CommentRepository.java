package com.ipi.backendedecision.repositories;

import com.ipi.backendedecision.models.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    @Query("SELECT p.comments FROM Proposal p WHERE p.proposalId = ?1")
    List<Comment> findCommentsForProposal(Integer id);

}
