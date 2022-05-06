package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.PersonMedicalInfoDTO;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.service.FirestationService;
import com.example.safetynet.service.MedicalrecordService;
import com.example.safetynet.service.PersonService;
import com.example.safetynet.service.SortDataService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    Person adult = new Person();
    Person child = new Person();

    List<Person> persons = new ArrayList<>();
    List<Firestation> stations = new ArrayList<>();
    List<Medicalrecord> records = new ArrayList<>();

    @BeforeEach
    public void SetUp() {

        person.setFirstName("firstName");
        person.setLastName("lastName");
        person.setAdress("adress");

        station.setAdress("adress");
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

        adult.setFirstName("adult");
        adult.setLastName("adult");

        child.setFirstName("child");
        child.setLastName("child");
    }

    @Test
    void findPersonsbyStationTest() {

        persons.add(person);
        stations.add(station);
        doReturn(stations).when(firestationService).findByStation(1);
        doReturn(persons).when(personService).findByAdress("adress");
        List<Person> result = service.findPersonsbystation(1);

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getFirstName()).isEqualTo("firstName");
        assertThat(result.get(0).getLastName()).isEqualTo("lastName");
    }

    @Test
    void personAgeTesttrue() {
        persons.add(person);
        records.add(record);
        doReturn(records).when(medicalrecordService).findAllMedicalRecords();

        Person person = persons.get(0);
        int result = service.personAge(person);

        assertThat(result).isEqualTo(20);
    }

    @Test
    void personAgeTestnorecordfirstname() {
        persons.add(person);
        Medicalrecord record = new Medicalrecord();
        record.setFirstName("null");
        record.setLastName("null");
        records.add(record);
        doReturn(records).when(medicalrecordService).findAllMedicalRecords();

        Person person = persons.get(0);
        int result = service.personAge(person);

        assertThat(logCaptor.getErrorLogs()).containsExactly("No Medical Record found for this person");
        assertThat(result).isZero();
    }

    @Test
    void personAgeTestnorecordlastname() {
        persons.add(person);
        records.clear();
        Medicalrecord record = new Medicalrecord();
        record.setFirstName("firstName");
        record.setLastName("null");
        records.add(record);
        doReturn(records).when(medicalrecordService).findAllMedicalRecords();

        Person person = persons.get(0);
        int result = service.personAge(person);

        assertThat(logCaptor.getErrorLogs()).containsExactly("No Medical Record found for this person");
        assertThat(result).isZero();
    }

    @Test
    void personAgeTestmultiple() {
        records.add(record);
        records.add(record);
        persons.add(person);
        doReturn(records).when(medicalrecordService).findAllMedicalRecords();

        Person person = persons.get(0);
        int result = service.personAge(person);

        assertThat(logCaptor.getErrorLogs())
                .containsExactly("Multiple persons with this firstname and lastname combination founded");
        assertThat(result).isZero();
    }

    @Test
    void adultNumberTest() {
        records.add(adultrecord);
        records.add(childrecord);
        doReturn(records).when(medicalrecordService).findAllMedicalRecords();
        for (int i = 0; i < 5; i++) {
            persons.add(adult);
        }
        for (int i = 0; i < 3; i++) {
            persons.add(child);
        }
        assertThat(service.adultNumber(persons)).isEqualTo(5);

    }

    @Test
    void childNumberTest() {
        records.add(adultrecord);
        records.add(childrecord);
        doReturn(records).when(medicalrecordService).findAllMedicalRecords();
        for (int i = 0; i < 5; i++) {
            persons.add(adult);
        }
        for (int i = 0; i < 3; i++) {
            persons.add(child);
        }
        assertThat(service.childNumber(persons)).isEqualTo(3);

    }

    @Test
    void personMedicalInfoTest() {
        records.add(record);
        doReturn(record).when(medicalrecordService).findByPerson(any(Person.class));
        doReturn(records).when(medicalrecordService).findAllMedicalRecords();
        PersonMedicalInfoDTO result = service.createPersonInfo(person);

        assertThat(result.getFirstname()).isEqualTo("firstName");
        assertThat(result.getLastname()).isEqualTo("lastName");
        assertThat(result.getAge()).isEqualTo(20);
        assertThat(result.getMedications().get(0)).isEqualTo("medications");
        assertThat(result.getAllergies().get(0)).isEqualTo("allergies");

    }
}
