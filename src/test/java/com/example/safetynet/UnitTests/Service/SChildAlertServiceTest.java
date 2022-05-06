package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.SChildAlertDTO;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.PersonService;
import com.example.safetynet.service.SChildAlertService;
import com.example.safetynet.service.SortDataService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SChildAlertServiceTest {

    @Mock
    PersonService personService;

    @Mock
    SortDataService sortDataService;

    @InjectMocks
    SChildAlertService service;

    @Test
    void childAlertDTOTest() {
        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");

        List<Person> persons = new ArrayList<>();

        persons.add(person);
        persons.add(person);

        doReturn(persons).when(personService).findByAdress(anyString());
        doReturn(15).when(sortDataService).personAge(any(Person.class));

        List<SChildAlertDTO> result = service.childAlertDTO("adress");

        verify(sortDataService, times(2)).personAge(any(Person.class));
        assertThat(result.get(0).getFirstname()).isEqualTo("firstName");

    }
}
