package com.example.safetynet.UnitTests.Service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
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
    void findByStationTest() throws RessourceNotFoundException {

        List<Firestation> rawlist = new ArrayList<>();

        Firestation station = new Firestation();
        station.setAddress("true");
        station.setStation(1);

        Firestation station2 = new Firestation();
        station2.setAddress("false");
        station2.setStation(2);

        rawlist.add(station);
        rawlist.add(station2);

        when(repository.getAll()).thenReturn(rawlist);
        List<Firestation> result = service.findByStation(1);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getAddress()).isEqualTo("true");
    }

    @Test
    void findByStationTestFalse() {
        List<Firestation> rawlist = new ArrayList<>();
        when(repository.getAll()).thenReturn(rawlist);
        RessourceNotFoundException thrown = assertThrows(RessourceNotFoundException.class,
                () -> {
                    service.findByStation(1);
                });
        assertThat(thrown.getMessage()).isEqualTo("La station n° 1 n'existe pas");
        assertThat(logCaptor.getErrorLogs()).containsExactly("La station n° 1 n'existe pas");
    }

    @Test
    void findByAddressTestTrue() throws RessourceNotFoundException, TooManyRessourcesFoundException {

        List<Firestation> rawlist = new ArrayList<>();

        Firestation station = new Firestation();
        station.setAddress("true");
        station.setStation(1);

        Firestation station2 = new Firestation();
        station2.setAddress("false");
        station2.setStation(2);

        rawlist.add(station);
        rawlist.add(station2);

        when(repository.getAll()).thenReturn(rawlist);
        Firestation result = service.findByAddress("true");

        assertThat(result.getStation()).isEqualTo(1);
    }

    @Test
    void findByAddressTestEmpty() throws RessourceNotFoundException, TooManyRessourcesFoundException {

        List<Firestation> rawlist = new ArrayList<>();

        when(repository.getAll()).thenReturn(rawlist);
        RessourceNotFoundException thrown = assertThrows(RessourceNotFoundException.class,
                () -> {
                    service.findByAddress("true");
                });
        assertThat(thrown.getMessage()).isEqualTo("Aucune station trouvée pour l'adresse: true");
        assertThat(logCaptor.getErrorLogs()).containsExactly("Aucune station trouvée pour l'adresse: true");
    }

    @Test
    void findByAddressTesttoManyStations() throws RessourceNotFoundException, TooManyRessourcesFoundException {

        List<Firestation> rawlist = new ArrayList<>();

        Firestation station = new Firestation();
        station.setAddress("true");
        station.setStation(1);

        Firestation station2 = new Firestation();
        station2.setAddress("true");
        station2.setStation(2);

        rawlist.add(station);
        rawlist.add(station2);

        when(repository.getAll()).thenReturn(rawlist);

        TooManyRessourcesFoundException thrown = assertThrows(TooManyRessourcesFoundException.class,
                () -> {
                    service.findByAddress("true");
                });
        assertThat(thrown.getMessage()).isEqualTo("Plusieurs stations trouvées pour l'adresse: true");
        assertThat(logCaptor.getErrorLogs()).containsExactly("Plusieurs stations trouvées pour l'adresse: true");

    }

    @Test
    void addFirestationTest() {
        Firestation firestation = new Firestation();
        service.addFirestation(firestation);
        verify(repository).add(firestation);
    }

    @Test
    void updateFirestationTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Firestation firestation1 = new Firestation();
        firestation1.setAddress("address");
        firestation1.setStation(1);

        Firestation newstation = new Firestation();
        newstation.setAddress("address");
        newstation.setStation(2);
        List<Firestation> stations = new ArrayList<>();
        stations.add(newstation);

        Firestation fakestation = new Firestation();
        fakestation.setAddress("fake");
        fakestation.setStation(3);

        List<Firestation> baselist = new ArrayList<>();
        baselist.add(firestation1);
        baselist.add(fakestation);

        doReturn(baselist).when(repository).getAll();

        Firestation result = service.updateFirestation(newstation);
        verify(repository).update(anyInt(), any(Firestation.class));
        assertThat(result.getStation()).isEqualTo(2);

    }
}
