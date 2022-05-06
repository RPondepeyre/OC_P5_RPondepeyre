package com.example.safetynet.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityEmailService {

    @Autowired
    PersonService personService;

    public Set<String> communityEmail(String city) {
        List<Person> persons = personService.findByCity(city);
        Set<String> result = new HashSet<>();
        for (Person person : persons) {
            result.add(person.getEmail());
        }
        return result;
    }
}
