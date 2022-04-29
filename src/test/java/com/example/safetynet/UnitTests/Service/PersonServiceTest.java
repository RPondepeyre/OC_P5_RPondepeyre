package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonsRepository;
import com.example.safetynet.service.PersonService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    PersonsRepository repository;

    @InjectMocks
    PersonService service;

    @Test
    void findAllPersonsTest() {
        List<Person> persons = new ArrayList<>();

        when(repository.getAll()).thenReturn(persons);
        service.findAllPersons();

        verify(repository).getAll();
    }

    @Test
    void findByAdressTest() {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setAdress("true");
        person1.setFirstName("truefirstname");
        Person person2 = new Person();
        person2.setAdress("false");
        person2.setFirstName("falsefirstname");

        persons.add(person1);
        persons.add(person2);

        when(repository.getAll()).thenReturn(persons);

        List<Person> result = service.findByAdress("true");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("truefirstname");

    }
}
