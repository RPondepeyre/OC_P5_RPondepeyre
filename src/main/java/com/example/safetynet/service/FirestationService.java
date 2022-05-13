package com.example.safetynet.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.safetynet.model.Firestation;
import com.example.safetynet.repository.FirestationsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirestationService {

    private static final Logger logger = LoggerFactory.getLogger(FirestationService.class);

    @Autowired
    FirestationsRepository repository;

    public List<Firestation> findAllFirestations() {
        return repository.getAll();
    }

    public List<Firestation> findByStation(Integer station) {
        return repository.getAll().stream().filter(firestation -> firestation.getStation().equals(station))
                .collect(Collectors.toList());
    }

    public Firestation findByAddress(String address) {
        List<Firestation> stations = repository.getAll().stream()
                .filter(firestation -> firestation.getAddress().equals(address))
                .collect(Collectors.toList());
        if (stations.size() == 1) {
            return stations.get(0);
        } else if (stations.isEmpty()) {
            logger.error("Aucune station trouvée pour cette addresse");
            return null;
        } else {
            logger.error("Plusieurs stations trouvées pour cette addresse");
            return null;
        }

    }

    public Firestation addFirestation(Firestation firestation) {
        repository.add(firestation);
        return firestation;
    }

}
