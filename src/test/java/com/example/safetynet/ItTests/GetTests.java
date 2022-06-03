package com.example.safetynet.ItTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest(properties = { "datapath = src/test/resources/DataTest.json" })
@AutoConfigureMockMvc
public class GetTests {

    @Autowired
    public MockMvc mockMvc;

    @Test
    void contextloads() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/firestation/?stationNumber=2"))
                .andDo(MockMvcResultHandlers.print());
    }

}
