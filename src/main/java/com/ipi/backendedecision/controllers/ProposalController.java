package com.ipi.backendedecision.controllers;

import com.ipi.backendedecision.dao.ProposalVote;
import com.ipi.backendedecision.exceptions.AlreadyVotedException;
import com.ipi.backendedecision.exceptions.ProposalNotFoundException;
import com.ipi.backendedecision.exceptions.UserNotFoundException;
import com.ipi.backendedecision.models.Proposal;
import com.ipi.backendedecision.models.User;
import com.ipi.backendedecision.models.Vote;
import com.ipi.backendedecision.models.VoteType;
import com.ipi.backendedecision.repositories.ProposalRepository;
import com.ipi.backendedecision.repositories.UserRepository;
import com.ipi.backendedecision.repositories.VoteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/proposal/{id}")
    public Proposal findById(@PathVariable Integer id) {
        return proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException(id));
    }

    @GetMapping("/proposal/{id}/{userId}/yes")
    public Vote voteYes(@PathVariable Integer id, @PathVariable Integer userId) {
        return voteRepository.save(getVote(id, userId, VoteType.YES));
    }

    @GetMapping("/proposal/{id}/{userId}/no")
    public Vote voteNo(@PathVariable Integer id, @PathVariable Integer userId) {
        return voteRepository.save(getVote(id, userId, VoteType.NO));
    }

    @GetMapping("/proposal/{id}/{userId}/undetermined")
    public Vote voteUndetermined(@PathVariable Integer id, @PathVariable Integer userId) {
        return voteRepository.save(getVote(id, userId, VoteType.UNDETERMINED));
    }

    private Vote getVote(Integer id, Integer userId, VoteType voteType) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Proposal proposal = proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException(id));

        List<Vote> votes = voteRepository.findAllVotesForProposal(proposal.getProposalId()).orElseThrow(RuntimeException::new);

        if(votes.stream().anyMatch(v -> v.getUser() == user)) {
            throw new AlreadyVotedException();
        }

        return new Vote(user, proposal, voteType);
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
