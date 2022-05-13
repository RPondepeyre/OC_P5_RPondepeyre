package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.ChildAlertDTO;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.ChildAlertService;
import com.example.safetynet.service.PersonService;
import com.example.safetynet.service.SortDataService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChildAlertServiceTest {

    @Mock
    PersonService personService;

    @Mock
    SortDataService sortDataService;

    @InjectMocks
    ChildAlertService service;

    @Test
    void childAlertDTOTest() {
        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");

        Person person2 = new Person();
        person2.setFirstName("firstName2");
        person2.setLastName("lastName");

        Person person3 = new Person();
        person3.setFirstName("firstName3");
        person3.setLastName("lastName");

        List<Person> persons = new ArrayList<>();

        persons.add(person);
        persons.add(person2);
        persons.add(person3);

        doReturn(persons).when(personService).findByAdress(anyString());
        doReturn(15, 12, 20).when(sortDataService).personAge(any(Person.class));

        List<ChildAlertDTO> result = service.childAlertDTO("adress");

        verify(sortDataService, times(3)).personAge(any(Person.class));
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getPersons()).hasSize(2);
        assertThat(result.get(0).getLastName()).isEqualTo("lastName");
        assertThat(result.get(0).getFirstname()).isEqualTo("firstName");
        assertThat(result.get(0).getAge()).isEqualTo(15);
        assertThat(result.get(1).getAge()).isEqualTo(12);

    }
}
