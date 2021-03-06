package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.dtobjects.PersonsbyFirestationsDTO;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.PersonsbyFirestationService;
import com.example.safetynet.service.SortDataService;

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
    void PersonsbyFirestationsDTOTesttrue() throws RessourceNotFoundException, TooManyRessourcesFoundException {

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setAddress("address");
        person.setPhone("phone");
        rawlist.add(person);
        Person person2 = new Person();
        person2.setFirstName("firstName2");
        person2.setLastName("lastName2");
        rawlist.add(person2);

        when(sortDataService.findPersonsbystation((anyInt()))).thenReturn(rawlist);
        PersonsbyFirestationsDTO result = service.personsbyFirestationDTO(0);

        assertThat(result.getPersons()).hasSize(2);
        assertThat(result.getPersons().get(0).getFirstName()).isEqualTo("firstName");
        assertThat(result.getPersons().get(0).getLastName()).isEqualTo("lastName");
        assertThat(result.getPersons().get(0).getAddress()).isEqualTo("address");
        assertThat(result.getPersons().get(0).getPhone()).isEqualTo("phone");
        assertThat(result.getPersons().get(1).getFirstName()).isEqualTo("firstName2");
        assertThat(result.getNbAdults()).isZero();
        assertThat(result.getNbChildren()).isZero();

    }

    @Test
    void PersonsbyFirestationsDTOTestfalse() throws RessourceNotFoundException, TooManyRessourcesFoundException {

        when(sortDataService.findPersonsbystation((anyInt()))).thenReturn(rawlist);
        PersonsbyFirestationsDTO result = service.personsbyFirestationDTO(0);

        assertThat(result).isNull();
        assertThat(logCaptor.getErrorLogs()).containsExactly("La station mentionn??e n'existe pas");

    }

}
