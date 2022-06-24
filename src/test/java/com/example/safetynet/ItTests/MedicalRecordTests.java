package com.example.safetynet.ItTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.MedicalrecordService;

@SpringBootTest(properties = { "datapath = src/test/resources/DataTest.json" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class MedicalRecordTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    MedicalrecordService service;

    Person person = new Person();

    @BeforeEach
    void SetUp() {

    }

    @Test
    void post() throws Exception {

        person.setFirstName("prenom");
        person.setLastName("nom");

        mockMvc.perform(MockMvcRequestBuilders.post("/medicalrecord")
                .content(
                        "{ \"firstName\": \"prenom\", \"lastName\": \"nom\", \"birthdate\": \"01/01/2000\", \"medications\": [], \"allergies\": [] }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        assertThat(service.findByPerson(person)).isNotNull();

    }

    @Test
    void put() throws Exception {

        person.setFirstName("firstName1");
        person.setLastName("lastName1");

        mockMvc.perform(MockMvcRequestBuilders.put("/medicalrecord")
                .content(
                        "{ \"firstName\": \"firstName1\", \"lastName\": \"lastName1\", \"birthdate\": \"01/01/1500\", \"medications\": [], \"allergies\": [] }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThat(service.findByPerson(person).getMedications()).isEmpty();

    }

    @Test
    void delete() throws Exception {

        person.setFirstName("firstName1");
        person.setLastName("lastName1");

        mockMvc.perform(MockMvcRequestBuilders.delete("/medicalrecord")
                .content(
                        "{\"firstName\": \"firstName1\", \"lastName\": \"lastName1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThrows(RessourceNotFoundException.class, () -> {
            service.findByPerson(person);
        });

    }
}
