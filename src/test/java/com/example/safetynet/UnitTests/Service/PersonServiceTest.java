package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonsRepository;
import com.example.safetynet.service.PersonService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.altindag.log.LogCaptor;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    LogCaptor logCaptor = LogCaptor.forClass(PersonService.class);

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
    void findByNameTestTrue() throws RessourceNotFoundException, TooManyRessourcesFoundException {
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
    void findByNameTestEmpty() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setFirstName("truefirstname");
        person1.setLastName("falselastName");

        persons.add(person1);

        when(repository.getAll()).thenReturn(persons);

        RessourceNotFoundException thrown = assertThrows(RessourceNotFoundException.class,
                () -> {
                    service.findByName("truefirstname", "truelastName");
                });
        assertThat(thrown.getMessage()).isEqualTo("Aucune personne trouvée pour le nom: truefirstname truelastName");
        assertThat(logCaptor.getErrorLogs())
                .containsExactly("Aucune personne trouvée pour le nom: truefirstname truelastName");
    }

    @Test
    void findByNameTestTooMany() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setAddress("true");
        person1.setFirstName("truefirstname");
        person1.setLastName("truelastName");
        Person person2 = new Person();
        person2.setAddress("false");
        person2.setFirstName("truefirstname");
        person2.setLastName("truelastName");

        persons.add(person1);
        persons.add(person2);

        when(repository.getAll()).thenReturn(persons);

        TooManyRessourcesFoundException thrown = assertThrows(TooManyRessourcesFoundException.class,
                () -> {
                    service.findByName("truefirstname", "truelastName");
                });
        assertThat(thrown.getMessage())
                .isEqualTo("Plusieurs personnes trouvées pour le nom: truefirstname truelastName");
        assertThat(logCaptor.getErrorLogs())
                .containsExactly("Plusieurs personnes trouvées pour le nom: truefirstname truelastName");
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

    @Test
    void addPersonTest() {
        Person person = new Person();
        service.addPerson(person);
        verify(repository).add(person);
    }

    @Test
    void updatePersonTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person personfull = new Person();
        personfull.setFirstName("firstName");
        personfull.setLastName("lastName");
        personfull.setAddress("address");
        personfull.setCity("city");
        personfull.setEmail("email");
        personfull.setPhone("phone");
        personfull.setZip("zip");

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        List<Person> persons = new ArrayList<>();
        persons.add(person);

        Person fakeperson = new Person();
        fakeperson.setFirstName("fake");
        fakeperson.setLastName("fake");

        List<Person> baselist = new ArrayList<>();
        baselist.add(personfull);
        baselist.add(fakeperson);

        doReturn(baselist).when(repository).getAll();

        Person result = service.updatePerson(person);
        verify(repository).update(anyInt(), any(Person.class));
        assertThat(result.getAddress()).isEqualTo("address");
        assertThat(result.getCity()).isEqualTo("city");
        assertThat(result.getEmail()).isEqualTo("email");
    }

    @Test
    void updatePersonTestNotNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person personfull = new Person();
        personfull.setFirstName("firstName");
        personfull.setLastName("lastName");
        personfull.setAddress("address");
        personfull.setCity("city");
        personfull.setEmail("email");
        personfull.setPhone("phone");
        personfull.setZip("zip");

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setAddress("address2");
        person.setCity("city2");
        person.setEmail("email2");
        person.setPhone("phone2");
        person.setZip("zip2");
        List<Person> persons = new ArrayList<>();
        persons.add(person);

        Person fakeperson = new Person();
        fakeperson.setFirstName("fake");
        fakeperson.setLastName("fake");

        List<Person> baselist = new ArrayList<>();
        baselist.add(personfull);
        baselist.add(fakeperson);

        doReturn(baselist).when(repository).getAll();

        Person result = service.updatePerson(person);
        verify(repository).update(anyInt(), any(Person.class));
        assertThat(result.getAddress()).isEqualTo("address2");
        assertThat(result.getCity()).isEqualTo("city2");
        assertThat(result.getEmail()).isEqualTo("email2");
    }

    @Test
    void deletePerson() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        doReturn(persons).when(repository).getAll();
        service.deletePerson(person);
        verify(repository).delete(person);

    }
}
