package com.example.safetynet.UnitTests.Service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Medicalrecord;
import com.example.safetynet.repository.MedicalrecordRepository;
import com.example.safetynet.service.MedicalrecordService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MedicalrecordServiceTest {

    @Mock
    MedicalrecordRepository repository;

    @InjectMocks
    MedicalrecordService service;

    @Test
    void findAllRecordsTest() {
        List<Medicalrecord> records = new ArrayList<>();

        when(repository.getAll()).thenReturn(records);
        service.findAllMedicalRecords();

        verify(repository).getAll();
    }

}
