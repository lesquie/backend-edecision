package com.ipi.backendedecision.exceptions;

public class ActionUnauthorizedException extends RuntimeException {
    public ActionUnauthorizedException() {
        super("Action non autorisée.");
    }
}
