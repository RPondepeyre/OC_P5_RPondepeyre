package com.example.safetynet.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.model.Person;
import com.example.safetynet.repository.MedicalrecordRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalrecordService {

    private static final Logger logger = LoggerFactory.getLogger(MedicalrecordService.class);

    @Autowired
    MedicalrecordRepository repository;
    @Autowired
    PersonService personService;

    public List<Medicalrecord> findAllMedicalRecords() {
        return repository.getAll();
    }

    public Medicalrecord findByPerson(Person person)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<Medicalrecord> records = repository.getAll().stream()
                .filter(medicalrecord -> medicalrecord.getFirstName().equals(person.getFirstName())
                        && medicalrecord.getLastName().equals(person.getLastName()))
                .collect(Collectors.toList());
        if (records.size() == 1) {
            return records.get(0);
        } else if (records.isEmpty()) {
            String error = String.format("Aucun dossier médical trouvé pour le nom: %s %s", person.getFirstName(),
                    person.getLastName());
            logger.error(error);
            throw new RessourceNotFoundException(error);
        } else {
            String error = String.format("Plusieurs dossiers médicaux trouvés pour le nom: %s %s",
                    person.getFirstName(),
                    person.getLastName());
            logger.error(error);
            throw new TooManyRessourcesFoundException(error);
        }
    }

    public Medicalrecord addMedicalRecord(Medicalrecord medicalrecord) {
        repository.add(medicalrecord);
        return medicalrecord;
    }

    public Medicalrecord updateMedicalrecord(Medicalrecord newrecord)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord oldrecord = findByPerson(
                personService.findByName(newrecord.getFirstName(), newrecord.getLastName()));
        Medicalrecord medrecord = oldrecord;
        medrecord.setBirthdate(newrecord.getBirthdate() != null ? newrecord.getBirthdate() : medrecord.getBirthdate());
        medrecord.setMedications(
                newrecord.getMedications() != null ? newrecord.getMedications() : medrecord.getMedications());
        medrecord.setAllergies(newrecord.getAllergies() != null ? newrecord.getAllergies() : medrecord.getAllergies());
        repository.update(findAllMedicalRecords().indexOf(oldrecord), medrecord);
        return medrecord;
    }

    public Medicalrecord deleteMedicalrecord(Medicalrecord medicalrecord)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Medicalrecord deletedrecord = findByPerson(
                personService.findByName(medicalrecord.getFirstName(), medicalrecord.getLastName()));
        repository.delete(deletedrecord);
        return deletedrecord;
    }
}
