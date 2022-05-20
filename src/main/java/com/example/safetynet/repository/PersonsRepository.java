package com.example.safetynet.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Person;

import org.springframework.stereotype.Repository;

@Repository
public class PersonsRepository {

    private List<Person> persons = new ArrayList<>();

    public void save(List<Person> persons) {
        this.persons = persons;
    }

    public void add(Person person) {
        this.persons.add(person);
    }

    public List<Person> getAll() {
        return this.persons;
    }

    public void update(int id, Person newperson) {
        this.persons.set(id, newperson);
    }

    public void delete(Person person) {
        this.persons.remove(person);
    }
}
