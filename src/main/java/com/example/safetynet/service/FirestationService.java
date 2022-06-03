package com.example.safetynet.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
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

    public List<Firestation> findByStation(Integer station) throws RessourceNotFoundException {
        List<Firestation> stations = repository.getAll().stream()
                .filter(firestation -> firestation.getStation().equals(station))
                .collect(Collectors.toList());
        if (stations.isEmpty()) {
            String error = String.format("La station n° %s n'existe pas", station);
            logger.error(error);
            throw new RessourceNotFoundException(error);
        } else {
            return stations;
        }
    }

    public Firestation findByAddress(String address)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<Firestation> stations = repository.getAll().stream()
                .filter(firestation -> firestation.getAddress().equals(address))
                .collect(Collectors.toList());
        if (stations.size() == 1) {
            return stations.get(0);
        } else if (stations.isEmpty()) {
            String error = String.format("Aucune station trouvée pour l'adresse: %s", address);
            logger.error(error);
            throw new RessourceNotFoundException(error);
        } else {
            String error = String.format("Plusieurs stations trouvées pour l'adresse: %s", address);
            logger.error(error);
            throw new TooManyRessourcesFoundException(error);
        }

    }

    public Firestation addFirestation(Firestation firestation) {
        repository.add(firestation);
        return firestation;
    }

    public Firestation updateFirestation(Firestation newfirestation)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Firestation oldfirestation = findByAddress(newfirestation.getAddress());
        Firestation firestation = oldfirestation;
        firestation.setStation(newfirestation.getStation());
        repository.update(findAllFirestations().indexOf(oldfirestation), firestation);
        return firestation;

    }

    public Firestation deleteFirestation(Firestation firestation)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        if (firestation.getAddress() != null) {
            Firestation deletefirestation = findByAddress(firestation.getAddress());
            repository.delete(deletefirestation);
        }
        if (firestation.getStation() != null && firestation.getAddress() == null) {
            List<Firestation> firestations = findByStation(firestation.getStation());
            for (Firestation deletefirestation : firestations) {
                repository.delete(deletefirestation);
            }
        }
        return firestation;
    }
}
