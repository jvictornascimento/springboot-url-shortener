package com.jvictornascimento.urtshortener.controllers.exceptions;

import com.jvictornascimento.urtshortener.services.exceptions.ExpiredLinkException;
import com.jvictornascimento.urtshortener.services.exceptions.HashNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(HashNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFond(HashNotFoundException e , HttpServletRequest request){
        String error = "Hash not found!";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(ExpiredLinkException.class)
    public ResponseEntity<StandardError> resourceNotFond(ExpiredLinkException e , HttpServletRequest request){
        String error = "Expired link!";
        HttpStatus status = HttpStatus.GONE;
        StandardError err = new StandardError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
