package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.PhoneAlertService;
import com.example.safetynet.service.SortDataService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PhoneAlertServiceTest {

    @Mock
    SortDataService sortDataService;

    @InjectMocks
    PhoneAlertService service;

    @Test
    void createPhoneListTest() throws RessourceNotFoundException {
        List<Person> persons = new ArrayList<>();

        Person person1 = new Person();
        person1.setPhone("phone1");
        Person person2 = new Person();
        person2.setPhone("phone2");

        persons.add(person1);
        persons.add(person2);

        doReturn(persons).when(sortDataService).findPersonsbystation(anyInt());

        Set<String> result = service.createPhoneList(1);

        assertThat(result).contains("phone1", "phone2");

    }
}
