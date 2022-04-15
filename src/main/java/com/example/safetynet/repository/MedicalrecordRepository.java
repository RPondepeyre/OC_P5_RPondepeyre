package com.example.safetynet.repository;
import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Medicalrecord;

import org.springframework.stereotype.Repository;

@Repository
public class MedicalrecordRepository {


    private List<Medicalrecord> medicalrecords = new ArrayList<>();

    public void save(List<Medicalrecord> medicalrecords){
        this.medicalrecords = medicalrecords;
    }
    
    public void add(Medicalrecord medicalrecord){
        this.medicalrecords.add(medicalrecord);
    }

    public List<Medicalrecord> getAll(){
        return this.medicalrecords;
    }



    
}
