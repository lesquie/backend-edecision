package com.ipi.backendedecision.exceptions;

public class VoteNotFoundException extends RuntimeException {
    public VoteNotFoundException() {
        super("Vote not found.");
    }
}
