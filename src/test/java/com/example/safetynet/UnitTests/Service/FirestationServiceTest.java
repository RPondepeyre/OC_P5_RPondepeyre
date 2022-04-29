package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.model.Firestation;
import com.example.safetynet.repository.FirestationsRepository;
import com.example.safetynet.service.FirestationService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

    @Mock
    FirestationsRepository repository;

    @InjectMocks
    FirestationService service;

    @Test
    void findAllRecordsTest() {
        List<Firestation> firestations = new ArrayList<>();

        when(repository.getAll()).thenReturn(firestations);
        service.findAllFirestations();

        verify(repository).getAll();
    }

    @Test
    void findByStationTest() {

        List<Firestation> rawlist = new ArrayList<>();

        Firestation station = new Firestation();
        station.setAdress("true");
        station.setStation(1);

        Firestation station2 = new Firestation();
        station2.setAdress("false");
        station2.setStation(2);

        rawlist.add(station);
        rawlist.add(station2);

        when(repository.getAll()).thenReturn(rawlist);
        List<Firestation> result = service.findByStation(1);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAdress()).isEqualTo("true");
    }

    @Test
    void findByAdressTest() {

        List<Firestation> rawlist = new ArrayList<>();

        Firestation station = new Firestation();
        station.setAdress("true");
        station.setStation(1);

        Firestation station2 = new Firestation();
        station2.setAdress("false");
        station2.setStation(2);

        rawlist.add(station);
        rawlist.add(station2);

        when(repository.getAll()).thenReturn(rawlist);
        List<Firestation> result = service.findByAdress("true");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getStation()).isEqualTo(1);
    }

}
