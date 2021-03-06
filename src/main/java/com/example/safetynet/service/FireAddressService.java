package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.dtobjects.FireAddressDTO;
import com.example.safetynet.dtobjects.PersonMedicalInfoDTO;
import com.example.safetynet.model.Person;

@Service
public class FireAddressService {

    @Autowired
    FirestationService firestationService;

    @Autowired
    PersonService personService;

    @Autowired
    SortDataService sortDataService;

    public FireAddressDTO createFireAddressDTO(String address)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        FireAddressDTO result = new FireAddressDTO();
        result.setStation(firestationService.findByAddress(address).getStation());
        List<Person> rawlist = personService.findByAddress(address);
        List<PersonMedicalInfoDTO> personInfos = new ArrayList<>();
        for (Person person : rawlist) {
            PersonMedicalInfoDTO personInfoDTO = sortDataService.createPersonInfo(person);
            personInfos.add(personInfoDTO);
        }
        result.setPersons(personInfos);
        return result;
    }

}
