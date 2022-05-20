package com.example.safetynet.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.PersonsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private static final Logger logger = LoggerFactory.getLogger(MedicalrecordService.class);

    @Autowired
    PersonsRepository repository;

    public List<Person> findAllPersons() {
        return repository.getAll();
    }

    public List<Person> findByAddress(String address) {
        return repository.getAll().stream().filter(person -> person.getAddress().equals(address))
                .collect(Collectors.toList());
    }

    public Person findByName(String firstName, String lastName)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<Person> persons = repository.getAll().stream()
                .filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
                .collect(Collectors.toList());
        if (persons.size() == 1) {
            return persons.get(0);
        } else if (persons.isEmpty()) {
            String error = String.format("Aucune personne trouvée pour le nom: %s %s ", firstName, lastName);
            logger.error(error);
            throw new RessourceNotFoundException(error);

        } else {
            String error = String.format("Plusieurs personnes trouvées pour le nom: %s %s ", firstName, lastName);
            logger.error(error);
            throw new TooManyRessourcesFoundException(error);
        }
    }

    public List<Person> findByCity(String city) {
        return repository.getAll().stream().filter(person -> person.getCity().equals(city))
                .collect(Collectors.toList());
    }

    public Person addPerson(Person person) {
        repository.add(person);
        return person;

    }

    public Person updatePerson(Person newperson) throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person oldperson = findByName(newperson.getFirstName(), newperson.getLastName());
        Person person = oldperson;
        person.setAddress(newperson.getAddress() != null ? newperson.getAddress() : person.getAddress());
        person.setCity(newperson.getCity() != null ? newperson.getCity() : person.getCity());
        person.setZip(newperson.getZip() != null ? newperson.getZip() : person.getZip());
        person.setPhone(newperson.getPhone() != null ? newperson.getPhone() : person.getPhone());
        person.setEmail(newperson.getEmail() != null ? newperson.getEmail() : person.getEmail());
        repository.update(findAllPersons().indexOf(oldperson), person);
        return person;
    }

    public Person deletePerson(Person person) throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Person deleteperson = findByName(person.getFirstName(), person.getLastName());
        repository.delete(deleteperson);
        return deleteperson;
    }
}
