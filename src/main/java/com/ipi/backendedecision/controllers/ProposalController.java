package com.ipi.backendedecision.controllers;

import com.ipi.backendedecision.dao.ProposalVote;
import com.ipi.backendedecision.exceptions.ProposalNotFoundException;
import com.ipi.backendedecision.exceptions.UserNotFoundException;
import com.ipi.backendedecision.models.Proposal;
import com.ipi.backendedecision.models.User;
import com.ipi.backendedecision.models.Vote;
import com.ipi.backendedecision.models.VoteType;
import com.ipi.backendedecision.repositories.ProposalRepository;
import com.ipi.backendedecision.repositories.UserRepository;
import com.ipi.backendedecision.repositories.VoteRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProposalController {

    private final ProposalRepository proposalRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public ProposalController(ProposalRepository proposalRepository, VoteRepository voteRepository, UserRepository userRepository) {
        this.proposalRepository = proposalRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/proposals")
    public List<ProposalVote> all() {
        List<Proposal> proposals = (List<Proposal>) proposalRepository.findAll();
        return proposals.stream().map(this::proposalToProposalVote).collect(Collectors.toList());
    }

    @GetMapping("/proposal/details/{id}")
    public ProposalVote findProposalDetails(@PathVariable Integer id) {
        return proposalRepository.findById(id).map(this::proposalToProposalVote).orElseThrow(() -> new ProposalNotFoundException(id));
    }

    @GetMapping("/proposal/{id}")
    public Proposal findById(@PathVariable Integer id) {
        return proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException(id));
    }

    @PostMapping("/addProposal")
    public Proposal add(@RequestBody Proposal newProposal) {
        newProposal.setCreationDate(LocalDate.now());
        return proposalRepository.save(newProposal);
    }

    @PutMapping("/proposal/{id}")
    public Proposal update(@PathVariable Integer id, @RequestBody Proposal newProposal) {
        return proposalRepository.findById(id)
                .map(proposal -> {
                    proposal.setTitle(newProposal.getTitle());
                    proposal.setDescription(newProposal.getDescription());
                    proposal.setClosingDate(newProposal.getClosingDate());
                    proposal.setOwners(newProposal.getOwners());
                    proposal.setPublicationLevel(newProposal.getPublicationLevel());
                    proposal.setCreationDate(LocalDate.now());
                    return proposalRepository.save(proposal);
                }).orElseGet(() -> {
                    newProposal.setProposalId(id);
                    return proposalRepository.save(newProposal);
                });
    }

    @DeleteMapping("/proposal/{id}/{userId}")
    public void deleteProposal(@PathVariable Integer id, @PathVariable Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Proposal p = proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException(id));

        if(!p.getOwners().contains(user)) {
            proposalRepository.deleteById(id);
        } else {
            throw new RuntimeException("Unauthorized Action");
        }
    }

    private ProposalVote proposalToProposalVote(Proposal p) {
        List<Vote> votes = voteRepository.findAllVotesForProposal(p.getProposalId()).orElseThrow(RuntimeException::new);
        int finalVotes = getTotalOfVotes(votes);
        return new ProposalVote(p, votes, finalVotes);
    }

    private int getTotalOfVotes(List<Vote> votes) {
        int voteYes = (int) votes.stream().filter(vote -> vote.getVoteType() == VoteType.YES).count();
        int voteNo = (int) votes.stream().filter(vote -> vote.getVoteType() == VoteType.NO).count();

        return voteYes - voteNo;
    }
}
