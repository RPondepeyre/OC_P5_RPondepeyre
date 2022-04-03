package com.example.safetynet.config;

import javax.annotation.PostConstruct;
import com.example.safetynet.repository.FirestationsRepository;
import com.example.safetynet.repository.MedicalrecordRepository;
import com.example.safetynet.repository.PersonsRepository;
import com.example.safetynet.service.GetDataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitDataConfig {

    
    private static final Logger logger = LoggerFactory.getLogger(InitDataConfig.class);

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
            logger.info("Firestations loaded");
        }catch(Exception e){
            logger.error("Can't load Firestions, error occured");
        }

        try{
            persons.save(getData.getPersons());
            logger.info("Persons loaded");
        }catch(Exception e){     
            logger.error("Can't load Persons, error occured");
        }

        try{
            medicalrecords.save(getData.getMedicalrecords());
            logger.info("Medical records loaded");
        }catch(Exception e){   
            logger.error("Can't load Medical records, error occured");
        }
    }

}
