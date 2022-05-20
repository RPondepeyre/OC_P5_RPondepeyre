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
    void findByAddressTest() {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setAddress("true");
        person1.setFirstName("truefirstname");
        Person person2 = new Person();
        person2.setAddress("false");
        person2.setFirstName("falsefirstname");

        persons.add(person1);
        persons.add(person2);

        when(repository.getAll()).thenReturn(persons);

        List<Person> result = service.findByAddress("true");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("truefirstname");

    }

    @Test
    void findByNameTestTrue() {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setAddress("true");
        person1.setFirstName("truefirstname");
        person1.setLastName("truelastName");
        Person person2 = new Person();
        person2.setAddress("false");
        person2.setFirstName("falsefirstname");
        person2.setLastName("falselastName");

        persons.add(person1);
        persons.add(person2);

        when(repository.getAll()).thenReturn(persons);

        Person result = service.findByName("truefirstname", "truelastName");

        assertThat(result.getAddress()).isEqualTo("true");

    }

    @Test
    void findByNameTestFalse() {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setAddress("true");
        person1.setFirstName("truefirstname");
        person1.setLastName("falselastName");
        Person person2 = new Person();
        person2.setAddress("false");
        person2.setFirstName("falsefirstname");
        person2.setLastName("truelastName");

        persons.add(person1);
        persons.add(person2);

        when(repository.getAll()).thenReturn(persons);

        Person result = service.findByName("truefirstname", "truelastName");

        assertThat(result).isNull();

    }

    @Test
    void findByCityTest() {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setCity("true");
        person1.setFirstName("truefirstname");
        Person person2 = new Person();
        person2.setCity("false");
        person2.setFirstName("falsefirstname");

        persons.add(person1);
        persons.add(person2);

        when(repository.getAll()).thenReturn(persons);

        List<Person> result = service.findByCity("true");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("truefirstname");

    }

}
