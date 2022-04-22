package com.example.safetynet.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.safetynet.model.Firestation;
import com.example.safetynet.repository.FirestationsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirestationService {

    @Autowired
    FirestationsRepository repository;

    public List<Firestation> findAllFirestations() {
        return repository.getAll();
    }

    public List<Firestation> findByStation(Integer station) {
        return repository.getAll().stream().filter(firestation -> firestation.getStation().equals(station))
                .collect(Collectors.toList());
    }

    public List<Firestation> findByAdress(String adress) {
        return repository.getAll().stream().filter(firestation -> firestation.getAdress().equals(adress))
                .collect(Collectors.toList());
    }

}
