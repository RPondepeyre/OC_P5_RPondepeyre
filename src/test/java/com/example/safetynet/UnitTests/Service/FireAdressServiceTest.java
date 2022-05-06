package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.FireAdressDTO;
import com.example.safetynet.DTO.PersonMedicalInfoDTO;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.FireAdressService;
import com.example.safetynet.service.FirestationService;
import com.example.safetynet.service.PersonService;
import com.example.safetynet.service.SortDataService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FireAdressServiceTest {

    @Mock
    FirestationService firestationService;

    @Mock
    PersonService personService;

    @Mock
    SortDataService sortDataService;

    @InjectMocks
    FireAdressService service;

    @Test
    void createFireAdressDTOTest() {

        Firestation firestation = new Firestation();
        firestation.setStation(1);
        doReturn(firestation).when(firestationService).findByAdress(anyString());

        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        persons.add(person);
        persons.add(person);

        doReturn(persons).when(personService).findByAdress(anyString());
        PersonMedicalInfoDTO personmed = new PersonMedicalInfoDTO();
        personmed.setFirstname("firstname");
        personmed.setLastname("lastname");
        doReturn(personmed).when(sortDataService).createPersonInfo(any(Person.class));

        FireAdressDTO result = service.createFireAdressDTO("address");

        verify(sortDataService, times(2)).createPersonInfo(any(Person.class));

        assertThat(result.getStation()).isEqualTo(1);
        assertThat(result.getPersons().get(0).getFirstname()).isEqualTo("firstname");

    }

}
