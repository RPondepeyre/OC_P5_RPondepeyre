package com.example.safetynet.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.FirestationsRepository;
import com.example.safetynet.repository.MedicalrecordRepository;
import com.example.safetynet.repository.PersonsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GetDataService {

    @Autowired
    FirestationsRepository firestationRepository;

    @Autowired
    PersonsRepository personsRepository;

    @Autowired
    MedicalrecordRepository medicalrecordsRepository;

    @Value("${datapath}")
    private String pathname;

    public JsonNode getRoot() throws IOException{
        
        File file = new File(pathname);
        ObjectMapper objectmapper = new ObjectMapper();
        return objectmapper.readTree(file);

    }

    public List<Firestation> getFirestations() throws IOException{
     
        JsonNode root = getRoot();

        JsonNode array = root.get("firestations");
        List<Firestation> firestations = new ArrayList<>();
        if(array.isArray()){
            for(JsonNode jsonNode: array){
                Firestation firestation = new Firestation();
                firestation.setAdress(jsonNode.get("address").asText());
                firestation.setStation(jsonNode.get("station").asInt());
                firestations.add(firestation);
            }
        }else{
            //TODO Mettre un logger.error
        }
        return firestations;    
        }
    
    public List<Person> getPersons() throws IOException{

        JsonNode root = getRoot();
 
        JsonNode array = root.get("persons");
        List<Person> persons = new ArrayList<Person>();
        if(array.isArray()){
            for(JsonNode jsonNode: array){
                Person person = new Person();
                person.setFirstName(jsonNode.get("firstName").asText());
                person.setLastName(jsonNode.get("lastName").asText());
                person.setAdress(jsonNode.get("address").asText());
                person.setCity(jsonNode.get("city").asText());
                person.setZip(jsonNode.get("zip").asText());
                person.setPhone(jsonNode.get("phone").asText());
                person.setEmail(jsonNode.get("email").asText());

                persons.add(person);
            }
        }else{
            //TODO Mettre un logger.error
        }
        return persons;    
        }

        public List<Medicalrecord> getMedicalrecords() throws IOException{

            JsonNode root = getRoot();
     
            JsonNode array = root.get("medicalrecords");
            List<Medicalrecord> medicalrecords = new ArrayList<Medicalrecord>();
            if(array.isArray()){
                for(JsonNode jsonNode: array){
                    Medicalrecord medicalrecord = new Medicalrecord();
                    medicalrecord.setFirstName(jsonNode.get("firstName").asText());
                    medicalrecord.setLastName(jsonNode.get("lastName").asText());
                    medicalrecord.setBirthdate(LocalDate.parse(jsonNode.get("birthdate").asText(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                    JsonNode medications = jsonNode.get("medications");
                    List<String> medicationList = new ArrayList<String>();
                        for(JsonNode medsnode: medications)
                        {
                            medicationList.add(medsnode.asText());
                        }
                    medicalrecord.setMedications(medicationList);
                    JsonNode allergies = jsonNode.get("allergies");
                    List<String> allergiesList = new ArrayList<String>();
                        for(JsonNode allergienode: allergies)
                        {
                            medicationList.add(allergienode.asText());
                        }
                    medicalrecord.setAllergies(allergiesList);
                    medicalrecords.add(medicalrecord);
                }
            }else{
                //TODO Mettre un logger.error
            }
            return medicalrecords;    
            }
    
}
