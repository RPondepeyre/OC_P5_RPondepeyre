package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.safetynet.DTO.childAlertDTO;
import com.example.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class childAlertService {

    @Autowired
    PersonService personService;

    @Autowired
    SortDataService sortDataService;

    public List<childAlertDTO> childAlertDTO(String adress) {
        List<Person> rawlist = personService.findByAdress(adress);
        List<childAlertDTO> result = new ArrayList<>();
        for (Person person : rawlist) {
            int age = sortDataService.personAge(person);
            if (age < 18) {
                childAlertDTO childAlertDTO = new childAlertDTO();
                childAlertDTO.setFirstname(person.getFirstName());
                childAlertDTO.setLastName(person.getLastName());
                childAlertDTO.setAge(age);
                childAlertDTO.setPersons(
                        rawlist.stream()
                                .filter(other -> !other.getFirstName().equals(person.getFirstName()))
                                .collect(Collectors.toList()));
                result.add(childAlertDTO);

            }

        }
        return result;
    }

}
