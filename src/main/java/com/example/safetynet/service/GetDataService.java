package com.example.safetynet.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GetDataService {

    private ObjectMapper objectmapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(GetDataService.class);

    public JsonNode getRoot(String pathname) throws IOException {

        File file = new File(pathname);
        return objectmapper.readTree(file);

    }

    public List<Firestation> getFirestations(String pathname) throws IOException {

        JsonNode root = getRoot(pathname);

        JsonNode array = root.get("firestations");
        List<Firestation> firestations = new ArrayList<>();
        if (array.isArray()) {
            for (JsonNode jsonNode : array) {
                Firestation firestation = new Firestation();
                firestation.setAddress(jsonNode.get("address").asText());
                firestation.setStation(jsonNode.get("station").asInt());
                firestations.add(firestation);
            }
        } else {
            logger.error("No firestations found");
        }
        return firestations;
    }

    public List<Person> getPersons(String pathname) throws IOException {

        JsonNode root = getRoot(pathname);

        JsonNode array = root.get("persons");
        List<Person> persons = new ArrayList<>();
        if (array.isArray()) {
            for (JsonNode jsonNode : array) {
                Person person = new Person();
                person.setFirstName(jsonNode.get("firstName").asText());
                person.setLastName(jsonNode.get("lastName").asText());
                person.setAddress(jsonNode.get("address").asText());
                person.setCity(jsonNode.get("city").asText());
                person.setZip(jsonNode.get("zip").asText());
                person.setPhone(jsonNode.get("phone").asText());
                person.setEmail(jsonNode.get("email").asText());

                persons.add(person);
            }
        } else {
            logger.error("No persons found");
        }
        return persons;
    }

    public List<Medicalrecord> getMedicalrecords(String pathname) throws IOException {

        JsonNode root = getRoot(pathname);

        JsonNode array = root.get("medicalrecords");
        List<Medicalrecord> medicalrecords = new ArrayList<>();
        if (array.isArray()) {
            for (JsonNode jsonNode : array) {
                Medicalrecord medicalrecord = new Medicalrecord();
                medicalrecord.setFirstName(jsonNode.get("firstName").asText());
                medicalrecord.setLastName(jsonNode.get("lastName").asText());
                medicalrecord.setBirthdate(
                        LocalDate.parse(jsonNode.get("birthdate").asText(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                JsonNode medications = jsonNode.get("medications");
                List<String> medicationList = new ArrayList<>();
                for (JsonNode medsnode : medications) {
                    medicationList.add(medsnode.asText());
                }
                medicalrecord.setMedications(medicationList);
                JsonNode allergies = jsonNode.get("allergies");
                List<String> allergiesList = new ArrayList<>();
                for (JsonNode allergienode : allergies) {
                    allergiesList.add(allergienode.asText());
                }
                medicalrecord.setAllergies(allergiesList);
                medicalrecords.add(medicalrecord);
            }
        } else {
            logger.error("No medical records found");
        }
        return medicalrecords;
    }

}
