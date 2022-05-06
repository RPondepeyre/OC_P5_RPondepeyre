package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.FireAdressDTO;
import com.example.safetynet.DTO.PersonMedicalInfoDTO;
import com.example.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireAdressService {

    @Autowired
    FirestationService firestationService;

    @Autowired
    PersonService personService;

    @Autowired
    SortDataService sortDataService;

    public FireAdressDTO createFireAdressDTO(String address) {
        FireAdressDTO result = new FireAdressDTO();
        result.setStation(firestationService.findByAdress(address).getStation());
        List<Person> rawlist = personService.findByAdress(address);
        List<PersonMedicalInfoDTO> personInfos = new ArrayList<>();
        for (Person person : rawlist) {
            PersonMedicalInfoDTO personInfoDTO = sortDataService.createPersonInfo(person);
            personInfos.add(personInfoDTO);
        }
        result.setPersons(personInfos);
        return result;
    }

}
