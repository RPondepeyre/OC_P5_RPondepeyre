package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.PersonsbyFirestationsDTO;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.PersonsbyFirestationService;
import com.example.safetynet.service.SortDataService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.altindag.log.LogCaptor;

@ExtendWith(MockitoExtension.class)
public class PersonsbyFirestationServiceTest {

    @Mock
    SortDataService sortDataService;

    @InjectMocks
    PersonsbyFirestationService service = new PersonsbyFirestationService();

    List<Person> rawlist = new ArrayList<>();

    LogCaptor logCaptor = LogCaptor.forClass(PersonsbyFirestationService.class);

    @BeforeEach
    void setUp() {

    }

    @Test
    void PersonsbyFirestationsDTOTesttrue() {

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        rawlist.add(person);
        Person person2 = new Person();
        person2.setFirstName("firstName2");
        person2.setLastName("lastName2");
        rawlist.add(person2);

        when(sortDataService.findPersonsbystation((anyInt()))).thenReturn(rawlist);
        PersonsbyFirestationsDTO result = service.personsbyFirestationDTO(0);

        assertThat(result.getPersons()).hasSize(2);
        assertThat(result.getPersons().get(0).getFirstName()).isEqualTo("firstName");
        assertThat(result.getPersons().get(1).getFirstName()).isEqualTo("firstName2");

    }

    @Test
    void PersonsbyFirestationsDTOTestfalse() {

        when(sortDataService.findPersonsbystation((anyInt()))).thenReturn(rawlist);
        PersonsbyFirestationsDTO result = service.personsbyFirestationDTO(0);

        assertThat(result).isNull();
        assertThat(logCaptor.getErrorLogs()).containsExactly("La station mentionnée n'existe pas");

    }

}
