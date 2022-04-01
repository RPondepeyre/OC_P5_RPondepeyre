package com.example.safetynet.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Person;

import org.springframework.stereotype.Repository;

@Repository
public class PersonsRepository {


    public List<Person> persons = new ArrayList<>();

    public void save(List<Person> persons){
        this.persons = persons;
    }
    
}
