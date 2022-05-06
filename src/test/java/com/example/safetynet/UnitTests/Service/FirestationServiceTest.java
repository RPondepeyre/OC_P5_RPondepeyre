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

import nl.altindag.log.LogCaptor;

@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

    @Mock
    FirestationsRepository repository;

    @InjectMocks
    FirestationService service;

    LogCaptor logCaptor = LogCaptor.forClass(FirestationService.class);

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
    void findByAdressTestTrue() {

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
        Firestation result = service.findByAdress("true");

        assertThat(result.getStation()).isEqualTo(1);
    }

    @Test
    void findByAdressTestEmpty() {

        List<Firestation> rawlist = new ArrayList<>();

        when(repository.getAll()).thenReturn(rawlist);
        Firestation result = service.findByAdress("true");
        assertThat(result).isNull();
        assertThat(logCaptor.getErrorLogs()).containsExactly("Aucune station trouvée pour cette addresse");
    }

    @Test
    void findByAdressTesttoManyStations() {

        List<Firestation> rawlist = new ArrayList<>();

        Firestation station = new Firestation();
        station.setAdress("true");
        station.setStation(1);

        Firestation station2 = new Firestation();
        station2.setAdress("true");
        station2.setStation(2);

        rawlist.add(station);
        rawlist.add(station2);

        when(repository.getAll()).thenReturn(rawlist);
        service.findByAdress("true");
        assertThat(logCaptor.getErrorLogs()).containsExactly("Plusieurs stations trouvées pour cette addresse");

    }

}
