package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.dtobjects.FireAddressDTO;
import com.example.safetynet.dtobjects.PersonMedicalInfoDTO;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.FireAddressService;
import com.example.safetynet.service.FirestationService;
import com.example.safetynet.service.PersonService;
import com.example.safetynet.service.SortDataService;

@ExtendWith(MockitoExtension.class)
public class FireAddressServiceTest {

    @Mock
    FirestationService firestationService;

    @Mock
    PersonService personService;

    @Mock
    SortDataService sortDataService;

    @InjectMocks
    FireAddressService service;

    @Test
    void createFireAddressDTOTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {

        Firestation firestation = new Firestation();
        firestation.setStation(1);
        doReturn(firestation).when(firestationService).findByAddress(anyString());

        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        persons.add(person);
        persons.add(person);

        doReturn(persons).when(personService).findByAddress(anyString());
        PersonMedicalInfoDTO personmed = new PersonMedicalInfoDTO();
        personmed.setFirstname("firstname");
        personmed.setLastname("lastname");
        doReturn(personmed).when(sortDataService).createPersonInfo(any(Person.class));

        FireAddressDTO result = service.createFireAddressDTO("address");

        verify(sortDataService, times(2)).createPersonInfo(any(Person.class));

        assertThat(result.getStation()).isEqualTo(1);
        assertThat(result.getPersons().get(0).getFirstname()).isEqualTo("firstname");

    }

}
