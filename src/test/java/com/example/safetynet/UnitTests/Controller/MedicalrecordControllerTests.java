package com.example.safetynet.UnitTests.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.controller.MedicalRecordController;
import com.example.safetynet.dtobjects.MedicalrecordDTO;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.service.MedicalrecordService;

@ExtendWith(MockitoExtension.class)
public class MedicalrecordControllerTests {
    @Mock
    MedicalrecordService service;
    @InjectMocks
    MedicalRecordController controller;

    @Test
    void addMedicalrecordTest() {
        Medicalrecord mr = new Medicalrecord();
        MedicalrecordDTO medicalrecordDTO = new MedicalrecordDTO();
        medicalrecordDTO.setBirthdate("01/01/2000");

        doReturn(mr).when(service).addMedicalRecord(any());

        ResponseEntity<Medicalrecord> result = controller.addmedicalrecord(medicalrecordDTO);

        verify(service).addMedicalRecord(any());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isEqualTo(mr);
    }

    @Test
    void addMedicalrecordTestNull() {
        Medicalrecord medicalrecord = null;
        MedicalrecordDTO medicalrecordDTO = new MedicalrecordDTO();
        medicalrecordDTO.setBirthdate("01/01/2000");

        doReturn(medicalrecord).when(service).addMedicalRecord(any());

        ResponseEntity<Medicalrecord> result = controller.addmedicalrecord(medicalrecordDTO);

        verify(service).addMedicalRecord(any());
        assertThat(result).isNull();
    }

    @Test
    void updateMedicalrecordTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord Medicalrecord = new Medicalrecord();
        MedicalrecordDTO medicalrecordDTO = new MedicalrecordDTO();
        medicalrecordDTO.setBirthdate("01/01/2000");

        doReturn(Medicalrecord).when(service).updateMedicalrecord(any());

        ResponseEntity<Medicalrecord> result = controller.updatemedicalrecord(medicalrecordDTO);

        verify(service).updateMedicalrecord(any());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(Medicalrecord);
    }

    @Test
    void updateMedicalrecordTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord Medicalrecord = null;
        MedicalrecordDTO medicalrecordDTO = new MedicalrecordDTO();
        medicalrecordDTO.setBirthdate("01/01/2000");

        doReturn(Medicalrecord).when(service).updateMedicalrecord(any());

        ResponseEntity<Medicalrecord> result = controller.updatemedicalrecord(medicalrecordDTO);

        verify(service).updateMedicalrecord(any());
        assertThat(result).isNull();
    }

    @Test
    void deleteMedicalrecordTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord Medicalrecord = new Medicalrecord();

        doReturn(Medicalrecord).when(service).deleteMedicalrecord(Medicalrecord);

        ResponseEntity<Medicalrecord> result = controller.deletemedicalrecord(Medicalrecord);

        verify(service).deleteMedicalrecord(Medicalrecord);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(Medicalrecord);
    }

    @Test
    void deleteMedicalrecordTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord Medicalrecord = null;

        doReturn(Medicalrecord).when(service).deleteMedicalrecord(Medicalrecord);

        ResponseEntity<Medicalrecord> result = controller.deletemedicalrecord(Medicalrecord);

        verify(service).deleteMedicalrecord(Medicalrecord);
        assertThat(result).isNull();
    }

}
