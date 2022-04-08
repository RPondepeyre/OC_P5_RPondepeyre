package com.example.safetynet.repository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.safetynet.model.Firestation;
import org.springframework.stereotype.Repository;

@Repository
public class FirestationsRepository{

public List<Firestation> firestations = new ArrayList<>();

public void save(List<Firestation> firestations){
    this.firestations = firestations;
}

public void addFirestation(Firestation firestation){
    this.firestations.add(firestation);
}

public List<Firestation> getAllFirestations(){
    return this.firestations;
}

public List<Firestation> findByStations(Integer station) {
    return this.firestations.stream().filter(firestation -> firestation.getStation().equals(station))
    .collect(Collectors.toList());
}

public List<Firestation> findByAdress(String adress) {
    return this.firestations.stream().filter(firestation -> firestation.getAdress().equals(adress))
    .collect(Collectors.toList());
}


    
    
}
