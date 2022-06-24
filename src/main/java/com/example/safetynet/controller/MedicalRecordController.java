package com.example.safetynet.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.dtobjects.MedicalrecordDTO;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.service.MedicalrecordService;

@RestController
public class MedicalRecordController {

    @Autowired
    MedicalrecordService medicalrecordService;

    public Medicalrecord translateDTOtoObject(MedicalrecordDTO dto) {
        Medicalrecord result = new Medicalrecord();
        result.setFirstName(dto.getFirstName());
        result.setLastName(dto.getLastName());
        result.setAllergies(dto.getAllergies());
        result.setMedications(dto.getMedications());
        result.setBirthdate(LocalDate.parse(dto.getBirthdate(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        return result;
    }

    @PostMapping("/medicalrecord")
    public ResponseEntity<Medicalrecord> addmedicalrecord(@RequestBody MedicalrecordDTO newmedicalrecord) {
        Medicalrecord request = translateDTOtoObject(newmedicalrecord);
        Medicalrecord medicalrecord = medicalrecordService.addMedicalRecord(request);
        if (medicalrecord == null) {
            return null;
        } else {
            return new ResponseEntity<>(medicalrecord, HttpStatus.CREATED);
        }
    }

    @PutMapping("/medicalrecord")
    public ResponseEntity<Medicalrecord> updatemedicalrecord(@RequestBody MedicalrecordDTO newmedicalrecord)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord request = translateDTOtoObject(newmedicalrecord);
        Medicalrecord medicalrecord = medicalrecordService.updateMedicalrecord(request);
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
