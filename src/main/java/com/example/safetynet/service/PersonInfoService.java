package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.PersonInfoDTO;
import com.example.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonInfoService {

    @Autowired
    PersonService personService;
    @Autowired
    SortDataService sortDataService;
    @Autowired
    MedicalrecordService medicalrecordService;

    public List<PersonInfoDTO> personInfo(String firstName, String lastName) {
        List<Person> persons = personService.findByName(firstName, lastName);
        List<PersonInfoDTO> result = new ArrayList<>();
        for (Person person : persons) {
            PersonInfoDTO personInfoDTO = new PersonInfoDTO();
            personInfoDTO.setFirstname(person.getFirstName());
            personInfoDTO.setLastname(person.getLastName());
            personInfoDTO.setAdress(person.getAdress());
            personInfoDTO.setAge(sortDataService.personAge(person));
            personInfoDTO.setEmail(person.getEmail());
            personInfoDTO.setMedications(medicalrecordService.findByPerson(person).getMedications());
            personInfoDTO.setAllergies(medicalrecordService.findByPerson(person).getAllergies());
            result.add(personInfoDTO);
        }
        return result;
    }

}
