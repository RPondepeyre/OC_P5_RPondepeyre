package com.example.safetynet.controller;

import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.FirestationService;
import com.example.safetynet.service.MedicalrecordService;
import com.example.safetynet.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    PersonService personService;

    @Autowired
    FirestationService firestationService;

    @Autowired
    MedicalrecordService medicalrecordService;

    @PostMapping("/person")
    public ResponseEntity<Person> person(@RequestBody Person newperson) {
        Person person = personService.addPerson(newperson);
        if (person == null) {
            return null;
        } else {
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        }
    }

    @PostMapping("/firestation")
    public ResponseEntity<Firestation> firestation(@RequestBody Firestation newfirestation) {
        Firestation firestation = firestationService.addFirestation(newfirestation);
        if (firestation == null) {
            return null;
        } else {
            return new ResponseEntity<>(firestation, HttpStatus.CREATED);
        }
    }

    @PostMapping("/medicalRecord")
    public ResponseEntity<Medicalrecord> medicalRecord(@RequestBody Medicalrecord newmedicalrecord) {
        Medicalrecord medicalrecord = medicalrecordService.addMedicalRecord(newmedicalrecord);
        if (medicalrecord == null) {
            return null;
        } else {
            return new ResponseEntity<>(medicalrecord, HttpStatus.CREATED);
        }
    }
}
