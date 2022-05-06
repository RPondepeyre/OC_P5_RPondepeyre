package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityEmailService {

    @Autowired
    PersonService personService;

    public List<String> communityEmail(String city) {
        List<Person> persons = personService.findByCity(city);
        List<String> result = new ArrayList<>();
        for (Person person : persons) {
            result.add(person.getEmail());
        }
        return result;
    }
}
