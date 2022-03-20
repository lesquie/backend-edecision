package com.ipi.backendedecision.controllers;

import com.ipi.backendedecision.exceptions.ProposalNotFoundException;
import com.ipi.backendedecision.models.Comment;
import com.ipi.backendedecision.models.Proposal;
import com.ipi.backendedecision.repositories.CommentRepository;
import com.ipi.backendedecision.repositories.ProposalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    private final CommentRepository commentRepository;
    private final ProposalRepository proposalRepository;

    public CommentController(CommentRepository commentRepository, ProposalRepository proposalRepository) {
        this.commentRepository = commentRepository;
        this.proposalRepository = proposalRepository;
    }

    @GetMapping("/proposal/{id}/comments")
    public List<Comment> getCommentsForProposal(@PathVariable Integer id) {
        return commentRepository.findCommentsForProposal(id);
    }

    @PostMapping("/proposal/{id}/comment")
    public Proposal addCommentToProposal(@PathVariable Integer id, @RequestBody Comment comment) {
        Proposal p = proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException(id));
        p.getComments().add(comment);
        return proposalRepository.save(p);
    }

}
