package com.example.safetynet.UnitTests.Repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonsRepository;

public class PersonsRepositoryTests {

    PersonsRepository repository = new PersonsRepository();
    List<Person> persons = new ArrayList<>();
    Person person1 = new Person();
    Person person2 = new Person();

    @BeforeEach
    public void SetUp() {

        person1.setFirstName("firstName1");
        person1.setLastName("lastName1");
        person2.setFirstName("firstName2");
        person2.setLastName("lastName2");

        persons.add(person1);
        persons.add(person2);

    }

    @Test
    void saveGetAll() {
        repository.save(persons);
        assertThat(repository.getAll()).isEqualTo(persons);

    }

    @Test
    void add() {
        repository.add(person1);
        assertThat(repository.getAll()).hasSize(1);
        assertThat(repository.getAll().get(0)).isEqualTo(person1);
    }

    @Test
    void update() {
        repository.save(persons);
        Person update = new Person();
        update.setFirstName("firstName");
        update.setLastName("lastName");

        repository.update(0, update);
        assertThat(repository.getAll().get(0)).isEqualTo(update);
    }

    @Test
    void delete() {
        repository.save(persons);
        repository.delete(person1);
        assertThat(repository.getAll()).hasSize(1);
        assertThat(repository.getAll().get(0)).isEqualTo(person2);
    }
}
