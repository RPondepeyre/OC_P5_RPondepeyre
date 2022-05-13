package com.example.safetynet.controller;

import com.example.safetynet.service.FirestationService;
import com.example.safetynet.service.MedicalrecordService;
import com.example.safetynet.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PutController {

    @Autowired
    PersonService personService;

    @Autowired
    FirestationService firestationService;

    @Autowired
    MedicalrecordService medicalrecordService;

    /*
     * @PutMapping("/person")
     * public ResponseEntity<Person> person(@RequestBody Person newperson) {
     * Person person = personService.updatePerson(newperson);
     * if (person == null) {
     * return null;
     * } else {
     * return new ResponseEntity<>(person, HttpStatus.CREATED);
     * }
     * }
     * 
     * @PutMapping("/firestation")
     * public ResponseEntity<Firestation> firestation(@RequestBody Firestation
     * newfirestation) {
     * Firestation firestation =
     * firestationService.updateFirestation(newfirestation);
     * if (firestation == null) {
     * return null;
     * } else {
     * return new ResponseEntity<>(firestation, HttpStatus.CREATED);
     * }
     * }
     * 
     * @PuttMapping("/medicalRecord")
     * public ResponseEntity<Medicalrecord> medicalRecord(@RequestBody Medicalrecord
     * newmedicalrecord) {
     * Medicalrecord medicalrecord =
     * medicalrecordService.updateMedicalRecord(newmedicalrecord);
     * if (medicalrecord == null) {
     * return null;
     * } else {
     * return new ResponseEntity<>(medicalrecord, HttpStatus.CREATED);
     * }
     * }
     */
}
