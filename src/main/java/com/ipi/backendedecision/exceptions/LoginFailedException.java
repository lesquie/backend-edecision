package com.ipi.backendedecision.exceptions;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException() {
        super("Login failed");
    }
}
