package com.example.safetynet.UnitTests.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.example.safetynet.config.InitDataConfig;
import com.example.safetynet.repository.FirestationsRepository;
import com.example.safetynet.repository.MedicalrecordRepository;
import com.example.safetynet.repository.PersonsRepository;
import com.example.safetynet.service.GetDataService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import nl.altindag.log.LogCaptor;

@ExtendWith(MockitoExtension.class)
public class InitDataConfigTest {

    @Mock
    GetDataService getData;
    @Mock
    FirestationsRepository firestations;
    @Mock
    PersonsRepository persons;
    @Mock
    MedicalrecordRepository medicalrecords;
    @InjectMocks
    InitDataConfig config = new InitDataConfig();

    LogCaptor logCaptor = LogCaptor.forClass(InitDataConfig.class);

    @Test
    void initDatatrue() {

        doNothing().when(firestations).save(anyList());
        doNothing().when(persons).save(anyList());
        doNothing().when(medicalrecords).save(anyList());
        config.initData();

        verify(firestations).save(anyList());
        verify(persons).save(anyList());
        verify(medicalrecords).save(anyList());

        assertThat(logCaptor.getInfoLogs()).containsExactly(
                "Firestations loaded",
                "Persons loaded",
                "Medical records loaded");
    }

    @Test
    void initDataFalse() {

        doThrow(NullPointerException.class).when(firestations).save(anyList());
        doThrow(NullPointerException.class).when(persons).save(anyList());
        doThrow(NullPointerException.class).when(medicalrecords).save(anyList());
        config.initData();
        assertThat(logCaptor.getErrorLogs()).containsExactly(
                "Can't load Firestions, error occured",
                "Can't load Persons, error occured",
                "Can't load Medical records, error occured");
    }

}
