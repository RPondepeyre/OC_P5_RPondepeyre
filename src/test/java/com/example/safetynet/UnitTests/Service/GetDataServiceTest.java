package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.GetDataService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.altindag.log.LogCaptor;

@ExtendWith(MockitoExtension.class)
public class GetDataServiceTest {

    @Mock
    ObjectMapper objectmapper;

    @InjectMocks
    GetDataService service = new GetDataService();

    JsonNode datanode;
    JsonNode nullnode;
    ObjectMapper testmapper = new ObjectMapper();
    LogCaptor logCaptor = LogCaptor.forClass(GetDataService.class);

    @BeforeEach
    public void Setup() throws IOException {

        String person = " \"firstName\": \"John\", \"lastName\": \"Boyd\", \"address\": \"1509 Culver St\", \"city\": \"Culver\", \"zip\": \"97451\", \"phone\": \"841-874-6512\", \"email\": \"jaboyd@email.com\" ";
        String persons = " \"persons\": [{" + person + "}], ";
        String firestation = "\"address\": \"1509 Culver St\", \"station\": \"3\" ";
        String firestations = "\"firestations\":[{" + firestation + "}], ";
        String medicalrecord = " \"firstName\": \"John\", \"lastName\": \"Boyd\", \"birthdate\": \"03/06/1984\", \"medications\": [\"aznol:350mg\",\"hydrapermazol:100mg\"], \"allergies\": [\"nillacilan\"]";
        String medicalrecords = "\"medicalrecords\":[{" + medicalrecord + "}] ";
        String jsonstring = "{" + persons + firestations + medicalrecords + "}";

        datanode = testmapper.readTree(jsonstring);

    }

    @Test
    void GetFirestationTesttrue() throws IOException {
        when(objectmapper.readTree(any(File.class))).thenReturn(datanode);
        List<Firestation> result = service.getFirestations("pathname");
        assertThat(result).isNotNull();
        assertThat(result.get(0).getAdress()).isEqualTo("1509 Culver St");
        assertThat(result.get(0).getStation()).isEqualTo(3);

    }

    @Test
    void GetFirestationTestFalse() throws IOException {
        nullnode = testmapper.readTree("{ \"firestations\":\"null\" }");
        when(objectmapper.readTree(any(File.class))).thenReturn(nullnode);
        List<Firestation> result = service.getFirestations("pathname");
        assertThat(result).isEmpty();
        assertThat(logCaptor.getErrorLogs()).containsExactly("No firestations found");

    }

    @Test
    void GetPersonTesttrue() throws IOException {
        when(objectmapper.readTree(any(File.class))).thenReturn(datanode);
        List<Person> result = service.getPersons("pathname");
        assertThat(result).isNotNull();
        assertThat(result.get(0).getFirstName()).isEqualTo("John");
        assertThat(result.get(0).getLastName()).isEqualTo("Boyd");
        assertThat(result.get(0).getAdress()).isEqualTo("1509 Culver St");
        assertThat(result.get(0).getCity()).isEqualTo("Culver");
        assertThat(result.get(0).getZip()).isEqualTo("97451");
        assertThat(result.get(0).getPhone()).isEqualTo("841-874-6512");
        assertThat(result.get(0).getEmail()).isEqualTo("jaboyd@email.com");

    }

    @Test
    void GetPersonTestFalse() throws IOException {
        nullnode = testmapper.readTree("{ \"persons\":\"null\" }");
        when(objectmapper.readTree(any(File.class))).thenReturn(nullnode);
        List<Person> result = service.getPersons("pathname");
        assertThat(result).isEmpty();
        assertThat(logCaptor.getErrorLogs()).containsExactly("No persons found");

    }

    @Test
    void GetMedicalRecordTesttrue() throws IOException {
        when(objectmapper.readTree(any(File.class))).thenReturn(datanode);
        List<Medicalrecord> result = service.getMedicalrecords("pathname");
        assertThat(result).isNotNull();
        assertThat(result.get(0).getFirstName()).isEqualTo("John");
        assertThat(result.get(0).getLastName()).isEqualTo("Boyd");
        assertThat(result.get(0).getBirthdate())
                .isEqualTo(LocalDate.parse("03/06/1984", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        assertThat(result.get(0).getMedications().get(0)).isEqualTo("aznol:350mg");
        assertThat(result.get(0).getMedications().get(1)).isEqualTo("hydrapermazol:100mg");
        assertThat(result.get(0).getAllergies().get(0)).isEqualTo("nillacilan");

    }

    @Test
    void GetMedicalRecordTestFalse() throws IOException {
        nullnode = testmapper.readTree("{ \"medicalrecords\":\"null\" }");
        when(objectmapper.readTree(any(File.class))).thenReturn(nullnode);
        List<Medicalrecord> result = service.getMedicalrecords("pathname");
        assertThat(result).isEmpty();
        assertThat(logCaptor.getErrorLogs()).containsExactly("No medical records found");

    }

}
