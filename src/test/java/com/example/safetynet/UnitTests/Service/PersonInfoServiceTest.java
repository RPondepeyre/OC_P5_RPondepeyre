package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.PersonInfoDTO;
import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.MedicalrecordService;
import com.example.safetynet.service.PersonInfoService;
import com.example.safetynet.service.PersonService;
import com.example.safetynet.service.SortDataService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonInfoServiceTest {

    @Mock
    PersonService personService;

    @Mock
    SortDataService sortDataService;

    @Mock
    MedicalrecordService medicalrecordService;

    @InjectMocks
    PersonInfoService service;

    @Test
    void personInfo() throws RessourceNotFoundException, TooManyRessourcesFoundException {

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setAddress("address");
        person.setEmail("email");
        Medicalrecord record = new Medicalrecord();
        List<String> allergies = new ArrayList<>();
        allergies.add("allergies");
        List<String> medications = new ArrayList<>();
        medications.add("medications");
        record.setAllergies(allergies);
        record.setMedications(medications);
        doReturn(person).when(personService).findByName(anyString(), anyString());
        doReturn(20).when(sortDataService).personAge(any(Person.class));
        doReturn(record).when(medicalrecordService).findByPerson(any(Person.class));

        List<PersonInfoDTO> result = service.personInfo("firstName", "lastName");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMedications().get(0)).isEqualTo("medications");
        assertThat(result.get(0).getAllergies().get(0)).isEqualTo("allergies");
        assertThat(result.get(0).getFirstname()).isEqualTo("firstName");
        assertThat(result.get(0).getLastname()).isEqualTo("lastName");
        assertThat(result.get(0).getAddress()).isEqualTo("address");
        assertThat(result.get(0).getEmail()).isEqualTo("email");
        assertThat(result.get(0).getAge()).isEqualTo(20);

    }

}
