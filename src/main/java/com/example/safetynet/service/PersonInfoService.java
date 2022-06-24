package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.dtobjects.PersonInfoDTO;
import com.example.safetynet.model.Person;

@Service
public class PersonInfoService {

    @Autowired
    PersonService personService;
    @Autowired
    SortDataService sortDataService;
    @Autowired
    MedicalrecordService medicalrecordService;

    public List<PersonInfoDTO> personInfo(String firstName, String lastName)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person person = personService.findByName(firstName, lastName);
        List<PersonInfoDTO> result = new ArrayList<>();
        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setFirstname(person.getFirstName());
        personInfoDTO.setLastname(person.getLastName());
        personInfoDTO.setAddress(person.getAddress());
        personInfoDTO.setAge(sortDataService.personAge(person));
        personInfoDTO.setEmail(person.getEmail());
        personInfoDTO.setMedications(medicalrecordService.findByPerson(person).getMedications());
        personInfoDTO.setAllergies(medicalrecordService.findByPerson(person).getAllergies());
        result.add(personInfoDTO);
        return result;
    }

}
