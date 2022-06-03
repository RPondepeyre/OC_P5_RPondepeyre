package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.MedicalrecordRepository;
import com.example.safetynet.service.MedicalrecordService;
import com.example.safetynet.service.PersonService;

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

    @Mock
    PersonService personService;

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
    void findByPersonTrue() throws RessourceNotFoundException, TooManyRessourcesFoundException {
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
    void findByPersonEmpty() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastNamefalse");

        Medicalrecord record = new Medicalrecord();
        record.setFirstName("firstName");
        record.setLastName("lastName");

        List<Medicalrecord> records = new ArrayList<>();

        records.add(record);

        doReturn(records).when(repository).getAll();

        RessourceNotFoundException thrown = assertThrows(RessourceNotFoundException.class,
                () -> {
                    service.findByPerson(person);

                });
        assertThat(thrown.getMessage()).isEqualTo("Aucun dossier médical trouvé pour le nom: firstName lastNamefalse");
        assertThat(logCaptor.getErrorLogs())
                .containsExactly("Aucun dossier médical trouvé pour le nom: firstName lastNamefalse");

    }

    @Test
    void findByPersonEmptyfilter() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person person = new Person();
        person.setFirstName("firstNamefalse");
        person.setLastName("lastName");

        Medicalrecord record = new Medicalrecord();
        record.setFirstName("firstName");
        record.setLastName("lastName");

        List<Medicalrecord> records = new ArrayList<>();

        records.add(record);

        doReturn(records).when(repository).getAll();

        RessourceNotFoundException thrown = assertThrows(RessourceNotFoundException.class,
                () -> {
                    service.findByPerson(person);

                });
        assertThat(thrown.getMessage()).isEqualTo("Aucun dossier médical trouvé pour le nom: firstNamefalse lastName");
        assertThat(logCaptor.getErrorLogs())
                .containsExactly("Aucun dossier médical trouvé pour le nom: firstNamefalse lastName");

    }

    @Test
    void findByPersonMultiple() throws RessourceNotFoundException, TooManyRessourcesFoundException {
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

        TooManyRessourcesFoundException thrown = assertThrows(TooManyRessourcesFoundException.class,
                () -> {
                    service.findByPerson(person);

                });
        assertThat(thrown.getMessage())
                .isEqualTo("Plusieurs dossiers médicaux trouvés pour le nom: firstName lastName");
        assertThat(logCaptor.getErrorLogs())
                .containsExactly("Plusieurs dossiers médicaux trouvés pour le nom: firstName lastName");

    }

    @Test
    void addMedicalRecordTest() {
        Medicalrecord medicalrecord = new Medicalrecord();
        service.addMedicalRecord(medicalrecord);
        verify(repository).add(medicalrecord);
    }

    @Test
    void updateMedicalRecordTestNotNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord record = new Medicalrecord();
        record.setFirstName("firstName");
        record.setLastName("lastName");
        LocalDate birthdate = LocalDate.of(2000, 01, 01);
        List<String> allergies = new ArrayList<>();
        List<String> medications = new ArrayList<>();
        allergies.add("allergie");
        medications.add("medications");
        record.setBirthdate(birthdate);
        record.setAllergies(allergies);
        record.setMedications(medications);

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");

        Medicalrecord oldrecord = new Medicalrecord();
        oldrecord.setFirstName("firstName");
        oldrecord.setLastName("lastName");
        List<Medicalrecord> recordlist = new ArrayList<>();
        recordlist.add(oldrecord);

        doReturn(person).when(personService).findByName("firstName", "lastName");
        doReturn(recordlist).when(repository).getAll();

        Medicalrecord result = service.updateMedicalrecord(record);

        verify(repository).update(anyInt(), any(Medicalrecord.class));
        assertThat(result.getAllergies()).isEqualTo(record.getAllergies());
        assertThat(result.getBirthdate()).isEqualTo(record.getBirthdate());
        assertThat(result.getMedications()).isEqualTo(record.getMedications());

    }

    @Test
    void updateMedicalRecordTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord record = new Medicalrecord();
        record.setFirstName("firstName");
        record.setLastName("lastName");

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");

        Medicalrecord oldrecord = new Medicalrecord();
        oldrecord.setFirstName("firstName");
        oldrecord.setLastName("lastName");
        LocalDate birthdate = LocalDate.of(2000, 01, 01);
        List<String> allergies = new ArrayList<>();
        List<String> medications = new ArrayList<>();
        allergies.add("allergie");
        medications.add("medications");
        oldrecord.setBirthdate(birthdate);
        oldrecord.setAllergies(allergies);
        oldrecord.setMedications(medications);

        List<Medicalrecord> recordlist = new ArrayList<>();
        recordlist.add(oldrecord);

        doReturn(person).when(personService).findByName("firstName", "lastName");
        doReturn(recordlist).when(repository).getAll();

        Medicalrecord result = service.updateMedicalrecord(record);

        verify(repository).update(anyInt(), any(Medicalrecord.class));
        assertThat(result.getAllergies()).isEqualTo(oldrecord.getAllergies());
        assertThat(result.getBirthdate()).isEqualTo(oldrecord.getBirthdate());
        assertThat(result.getMedications()).isEqualTo(oldrecord.getMedications());

    }

    @Test
    void deleteMedicalrecordTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord record = new Medicalrecord();
        record.setFirstName("firstName");
        record.setLastName("lastName");

        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");

        Medicalrecord oldrecord = new Medicalrecord();
        oldrecord.setFirstName("firstName");
        oldrecord.setLastName("lastName");
        LocalDate birthdate = LocalDate.of(2000, 01, 01);
        List<String> allergies = new ArrayList<>();
        List<String> medications = new ArrayList<>();
        allergies.add("allergie");
        medications.add("medications");
        oldrecord.setBirthdate(birthdate);
        oldrecord.setAllergies(allergies);
        oldrecord.setMedications(medications);

        List<Medicalrecord> recordlist = new ArrayList<>();
        recordlist.add(oldrecord);
        doReturn(person).when(personService).findByName("firstName", "lastName");
        doReturn(recordlist).when(repository).getAll();

        Medicalrecord result = service.deleteMedicalrecord(record);

        verify(repository).delete(any(Medicalrecord.class));
        assertThat(result.getAllergies()).isEqualTo(oldrecord.getAllergies());
        assertThat(result.getBirthdate()).isEqualTo(oldrecord.getBirthdate());
        assertThat(result.getMedications()).isEqualTo(oldrecord.getMedications());

    }

}
