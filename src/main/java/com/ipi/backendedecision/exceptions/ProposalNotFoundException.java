package com.ipi.backendedecision.exceptions;

public class ProposalNotFoundException extends RuntimeException {
    public ProposalNotFoundException(Integer id) {
        super("Could not find Proposal : " + id);
    }
}
