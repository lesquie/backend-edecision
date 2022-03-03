package com.ipi.backendedecision.exceptions;

public class AlreadyVotedException extends RuntimeException{
    public AlreadyVotedException() {
        super("Vous avez déjà voté.");
    }
}
