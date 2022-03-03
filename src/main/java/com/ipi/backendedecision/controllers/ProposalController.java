package com.ipi.backendedecision.controllers;

import com.ipi.backendedecision.exceptions.ProposalNotFoundException;
import com.ipi.backendedecision.models.Proposal;
import com.ipi.backendedecision.repositories.ProposalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProposalController {

    private final ProposalRepository proposalRepository;

    public ProposalController(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @GetMapping("/proposals")
    public List<Proposal> all() {
        return (List<Proposal>) proposalRepository.findAll();
    }

    @GetMapping("/proposal/{id}")
    public Proposal findById(@PathVariable Integer id) {
        return proposalRepository.findById(id).orElseThrow(() -> new ProposalNotFoundException(id));
    }



}
