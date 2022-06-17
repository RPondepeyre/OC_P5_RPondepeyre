package com.example.safetynet.ItTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(properties = { "datapath = src/test/resources/DataTest.json" })
@AutoConfigureMockMvc
public class GetTests {

    @Autowired
    public MockMvc mockMvc;

    @Test
    void firestations() throws Exception {

        String expected = "{"
                + "\"persons\":["
                + "{\"firstName\":\"firstName3\",\"lastName\":\"lastName3\",\"address\":\"address3\",\"phone\":\"phone3\"},"
                + "{\"firstName\":\"firstName4\",\"lastName\":\"lastName4\",\"address\":\"address4\",\"phone\":\"phone4\"},"
                + "{\"firstName\":\"firstName5\",\"lastName\":\"lastName4\",\"address\":\"address4\",\"phone\":\"phone4\"}"
                + "],"
                + "\"nbAdults\":1,"
                + "\"nbChildren\":2"
                + "}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/firestation/?stationNumber=2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);

    }

    @Test
    void firestationsNull() throws Exception {

        String expected = "";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/firestation/?stationNumber=5"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);

    }

    @Test
    void firestationsError() throws Exception {

        String expected = "La station n° 20 n'existe pas";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/firestation/?stationNumber=20"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);

    }

    @Test
    void childAlert() throws Exception {

        String expected = "["
                + "{\"firstname\":\"firstName4\",\"lastName\":\"lastName4\",\"age\":5,"
                + "\"persons\":["
                + "{\"firstName\":\"firstName5\",\"lastName\":\"lastName4\",\"address\":\"address4\",\"city\":\"Culver\",\"zip\":\"97451\",\"phone\":\"phone4\",\"email\":\"mail5\"}"
                + "]}]";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/childAlert?address=address4"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);

    }

    @Test
    void childAlertNull() throws Exception {

        String expected = "[]";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/childAlert?address=address1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);

    }

    @Test
    void phoneAlert() throws Exception {

        String expected = "[\"phone2\",\"phone1\"]";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation=1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);

    }

    @Test
    void phoneAlertNull() throws Exception {

        String expected = "[]";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation=5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);

    }

    @Test
    void phoneAlertError() throws Exception {

        String expected = "La station n° 20 n'existe pas";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/phoneAlert?firestation=20"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);

    }

    @Test
    void fire() throws Exception {

        String expected = "{"
                + "\"persons\":["
                + "{\"firstname\":\"firstName1\",\"lastname\":\"lastName1\",\"phone\":\"phone1\",\"age\":38,\"medications\":[\"medication1\",\"medication2\"],\"allergies\":[\"allergie1\"]}],"
                + "\"station\":1}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fire?address=address1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);
    }

    @Test
    void fireNull() throws Exception {

        String expected = "{"
                + "\"persons\":[],"
                + "\"station\":5}";
        ;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fire?address=address5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);

    }

    @Test
    void fireError() throws Exception {

        String expected = "Aucune station trouvée pour l'adresse: address20";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/fire?address=address20"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);

    }

    @Test
    void floodStations() throws Exception {

        String expected = "[{\"stationID\":1,\"houses\":["
                + "{\"address\":\"address1\",\"persons\":["
                + "{\"firstname\":\"firstName1\",\"lastname\":\"lastName1\",\"phone\":\"phone1\",\"age\":38,\"medications\":[\"medication1\",\"medication2\"],\"allergies\":[\"allergie1\"]}]},"
                + "{\"address\":\"address2\",\"persons\":[{\"firstname\":\"firstName2\",\"lastname\":\"lastName2\",\"phone\":\"phone2\",\"age\":33,\"medications\":[\"medication1\",\"medication2\",\"medication3\"],\"allergies\":[]}]}]},"
                + "{\"stationID\":2,\"houses\":["
                + "{\"address\":\"address3\",\"persons\":["
                + "{\"firstname\":\"firstName3\",\"lastname\":\"lastName3\",\"phone\":\"phone3\",\"age\":10,\"medications\":[],\"allergies\":[\"allergie1\"]}]},"
                + "{\"address\":\"address4\",\"persons\":["
                + "{\"firstname\":\"firstName4\",\"lastname\":\"lastName4\",\"phone\":\"phone4\",\"age\":5,\"medications\":[],\"allergies\":[]},"
                + "{\"firstname\":\"firstName5\",\"lastname\":\"lastName4\",\"phone\":\"phone4\",\"age\":36,\"medications\":[\"medication1\"],\"allergies\":[\"allergie1\"]}]}]}]";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations?stations=1,2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);
    }

    @Test
    void floodStationsNull() throws Exception {

        String expected = "[{\"stationID\":5,\"houses\":[{\"address\":\"address5\",\"persons\":[]}]}]";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations?stations=5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);
    }

    @Test
    void floodStationsError() throws Exception {

        String expected = "La station n° 20 n'existe pas";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/flood/stations?stations=20"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);
    }

    @Test
    void personInfo() throws Exception {

        String expected = "[{\"firstname\":\"firstName1\",\"lastname\":\"lastName1\",\"address\":\"address1\",\"age\":38,\"email\":\"mail1\",\"medications\":[\"medication1\",\"medication2\"],\"allergies\":[\"allergie1\"]}]";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/personInfo?firstName=firstName1&lastName=lastName1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);
    }

    @Test
    void personInfoError() throws Exception {

        String expected = "Aucune personne trouvée pour le nom: firstName1 lastName4";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/personInfo?firstName=firstName1&lastName=lastName4"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);
    }

    @Test
    void communityEmail() throws Exception {

        String expected = "[\"mail4\",\"mail3\",\"mail5\",\"mail2\",\"mail1\"]";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/communityEmail?city=Culver"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);
    }

    @Test
    void communityEmailNull() throws Exception {

        String expected = "[]";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/communityEmail?city=city"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        assertThat(content).isEqualToIgnoringCase(expected);
    }
}
