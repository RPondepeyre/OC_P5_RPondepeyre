package com.example.safetynet.service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Medicalrecord> findAllMedicalRecords() {
        return repository.getAll();
    }

    public Medicalrecord findByPerson(Person person) {
        List<Medicalrecord> records = repository.getAll().stream()
                .filter(medicalrecord -> medicalrecord.getFirstName().equals(person.getFirstName())
                        && medicalrecord.getLastName().equals(person.getLastName()))
                .collect(Collectors.toList());
        if (records.size() == 1) {
            return records.get(0);
        } else if (records.isEmpty()) {
            logger.error("Aucun dossier médical trouvé pour cette personne");
            return null;
        } else {
            logger.error("Plusieurs dossiers medicaux trouvés pour cette personne");
            return null;
        }
    }

}
