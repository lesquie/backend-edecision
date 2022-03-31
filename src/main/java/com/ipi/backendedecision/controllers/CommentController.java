package com.ipi.backendedecision.controllers;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.ipi.backendedecision.exceptions.ActionUnauthorizedException;
import com.ipi.backendedecision.exceptions.CommentNotFoundException;
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
    public Comment addCommentToProposal(@PathVariable Integer id, @RequestBody Comment comment) {
        Proposal p = proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException(id));
        p.getComments().add(comment);
        Comment c = commentRepository.save(comment);
        proposalRepository.save(p);
        return c;
    }

    @PutMapping("/proposal/{id}/{userId}/modifyComment/{commentId}")
    public Comment modifyComment(@PathVariable Integer id, @PathVariable Integer userId, @PathVariable Integer commentId, @RequestBody Comment commentModified) {
        Proposal p = proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException(id));

        Comment comment = p.getComments()
                .stream()
                .filter(c -> c.getId() == commentId)
                .findFirst()
                .map(comment1 -> {
                    comment1.setContent(commentModified.getContent());
                    comment1.setCreationDate(commentModified.getCreationDate());
                    return comment1;
                })
                .orElseGet(() -> {
                    commentModified.setId(commentId);
                    return commentModified;
                });

        if(comment.getOwner().getId() != userId) {
            throw new ActionUnauthorizedException();
        }

        proposalRepository.save(p);
        return commentRepository.save(comment);
    }

    @DeleteMapping("/proposal/{id}/deleteComment/{commentId}")
    public void deleteComment(@PathVariable Integer id, @PathVariable Integer commentId) {
        Proposal p = proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException(id));

        Comment comment = p.getComments()
                .stream()
                .filter(c -> c.getId() == commentId)
                .findFirst()
                .orElseThrow(CommentNotFoundException::new);

        p.getComments().remove(comment);

        proposalRepository.save(p);
        commentRepository.delete(comment);
    }


}
