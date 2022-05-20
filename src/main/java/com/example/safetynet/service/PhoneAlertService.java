package com.example.safetynet.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneAlertService {

    @Autowired
    SortDataService sortDataService;

    public Set<String> createPhoneList(int station) throws RessourceNotFoundException {
        List<Person> rawlist = sortDataService.findPersonsbystation(station);
        Set<String> result = new HashSet<>();
        for (Person person : rawlist) {
            result.add(person.getPhone());
        }
        return result;
    }
}
