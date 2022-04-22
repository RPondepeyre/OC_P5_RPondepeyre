package com.example.safetynet.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Firestation;

import org.springframework.stereotype.Repository;

@Repository
public class FirestationsRepository {

    private List<Firestation> firestations = new ArrayList<>();

    public void save(List<Firestation> firestations) {
        this.firestations = firestations;
    }

    public void add(Firestation firestation) {
        this.firestations.add(firestation);
    }

    public List<Firestation> getAll() {
        return this.firestations;
    }

}
