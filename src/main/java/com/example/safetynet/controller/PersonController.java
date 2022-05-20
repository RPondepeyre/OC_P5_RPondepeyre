package com.example.safetynet.controller;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person newperson) {
        Person person = personService.addPerson(newperson);
        if (person == null) {
            return null;
        } else {
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        }
    }

    @PutMapping("/person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person newperson)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person person = personService.updatePerson(newperson);
        if (person == null) {
            return null;
        } else {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }

    @DeleteMapping("/person")
    public ResponseEntity<Person> deletePerson(@RequestBody Person newperson)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person person = personService.deletePerson(newperson);
        if (person == null) {
            return null;
        } else {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
    }

}
