package com.example.safetynet.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.FirestationsRepository;
import com.example.safetynet.repository.MedicalrecordRepository;
import com.example.safetynet.repository.PersonsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SortDataService {

  private static final Logger logger = LoggerFactory.getLogger(SortDataService.class);

    @Autowired
    FirestationsRepository firestationRepository;

    @Autowired
    PersonsRepository personsRepository;

    @Autowired
    MedicalrecordRepository medicalrecordRepository;

    public List<Person> findPersonsbystation(int stationIn){
      //On veut récupérer toutes les personnes pour une station
      //on sait une station elle a plusieurs adresses
      //une personne a une addresse

      //
  //  List<Person> residents = firestationRepository.getFirestationsByStations(stationIn).stream()
  //  .map(Firestation::getAdress)
 //   .flatMap(address ->).collect(Collectors.toList personsRepository.findByAddress(address)());

    //.flatMap(address -> personsRepository.findByAddress(address).stream().collect(Collectors.toList()));
      
      Predicate<Firestation> byStation = firestation -> firestation.getStation() == stationIn;
      List<Firestation> fs = firestationRepository.firestations.stream().filter(byStation).collect(Collectors.toList());
      List<Person> result = new ArrayList<>();
    
        for(int i=0; i< fs.size(); i++){
          int y = i;
          Predicate<Person> byAdress = person -> person.getAdress().equals(fs.get(y).getAdress());
          result.addAll(personsRepository.persons.stream().filter(byAdress).collect(Collectors.toList()));
          }
      return result;
      
    }

    public int personAge(Person person){
      Predicate<Medicalrecord> byName = medicalrecord -> medicalrecord.getFirstName().equals(person.getFirstName()) && medicalrecord.getLastName().equals(person.getLastName());
      List<Medicalrecord> mrs = medicalrecordRepository.medicalrecords.stream().filter(byName).collect(Collectors.toList());
      Medicalrecord medicalrecord = new Medicalrecord();
        if(mrs.size() == 1){
          medicalrecord = mrs.get(0);
        }else if(mrs.isEmpty()){
          logger.error("No Medical Record found for this person");
        }else{
          logger.error("Multiple persons with this firstname and lastname combination founded");
        }
        return LocalDate.now().compareTo(medicalrecord.getBirthdate());
    }

    public int adultNumber(List<Person> persons){
      int adults = 0;
      for (Person person : persons) {
        if(personAge(person)>18){
          adults++;
        }
      }
      return adults;
    }

    public int childNumber(List<Person> persons){
      int children = 0;
      for (Person person : persons) {
        if(personAge(person)<18){
          children++;
        }
      }
      return children;
    }

    //Get1: Liste de personnes couvertes par la caserne de pompiers correspondante au paramêtre (ID)
    //Prénom, Nom, Adresse, téléphone, + décompte du nombre d'adultes et d'enfants dans la zone


    
}