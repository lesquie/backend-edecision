package com.ipi.backendedecision.exceptions.advices;

import com.ipi.backendedecision.exceptions.AlreadyVotedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AlreadyVotedExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(AlreadyVotedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String alreadyVotedHandler(AlreadyVotedException exception) {
        return exception.getMessage();
    }
}
