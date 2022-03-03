package com.ipi.backendedecision.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("Could not find User : " + id);
    }
}
