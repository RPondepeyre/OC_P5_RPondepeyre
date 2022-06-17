package com.example.safetynet.ItTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.example.safetynet.service.PersonService;

@SpringBootTest(properties = { "datapath = src/test/resources/DataTest.json" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class PersonTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    PersonService service;

    @Test
    void post() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/person")
                .content(
                        "{ \"firstName\": \"prenom\", \"lastName\": \"nom\", \"address\": \"addresse\", \"city\": \"ville\", \"zip\": \"00\",\"phone\": \"tel\", \"email\": \"email\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        assertThat(service.findByName("prenom", "nom")).isNotNull();

    }

    @Test
    void put() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/person")
                .content(
                        "{\"firstName\": \"firstName1\", \"lastName\": \"lastName1\", \"address\": \"addresse\", \"city\": \"ville\", \"zip\": \"00\",\"phone\": \"tel\", \"email\": \"email\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThat(service.findByName("firstName1", "lastName1").getAddress()).isEqualTo("addresse");
        assertThat(service.findByName("firstName1", "lastName1").getPhone()).isEqualTo("tel");

    }

    @Test
    void delete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/person")
                .content(
                        "{\"firstName\": \"firstName1\", \"lastName\": \"lastName1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThrows(RessourceNotFoundException.class, () -> {
            service.findByName("firstName1", "lastName1");
        });

    }

}
