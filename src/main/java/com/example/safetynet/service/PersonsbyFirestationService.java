package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.PersonsDTO;
import com.example.safetynet.DTO.PersonsbyFirestationsDTO;
import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
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

  public PersonsbyFirestationsDTO personsbyFirestationDTO(int stationNumber)
      throws RessourceNotFoundException, TooManyRessourcesFoundException {
    List<Person> rawlist = service.findPersonsbystation(stationNumber);
    if (!rawlist.isEmpty()) {
      PersonsbyFirestationsDTO result = new PersonsbyFirestationsDTO();
      result.setPersons(createPersonsDTO(rawlist));
      result.setNbAdults(service.adultNumber(rawlist));
      result.setNbChildren(service.childNumber(rawlist));
      return result;
    } else {
      logger.error("La station mentionnée n'existe pas");
      return null;
    }

  }

  public List<PersonsDTO> createPersonsDTO(List<Person> persons) {
    List<PersonsDTO> personsDTOs = new ArrayList<>();
    for (Person person : persons) {
      PersonsDTO dto = new PersonsDTO();
      dto.setFirstName(person.getFirstName());
      dto.setLastName(person.getLastName());
      dto.setAddress(person.getAddress());
      dto.setPhone(person.getPhone());
      personsDTOs.add(dto);
    }

    return personsDTOs;
  }

  // Get1: Liste de personnes couvertes par la caserne de pompiers correspondante
  // au paramêtre (ID)
  // Prénom, Nom, Addresse, téléphone, + décompte du nombre d'adultes et d'enfants
  // dans la zone

}