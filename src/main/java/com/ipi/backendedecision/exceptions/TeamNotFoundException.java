package com.ipi.backendedecision.exceptions;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Integer id) {
        super("Could not find Team : " + id);
    }
}
