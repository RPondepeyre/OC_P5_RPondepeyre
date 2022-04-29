package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class phoneAlertService {

    @Autowired
    SortDataService sortDataService;

    public List<String> CreatePhoneList(int station) {
        List<Person> rawlist = sortDataService.findPersonsbystation(station);
        List<String> result = new ArrayList<>();
        for (Person person : rawlist) {
            result.add(person.getPhone());
        }
        return result;
    }
}
