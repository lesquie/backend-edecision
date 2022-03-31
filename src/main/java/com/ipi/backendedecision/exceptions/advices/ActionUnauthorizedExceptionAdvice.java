package com.ipi.backendedecision.exceptions.advices;

import com.ipi.backendedecision.exceptions.ActionUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ActionUnauthorizedExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(ActionUnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String actionUnauthorizedHandler(ActionUnauthorizedException exception) {
        return exception.getMessage();
    }
}
