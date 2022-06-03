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
import com.example.safetynet.controller.FirestationController;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.service.FirestationService;

@ExtendWith(MockitoExtension.class)
public class FirestationControllerTests {

    @Mock
    FirestationService service;
    @InjectMocks
    FirestationController controller;

    @Test
    void addFirestationTest() {
        Firestation firestation = new Firestation();

        doReturn(firestation).when(service).addFirestation(firestation);

        ResponseEntity<Firestation> result = controller.addFirestation(firestation);

        verify(service).addFirestation(firestation);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isEqualTo(firestation);
    }

    @Test
    void addFirestationTestNull() {
        Firestation firestation = null;

        doReturn(firestation).when(service).addFirestation(firestation);

        ResponseEntity<Firestation> result = controller.addFirestation(firestation);

        verify(service).addFirestation(firestation);
        assertThat(result).isNull();
    }

    @Test
    void updateFirestationTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Firestation firestation = new Firestation();

        doReturn(firestation).when(service).updateFirestation(firestation);

        ResponseEntity<Firestation> result = controller.updateFirestation(firestation);

        verify(service).updateFirestation(firestation);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(firestation);
    }

    @Test
    void updateFirestationTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Firestation firestation = null;

        doReturn(firestation).when(service).updateFirestation(firestation);

        ResponseEntity<Firestation> result = controller.updateFirestation(firestation);

        verify(service).updateFirestation(firestation);
        assertThat(result).isNull();
    }

    @Test
    void deleteFirestationTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Firestation firestation = new Firestation();

        doReturn(firestation).when(service).deleteFirestation(firestation);

        ResponseEntity<Firestation> result = controller.deleteFirestation(firestation);

        verify(service).deleteFirestation(firestation);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(firestation);
    }

    @Test
    void deleteFirestationTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Firestation firestation = null;

        doReturn(firestation).when(service).deleteFirestation(firestation);

        ResponseEntity<Firestation> result = controller.deleteFirestation(firestation);

        verify(service).deleteFirestation(firestation);
        assertThat(result).isNull();
    }

}
