package com.example.safetynet.UnitTests.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.repository.MedicalrecordRepository;

public class MedicalRecordRepositoryTests {
    MedicalrecordRepository repository = new MedicalrecordRepository();
    List<Medicalrecord> records = new ArrayList<>();
    Medicalrecord record1 = new Medicalrecord();
    Medicalrecord record2 = new Medicalrecord();

    @BeforeEach
    public void SetUp() {
        record1.setFirstName("firstName1");
        record1.setLastName("lastName1");
        record2.setFirstName("firstName2");
        record2.setLastName("lastName2");
        records.add(record1);
        records.add(record2);

    }

    @Test
    void saveGetAll() {
        repository.save(records);
        assertThat(repository.getAll()).isEqualTo(records);

    }

    @Test
    void add() {
        repository.add(record1);
        assertThat(repository.getAll()).hasSize(1);
        assertThat(repository.getAll().get(0)).isEqualTo(record1);
    }

    @Test
    void update() {
        repository.save(records);
        Medicalrecord update = new Medicalrecord();
        update.setFirstName("firstName");
        update.setLastName("lastName");

        repository.update(0, update);
        assertThat(repository.getAll().get(0)).isEqualTo(update);
    }

    @Test
    void delete() {
        repository.save(records);
        repository.delete(record1);
        assertThat(repository.getAll()).hasSize(1);
        assertThat(repository.getAll().get(0)).isEqualTo(record2);
    }
}
