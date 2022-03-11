package com.ipi.backendedecision.exceptions;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Integer id) {
        super("Could not find Project : " + id);
    }
}
