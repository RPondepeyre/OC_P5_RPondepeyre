package com.example.safetynet.controller;

import com.example.safetynet.DTO.PersonsbyFirestationsDTO;
import com.example.safetynet.service.PersonsbyFirestationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
public class GetController {

    @Autowired
    PersonsbyFirestationService service;

    // localhost:8080/firestation?stationNumber=<station_number>
    @GetMapping("/firestation")
    public ResponseEntity<PersonsbyFirestationsDTO> fireStation(@RequestParam int stationNumber) {
        PersonsbyFirestationsDTO result = service.personsbyFirestationDTO(stationNumber);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // localhost:8080/childAlert?address=<address>
    @GetMapping("/childAlert")
    public ResponseEntity<String> childAlert(@RequestParam String address) {
        // Insert Method
        return new ResponseEntity<>("ChildAlert" + address, HttpStatus.OK);
    }

    // localhost:8080/phoneAlert?firestation=<firestation_number>
    @GetMapping("/phoneAlert")
    public ResponseEntity<String> phoneAlert(@RequestParam int firestation) {
        // Insert Method
        return new ResponseEntity<>("phoneAlert" + firestation, HttpStatus.OK);
    }

    // localhost:8080/fire?address=<address>
    @GetMapping("/fire")
    public ResponseEntity<String> fire(@RequestParam String address) {
        // Insert Method
        return new ResponseEntity<>("fire" + address, HttpStatus.OK);
    }
}
