package com.example.safetynet.service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.example.safetynet.DTO.PersonMedicalInfoDTO;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SortDataService {

  private static final Logger logger = LoggerFactory.getLogger(SortDataService.class);

  @Autowired
  FirestationService firestationService;

  @Autowired
  PersonService personService;

  @Autowired
  MedicalrecordService medicalrecordService;

  public List<Person> findPersonsbystation(int stationIn) {
    return firestationService.findByStation(stationIn).stream()
        .map(Firestation::getAdress)
        .flatMap(adress -> personService.findByAdress(adress).stream())
        .collect(Collectors.toList());
  }

  public int personAge(Person person) {
    Predicate<Medicalrecord> byName = medicalrecord -> medicalrecord.getFirstName().equals(person.getFirstName())
        && medicalrecord.getLastName().equals(person.getLastName());
    List<Medicalrecord> mrs = medicalrecordService.findAllMedicalRecords().stream().filter(byName)
        .collect(Collectors.toList());
    Medicalrecord medicalrecord;
    if (mrs.size() == 1) {
      medicalrecord = mrs.get(0);
      return LocalDate.now().compareTo(medicalrecord.getBirthdate());
    } else if (mrs.isEmpty()) {
      logger.error("No Medical Record found for this person");
      return 0;
    } else {
      logger.error("Multiple persons with this firstname and lastname combination founded");
      return 0;
    }
  }

  public int adultNumber(List<Person> persons) {
    int adults = 0;
    for (Person person : persons) {
      if (personAge(person) > 18) {
        adults++;
      }
    }
    return adults;
  }

  public int childNumber(List<Person> persons) {
    int children = 0;
    for (Person person : persons) {
      if (personAge(person) < 18) {
        children++;
      }
    }
    return children;
  }

  public PersonMedicalInfoDTO createPersonInfo(Person person) {

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
