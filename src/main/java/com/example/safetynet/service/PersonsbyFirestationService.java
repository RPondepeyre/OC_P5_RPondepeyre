package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.PersonsDTO;
import com.example.safetynet.DTO.PersonsbyFirestationsDTO;
import com.example.safetynet.model.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonsbyFirestationService {

  private static final Logger logger = LoggerFactory.getLogger(PersonsbyFirestationService.class);

  @Autowired
  SortDataService service;

  public PersonsbyFirestationsDTO personsbyFirestationDTO(int stationNumber) {
    List<Person> rawlist = service.findPersonsbystation(stationNumber);
    if (!rawlist.isEmpty()) {
      return new PersonsbyFirestationsDTO(
          createPersonsDTO(rawlist),
          service.adultNumber(rawlist),
          service.childNumber(rawlist));
    } else {
      logger.error("La station mentionnée n'existe pas");
      return null;
    }

  }

  public List<PersonsDTO> createPersonsDTO(List<Person> persons) {
    List<PersonsDTO> personsDTOs = new ArrayList<>();
    for (Person person : persons) {
      PersonsDTO dto = new PersonsDTO(
          person.getFirstName(),
          person.getLastName(),
          person.getAdress(),
          person.getPhone());
      personsDTOs.add(dto);
    }

    return personsDTOs;
  }

  // Get1: Liste de personnes couvertes par la caserne de pompiers correspondante
  // au paramêtre (ID)
  // Prénom, Nom, Adresse, téléphone, + décompte du nombre d'adultes et d'enfants
  // dans la zone

}