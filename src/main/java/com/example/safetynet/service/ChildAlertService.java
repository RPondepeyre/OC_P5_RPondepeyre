package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.safetynet.DTO.ChildAlertDTO;
import com.example.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChildAlertService {

    @Autowired
    PersonService personService;

    @Autowired
    SortDataService sortDataService;

    public List<ChildAlertDTO> childAlertDTO(String address) {
        List<Person> rawlist = personService.findByAddress(address);
        List<ChildAlertDTO> result = new ArrayList<>();
        for (Person person : rawlist) {
            int age = sortDataService.personAge(person);
            if (age < 18) {
                ChildAlertDTO childAlertDTO = new ChildAlertDTO();
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
