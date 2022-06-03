package com.example.safetynet.UnitTests.Controller;

import static org.assertj.core.api.Assertions.assertThat;
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
        Medicalrecord medicalrecord = new Medicalrecord();

        doReturn(medicalrecord).when(service).addMedicalRecord(medicalrecord);

        ResponseEntity<Medicalrecord> result = controller.addmedicalrecord(medicalrecord);

        verify(service).addMedicalRecord(medicalrecord);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isEqualTo(medicalrecord);
    }

    @Test
    void addMedicalrecordTestNull() {
        Medicalrecord medicalrecord = null;

        doReturn(medicalrecord).when(service).addMedicalRecord(medicalrecord);

        ResponseEntity<Medicalrecord> result = controller.addmedicalrecord(medicalrecord);

        verify(service).addMedicalRecord(medicalrecord);
        assertThat(result).isNull();
    }

    @Test
    void updateMedicalrecordTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord Medicalrecord = new Medicalrecord();

        doReturn(Medicalrecord).when(service).updateMedicalrecord(Medicalrecord);

        ResponseEntity<Medicalrecord> result = controller.updatemedicalrecord(Medicalrecord);

        verify(service).updateMedicalrecord(Medicalrecord);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(Medicalrecord);
    }

    @Test
    void updateMedicalrecordTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord Medicalrecord = null;

        doReturn(Medicalrecord).when(service).updateMedicalrecord(Medicalrecord);

        ResponseEntity<Medicalrecord> result = controller.updatemedicalrecord(Medicalrecord);

        verify(service).updateMedicalrecord(Medicalrecord);
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
