package com.example.safetynet.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonsRepository repository;

    public List<Person> findAllPersons() {
        return repository.getAll();
    }

    public List<Person> findByAdress(String adress) {
        return repository.getAll().stream().filter(person -> person.getAdress().equals(adress))
                .collect(Collectors.toList());
    }
}
