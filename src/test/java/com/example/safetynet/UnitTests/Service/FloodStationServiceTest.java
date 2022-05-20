package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.PersonMedicalInfoDTO;
import com.example.safetynet.DTO.StationHousesDTO;
import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.FirestationService;
import com.example.safetynet.service.FloodStationsService;
import com.example.safetynet.service.PersonService;
import com.example.safetynet.service.SortDataService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FloodStationServiceTest {

    @Mock
    SortDataService sortDataService;

    @Mock
    FirestationService firestationService;

    @Mock
    PersonService personService;

    @InjectMocks
    FloodStationsService service;

    @Test
    void floodStationsHousesTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {

        List<Firestation> stations = new ArrayList<>();
        Firestation station = new Firestation();
        station.setAddress("address");
        station.setStation(1);
        stations.add(station);
        doReturn(stations).when(firestationService).findByStation(any());

        List<Person> persons = new ArrayList<>();
        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        persons.add(person);
        persons.add(person);
        doReturn(persons).when(personService).findByAddress(anyString());

        PersonMedicalInfoDTO personInfo = new PersonMedicalInfoDTO();
        personInfo.setFirstname("firstname");
        doReturn(personInfo).when(sortDataService).createPersonInfo(any());

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        List<StationHousesDTO> result = service.floodStationsHouses(integers);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getStationID()).isEqualTo(1);
        assertThat(result.get(0).getHouses()).hasSize(1);
        assertThat(result.get(0).getHouses().get(0).getAddress()).isEqualTo("address");
        assertThat(result.get(0).getHouses().get(0).getPersons()).hasSize(2);
    }

}
