package com.example.safetynet.config;

import javax.annotation.PostConstruct;
import com.example.safetynet.repository.FirestationsRepository;
import com.example.safetynet.repository.MedicalrecordRepository;
import com.example.safetynet.repository.PersonsRepository;
import com.example.safetynet.service.GetDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDataConfig {

    @Autowired
    GetDataService getData;

    @Autowired
    FirestationsRepository firestations;

    @Autowired
    PersonsRepository persons;

    @Autowired
    MedicalrecordRepository medicalrecords;

    @PostConstruct
    private void initData(){

        try{
            firestations.save(getData.getFirestations());
            System.out.println("Firestations loaded");
            //TODO Logg info succes
        }catch(Exception e){
            //TODO Logg error erreur
        }

        try{
            persons.save(getData.getPersons());
            System.out.println("Persons loaded");
            //TODO Logg info succes
        }catch(Exception e){
            //TODO Logg error erreur
        }

        try{
            medicalrecords.save(getData.getMedicalrecords());
            System.out.println("MedicalRecords loaded");
            //TODO Logg info succes
        }catch(Exception e){
            //TODO Logg error erreur
        }
    }

}
