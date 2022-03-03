package com.ipi.backendedecision.exceptions.advices;

import com.ipi.backendedecision.exceptions.LoginFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LoginFailedExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String loginFailedHandler(LoginFailedException exception) {
        return exception.getMessage();
    }
}
