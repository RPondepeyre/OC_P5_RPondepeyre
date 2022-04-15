package com.example.safetynet.service;

import java.util.List;

import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.repository.MedicalrecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalrecordService {
    
    @Autowired
    MedicalrecordRepository repository;

    public List<Medicalrecord> findAllMedicalRecords(){
        return repository.getAll();
    }

}
