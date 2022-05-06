package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.safetynet.DTO.SChildAlertDTO;
import com.example.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SChildAlertService {

    @Autowired
    PersonService personService;

    @Autowired
    SortDataService sortDataService;

    public List<SChildAlertDTO> childAlertDTO(String adress) {
        List<Person> rawlist = personService.findByAdress(adress);
        List<SChildAlertDTO> result = new ArrayList<>();
        for (Person person : rawlist) {
            int age = sortDataService.personAge(person);
            if (age < 18) {
                SChildAlertDTO childAlertDTO = new SChildAlertDTO();
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
