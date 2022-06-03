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
import com.example.safetynet.controller.PersonController;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTests {
    @Mock
    PersonService service;
    @InjectMocks
    PersonController controller;

    @Test
    void addPersonTest() {
        Person person = new Person();

        doReturn(person).when(service).addPerson(person);

        ResponseEntity<Person> result = controller.addPerson(person);

        verify(service).addPerson(person);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isEqualTo(person);
    }

    @Test
    void addPersonTestNull() {
        Person person = null;

        doReturn(person).when(service).addPerson(person);

        ResponseEntity<Person> result = controller.addPerson(person);

        verify(service).addPerson(person);
        assertThat(result).isNull();
    }

    @Test
    void updatePersonTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person person = new Person();

        doReturn(person).when(service).updatePerson(person);

        ResponseEntity<Person> result = controller.updatePerson(person);

        verify(service).updatePerson(person);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(person);
    }

    @Test
    void updatePersonTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person person = null;

        doReturn(person).when(service).updatePerson(person);

        ResponseEntity<Person> result = controller.updatePerson(person);

        verify(service).updatePerson(person);
        assertThat(result).isNull();
    }

    @Test
    void deletePersonTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person person = new Person();

        doReturn(person).when(service).deletePerson(person);

        ResponseEntity<Person> result = controller.deletePerson(person);

        verify(service).deletePerson(person);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(person);
    }

    @Test
    void deletePersonTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person person = null;

        doReturn(person).when(service).deletePerson(person);

        ResponseEntity<Person> result = controller.deletePerson(person);

        verify(service).deletePerson(person);
        assertThat(result).isNull();
    }
}
