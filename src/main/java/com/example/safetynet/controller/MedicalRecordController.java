package com.example.safetynet.controller;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.service.MedicalrecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRecordController {

    @Autowired
    MedicalrecordService medicalrecordService;

    @PostMapping("/medicalrecord")
    public ResponseEntity<Medicalrecord> addmedicalrecord(@RequestBody Medicalrecord newmedicalrecord) {
        Medicalrecord medicalrecord = medicalrecordService.addMedicalRecord(newmedicalrecord);
        if (medicalrecord == null) {
            return null;
        } else {
            return new ResponseEntity<>(medicalrecord, HttpStatus.CREATED);
        }
    }

    @PutMapping("/medicalrecord")
    public ResponseEntity<Medicalrecord> updatemedicalrecord(@RequestBody Medicalrecord newmedicalrecord)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord medicalrecord = medicalrecordService.updateMedicalrecord(newmedicalrecord);
        if (medicalrecord == null) {
            return null;
        } else {
            return new ResponseEntity<>(medicalrecord, HttpStatus.OK);
        }
    }

    @DeleteMapping("/medicalrecord")
    public ResponseEntity<Medicalrecord> deletemedicalrecord(@RequestBody Medicalrecord newmedicalrecord)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord medicalrecord = medicalrecordService.deleteMedicalrecord(newmedicalrecord);
        if (medicalrecord == null) {
            return null;
        } else {
            return new ResponseEntity<>(medicalrecord, HttpStatus.OK);
        }
    }

}
