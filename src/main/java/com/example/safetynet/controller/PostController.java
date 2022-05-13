package com.example.safetynet.controller;

import com.example.safetynet.service.FirestationService;
import com.example.safetynet.service.MedicalrecordService;
import com.example.safetynet.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public void Person() {

    }
}
