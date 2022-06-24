package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
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
import com.example.safetynet.dtobjects.PersonMedicalInfoDTO;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.FirestationService;
import com.example.safetynet.service.MedicalrecordService;
import com.example.safetynet.service.PersonService;
import com.example.safetynet.service.SortDataService;

import nl.altindag.log.LogCaptor;

@ExtendWith(MockitoExtension.class)
public class SortDataServiceTest {

    @Mock
    FirestationService firestationService;
    @Mock
    PersonService personService;
    @Mock
    MedicalrecordService medicalrecordService;
    @InjectMocks
    SortDataService service = new SortDataService();

    LogCaptor logCaptor = LogCaptor.forClass(SortDataService.class);

    Person person = new Person();
    Firestation station = new Firestation();
    Medicalrecord record = new Medicalrecord();
    Medicalrecord adultrecord = new Medicalrecord();
    Medicalrecord childrecord = new Medicalrecord();

    List<Person> persons = new ArrayList<>();
    List<Firestation> stations = new ArrayList<>();
    List<Medicalrecord> records = new ArrayList<>();

    @BeforeEach
    public void SetUp() {

        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setAddress("address");
        person.setPhone("phone");

        station.setAddress("address");
        station.setStation(1);

        List<String> medications = new ArrayList<>();
        medications.add("medications");
        List<String> allergies = new ArrayList<>();
        allergies.add("allergies");

        record.setFirstName("firstName");
        record.setLastName("lastName");
        record.setBirthdate(LocalDate.now().minusYears(20));
        record.setMedications(medications);
        record.setAllergies(allergies);

        adultrecord.setFirstName("adult");
        adultrecord.setLastName("adult");
        adultrecord.setBirthdate(LocalDate.now().minusYears(19));

        childrecord.setFirstName("child");
        childrecord.setLastName("child");
        childrecord.setBirthdate(LocalDate.now().minusYears(17));

    }

    @Test
    void findPersonsbyStationTest() throws RessourceNotFoundException {

        persons.add(person);
        stations.add(station);
        doReturn(stations).when(firestationService).findByStation(1);
        doReturn(persons).when(personService).findByAddress("address");
        List<Person> result = service.findPersonsbystation(1);

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getFirstName()).isEqualTo("firstName");
        assertThat(result.get(0).getLastName()).isEqualTo("lastName");
    }

    @Test
    void personAgeTesttrue() throws RessourceNotFoundException, TooManyRessourcesFoundException {

        records.add(record);
        doReturn(record).when(medicalrecordService).findByPerson(person);
        int result = service.personAge(person);

        assertThat(result).isEqualTo(20);
    }

    @Test
    void adultNumberTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {

        doReturn(adultrecord, childrecord).when(medicalrecordService).findByPerson(any(Person.class));
        for (int i = 0; i < 2; i++) {
            persons.add(person);
        }
        assertThat(service.adultNumber(persons)).isEqualTo(1);

    }

    @Test
    void childNumberTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {

        doReturn(childrecord, adultrecord).when(medicalrecordService).findByPerson(any(Person.class));
        for (int i = 0; i < 2; i++) {
            persons.add(person);
        }
        assertThat(service.childNumber(persons)).isEqualTo(1);

    }

    @Test
    void personMedicalInfoTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        records.add(record);
        doReturn(record).when(medicalrecordService).findByPerson(any(Person.class));
        PersonMedicalInfoDTO result = service.createPersonInfo(person);

        assertThat(result.getFirstname()).isEqualTo("firstName");
        assertThat(result.getLastname()).isEqualTo("lastName");
        assertThat(result.getAge()).isEqualTo(20);
        assertThat(result.getMedications().get(0)).isEqualTo("medications");
        assertThat(result.getAllergies().get(0)).isEqualTo("allergies");
        assertThat(result.getPhone()).isEqualTo("phone");

    }
}
