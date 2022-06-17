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
import com.example.safetynet.service.FirestationService;

@SpringBootTest(properties = { "datapath = src/test/resources/DataTest.json" })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class FirestationTests {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    FirestationService service;

    @Test
    void post() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/firestation")
                .content("{ \"address\": \"postedAddress\", \"station\": \"1\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());

        assertThat(service.findByAddress("postedAddress")).isNotNull();

    }

    @Test
    void put() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/firestation")
                .content("{ \"address\": \"address1\", \"station\": \"20\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThat(service.findByAddress("address1")).isNotNull();
        assertThat(service.findByAddress("address1").getStation()).isEqualTo(20);

    }

    @Test
    void deleteaddress() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/firestation")
                .content("{ \"address\": \"address1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThrows(RessourceNotFoundException.class, () -> {
            service.findByAddress("address1");
        });

    }

    @Test
    void notdeleteboth() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/firestation")
                .content("{ \"address\": \"address1\",\"station\": \"1\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThrows(RessourceNotFoundException.class, () -> {
            service.findByAddress("address1");
        });

        assertThat(service.findByAddress("address2")).isNotNull();

    }

    @Test
    void deleteFullmappig() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/firestation")
                .content("{ \"station\": \"1\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        assertThrows(RessourceNotFoundException.class, () -> {
            service.findByAddress("address1");
        });

        assertThrows(RessourceNotFoundException.class, () -> {
            service.findByAddress("address2");
        });

    }
}
