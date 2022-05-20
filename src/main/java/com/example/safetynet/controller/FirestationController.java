package com.example.safetynet.controller;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.service.FirestationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirestationController {

    @Autowired
    FirestationService firestationService;

    @PostMapping("/firestation")
    public ResponseEntity<Firestation> addFirestation(@RequestBody Firestation newfirestation) {
        Firestation firestation = firestationService.addFirestation(newfirestation);
        if (firestation == null) {
            return null;
        } else {
            return new ResponseEntity<>(firestation, HttpStatus.CREATED);
        }
    }

    @PutMapping("/firestation")
    public ResponseEntity<Firestation> updateFirestation(@RequestBody Firestation newfirestation)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Firestation firestation = firestationService.updateFirestation(newfirestation);
        if (firestation == null) {
            return null;
        } else {
            return new ResponseEntity<>(firestation, HttpStatus.OK);
        }
    }

    @DeleteMapping("/firestation")
    public ResponseEntity<Firestation> deleteFirestation(@RequestBody Firestation newfirestation)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Firestation firestation = firestationService.deleteFirestation(newfirestation);
        if (firestation == null) {
            return null;
        } else {
            return new ResponseEntity<>(firestation, HttpStatus.OK);
        }
    }
}
