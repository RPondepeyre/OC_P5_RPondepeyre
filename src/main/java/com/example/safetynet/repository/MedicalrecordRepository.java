package com.example.safetynet.repository;
import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Medicalrecord;

import org.springframework.stereotype.Repository;

@Repository
public class MedicalrecordRepository {


    public List<Medicalrecord> medicalrecords = new ArrayList<>();

    public void save(List<Medicalrecord> medicalrecords){
        this.medicalrecords = medicalrecords;
    }
    

    
}
