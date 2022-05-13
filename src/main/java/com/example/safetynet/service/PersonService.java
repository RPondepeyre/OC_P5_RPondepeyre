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

    public List<Person> findByAddress(String address) {
        return repository.getAll().stream().filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }

    public List<Person> findByName(String firstName, String lastName) {
        return repository.getAll().stream()
                .filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    public List<Person> findByCity(String city) {
        return repository.getAll().stream().filter(person -> person.getCity().equals(city))
                .collect(Collectors.toList());
    }

    public Person addPerson(Person person) {
        repository.add(person);
        return person;

    }

    // public Person updatePerson(Person newperson) {}
}
