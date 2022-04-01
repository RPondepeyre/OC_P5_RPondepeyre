package com.ocprojet5.safetynet.repository;
import java.util.ArrayList;
import java.util.List;

import com.ocprojet5.safetynet.model.Firestation;
import org.springframework.stereotype.Repository;

@Repository
public class FirestationsRepository {

public List<Firestation> firestations = new ArrayList<>();

public void save(List<Firestation> firestations){
    this.firestations = firestations;
}



    
    
}
