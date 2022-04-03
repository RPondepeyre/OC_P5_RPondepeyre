package com.example.safetynet.service;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonsRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SortDataServiceTest {

    @Autowired
    SortDataService service;
    
    @Autowired
    PersonsRepository personsRepository;

@Test
public void findPersonsbystationtest()
{
   service.findPersonsbystation(1);

}

@Test
public void personAgeTest(){
    Person connard = personsRepository.persons.get(0);

    service.personAge(connard);
}
    
}
