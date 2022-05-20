package com.example.safetynet.config;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = RessourceNotFoundException.class)
    public ResponseEntity<String> ressourceNotFound(RessourceNotFoundException e) {
        String error = e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(value = TooManyRessourcesFoundException.class)
    public ResponseEntity<String> tooManyressources(RessourceNotFoundException e) {
        String error = e.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
