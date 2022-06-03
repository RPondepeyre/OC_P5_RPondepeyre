package com.example.safetynet.UnitTests.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.safetynet.model.Firestation;
import com.example.safetynet.repository.FirestationsRepository;

public class FirestationRepositoryTests {

    FirestationsRepository repository = new FirestationsRepository();
    List<Firestation> firestations = new ArrayList<>();
    Firestation firestation1 = new Firestation();
    Firestation firestation2 = new Firestation();

    @BeforeEach
    public void SetUp() {

        firestation1.setAddress("address1");
        firestation1.setStation(1);
        firestation2.setAddress("address2");
        firestation2.setStation(2);
        firestations.add(firestation1);
        firestations.add(firestation2);

    }

    @Test
    void saveGetAll() {
        repository.save(firestations);
        assertThat(repository.getAll()).isEqualTo(firestations);

    }

    @Test
    void add() {
        repository.add(firestation1);
        assertThat(repository.getAll()).hasSize(1);
        assertThat(repository.getAll().get(0)).isEqualTo(firestation1);
    }

    @Test
    void update() {
        repository.save(firestations);
        Firestation update = new Firestation();
        update.setAddress("address");
        update.setStation(2);

        repository.update(0, update);
        assertThat(repository.getAll().get(0)).isEqualTo(update);
    }

    @Test
    void delete() {
        repository.save(firestations);
        repository.delete(firestation1);
        assertThat(repository.getAll()).hasSize(1);
        assertThat(repository.getAll().get(0)).isEqualTo(firestation2);
    }
}
