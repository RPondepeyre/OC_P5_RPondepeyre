package com.example.safetynet.config;

import com.example.safetynet.config.exceptions.PersonNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
@ControllerAdvice
public class ExceptionsHandle

@ExceptionHandler(FirestationAlreadyExistsException.class)
    public ResponseEntity<Object> handleFirestationAlreadyExistsExceptions(FirestationAlreadyExistsException e){
        logger.error("Firestation Already exists");
        CustomErrorResponse res = new CustomErrorResponse(e.getMessage(),e, HttpStatus.CONFLICT, ZonedDateTime.now());
        return new ResponseEntity<>(res,HttpStatus.CONFLICT);
    }

*/
@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = PersonNotFoundException.class)
    public ResponseEntity<PersonNotFoundException> personNotFound(PersonNotFoundException e) {
        return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
    }

}
