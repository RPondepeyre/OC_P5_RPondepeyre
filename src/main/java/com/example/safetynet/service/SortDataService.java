package com.example.safetynet.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.dtobjects.PersonMedicalInfoDTO;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;

@Service
public class SortDataService {

  @Autowired
  FirestationService firestationService;

  @Autowired
  PersonService personService;

  @Autowired
  MedicalrecordService medicalrecordService;

  public List<Person> findPersonsbystation(int stationIn) throws RessourceNotFoundException {
    return firestationService.findByStation(stationIn).stream()
        .map(Firestation::getAddress)
        .flatMap(address -> personService.findByAddress(address).stream())
        .collect(Collectors.toList());
  }

  public int personAge(Person person) throws RessourceNotFoundException, TooManyRessourcesFoundException {
    Medicalrecord medicalrecord = medicalrecordService.findByPerson(person);
    return LocalDate.now().compareTo(medicalrecord.getBirthdate());

  }

  public int adultNumber(List<Person> persons) throws RessourceNotFoundException, TooManyRessourcesFoundException {
    int adults = 0;
    for (Person person : persons) {
      if (personAge(person) > 18) {
        adults++;
      }
    }
    return adults;
  }

  public int childNumber(List<Person> persons) throws RessourceNotFoundException, TooManyRessourcesFoundException {
    int children = 0;
    for (Person person : persons) {
      if (personAge(person) < 18) {
        children++;
      }
    }
    return children;
  }

  public PersonMedicalInfoDTO createPersonInfo(Person person)
      throws RessourceNotFoundException, TooManyRessourcesFoundException {

    PersonMedicalInfoDTO personInfo = new PersonMedicalInfoDTO();
    personInfo.setFirstname(person.getFirstName());
    personInfo.setLastname(person.getLastName());
    personInfo.setPhone(person.getPhone());
    personInfo.setAge(personAge(person));
    personInfo.setMedications(medicalrecordService.findByPerson(person).getMedications());
    personInfo.setAllergies(medicalrecordService.findByPerson(person).getAllergies());
    return personInfo;

  }

}
