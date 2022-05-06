package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.example.safetynet.model.Person;
import com.example.safetynet.service.CommunityEmailService;
import com.example.safetynet.service.PersonService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommunityEmailServiceTest {

    @Mock
    PersonService personService;

    @InjectMocks
    CommunityEmailService service;

    @Test
    void communityEmailTest() {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setEmail("email1");
        person1.setCity("city");
        Person person2 = new Person();
        person2.setEmail("email2");
        person2.setCity("city");

        persons.add(person1);
        persons.add(person2);

        doReturn(persons).when(personService).findByCity(anyString());

        Set<String> result = service.communityEmail("city");

        assertThat(result).contains("email1", "email2");

    }

}
