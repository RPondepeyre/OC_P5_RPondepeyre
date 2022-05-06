package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.MedicalrecordRepository;
import com.example.safetynet.service.MedicalrecordService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.altindag.log.LogCaptor;

@ExtendWith(MockitoExtension.class)
public class MedicalrecordServiceTest {

    LogCaptor logCaptor = LogCaptor.forClass(MedicalrecordService.class);

    @Mock
    MedicalrecordRepository repository;

    @InjectMocks
    MedicalrecordService service;

    @Test
    void findAllRecordsTest() {
        List<Medicalrecord> records = new ArrayList<>();

        when(repository.getAll()).thenReturn(records);
        service.findAllMedicalRecords();

        verify(repository).getAll();
    }

    @Test
    void findByPersonTrue() {
        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");

        Medicalrecord record = new Medicalrecord();
        record.setFirstName("firstName");
        record.setLastName("lastName");

        List<Medicalrecord> records = new ArrayList<>();

        records.add(record);

        doReturn(records).when(repository).getAll();

        Medicalrecord result = service.findByPerson(person);

        assertThat(result.getFirstName()).isEqualTo("firstName");

    }

    @Test
    void findByPersonEmpty() {
        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastNamefalse");

        Medicalrecord record = new Medicalrecord();
        record.setFirstName("firstName");
        record.setLastName("lastName");

        List<Medicalrecord> records = new ArrayList<>();

        records.add(record);

        doReturn(records).when(repository).getAll();

        Medicalrecord result = service.findByPerson(person);

        assertThat(result).isNull();
        assertThat(logCaptor.getErrorLogs()).containsExactly("Aucun dossier médical trouvé pour cette personne");

    }

    @Test
    void findByPersonEmptyfilter() {
        Person person = new Person();
        person.setFirstName("firstNamefalse");
        person.setLastName("lastName");

        Medicalrecord record = new Medicalrecord();
        record.setFirstName("firstName");
        record.setLastName("lastName");

        List<Medicalrecord> records = new ArrayList<>();

        records.add(record);

        doReturn(records).when(repository).getAll();

        Medicalrecord result = service.findByPerson(person);

        assertThat(result).isNull();
        assertThat(logCaptor.getErrorLogs()).containsExactly("Aucun dossier médical trouvé pour cette personne");

    }

    @Test
    void findByPersonMultiple() {
        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");

        Medicalrecord record = new Medicalrecord();
        record.setFirstName("firstName");
        record.setLastName("lastName");

        List<Medicalrecord> records = new ArrayList<>();

        records.add(record);
        records.add(record);

        doReturn(records).when(repository).getAll();

        Medicalrecord result = service.findByPerson(person);

        assertThat(result).isNull();
        assertThat(logCaptor.getErrorLogs()).containsExactly("Plusieurs dossiers medicaux trouvés pour cette personne");

    }

}
