package com.example.safetynet.UnitTests.Controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.safetynet.DTO.ChildAlertDTO;
import com.example.safetynet.DTO.FireAddressDTO;
import com.example.safetynet.DTO.PersonInfoDTO;
import com.example.safetynet.DTO.PersonsbyFirestationsDTO;
import com.example.safetynet.DTO.StationHousesDTO;
import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.controller.GetController;
import com.example.safetynet.service.ChildAlertService;
import com.example.safetynet.service.CommunityEmailService;
import com.example.safetynet.service.FireAddressService;
import com.example.safetynet.service.FloodStationsService;
import com.example.safetynet.service.PersonInfoService;
import com.example.safetynet.service.PersonsbyFirestationService;
import com.example.safetynet.service.PhoneAlertService;

@ExtendWith(MockitoExtension.class)
public class GetControllerTests {
    @Mock
    PersonsbyFirestationService personsbyFirestationService;
    @Mock
    ChildAlertService childAlertService;
    @Mock
    PhoneAlertService phoneAlertService;
    @Mock
    FireAddressService fireAddressService;
    @Mock
    FloodStationsService floodStationsService;
    @Mock
    PersonInfoService personInfoService;
    @Mock
    CommunityEmailService communityEmailService;

    @InjectMocks
    GetController controller;

    @Test
    void fireStationTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        PersonsbyFirestationsDTO personsbyFirestationsDTO = new PersonsbyFirestationsDTO();
        doReturn(personsbyFirestationsDTO).when(personsbyFirestationService).personsbyFirestationDTO(1);
        ResponseEntity<PersonsbyFirestationsDTO> result = controller.fireStation(1);

        verify(personsbyFirestationService).personsbyFirestationDTO(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(personsbyFirestationsDTO);
    }

    @Test
    void fireStationTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        PersonsbyFirestationsDTO personsbyFirestationsDTO = null;
        doReturn(personsbyFirestationsDTO).when(personsbyFirestationService).personsbyFirestationDTO(1);
        ResponseEntity<PersonsbyFirestationsDTO> result = controller.fireStation(1);

        verify(personsbyFirestationService).personsbyFirestationDTO(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isNull();
        ;
    }

    @Test
    void childAlertTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<ChildAlertDTO> childAlertDTO = new ArrayList<>();
        doReturn(childAlertDTO).when(childAlertService).childAlertDTO("address");
        ResponseEntity<List<ChildAlertDTO>> result = controller.childAlert("address");

        verify(childAlertService).childAlertDTO("address");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(childAlertDTO);
    }

    @Test
    void childAlertTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        ChildAlertDTO childAlertDTO = null;
        doReturn(childAlertDTO).when(childAlertService).childAlertDTO("address");
        ResponseEntity<List<ChildAlertDTO>> result = controller.childAlert("address");

        verify(childAlertService).childAlertDTO("address");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isNull();
        ;
    }

    @Test
    void phoneAlertTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Set<String> set = new HashSet<>();
        doReturn(set).when(phoneAlertService).createPhoneList(1);
        ResponseEntity<Set<String>> result = controller.phoneAlert(1);

        verify(phoneAlertService).createPhoneList(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(set);
    }

    @Test
    void phoneAlertTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Set<String> set = null;
        doReturn(set).when(phoneAlertService).createPhoneList(1);
        ResponseEntity<Set<String>> result = controller.phoneAlert(1);

        verify(phoneAlertService).createPhoneList(1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isNull();
        ;
    }

    @Test
    void fireTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        FireAddressDTO fireAddressDTO = new FireAddressDTO();
        doReturn(fireAddressDTO).when(fireAddressService).createFireAddressDTO("address");
        ResponseEntity<FireAddressDTO> result = controller.fire("address");

        verify(fireAddressService).createFireAddressDTO("address");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(fireAddressDTO);
    }

    @Test
    void fireTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        FireAddressDTO fireAddressDTO = null;
        doReturn(fireAddressDTO).when(fireAddressService).createFireAddressDTO("address");
        ResponseEntity<FireAddressDTO> result = controller.fire("address");

        verify(fireAddressService).createFireAddressDTO("address");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isNull();
        ;
    }

    @Test
    void floodStationsTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<StationHousesDTO> stationHousestDTO = new ArrayList<>();
        List<Integer> param = new ArrayList<>();
        doReturn(stationHousestDTO).when(floodStationsService).floodStationsHouses(param);
        ResponseEntity<List<StationHousesDTO>> result = controller.floodStations(param);

        verify(floodStationsService).floodStationsHouses(param);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(stationHousestDTO);
    }

    @Test
    void floodStationsTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<StationHousesDTO> stationHousestDTO = null;
        List<Integer> param = new ArrayList<>();
        doReturn(stationHousestDTO).when(floodStationsService).floodStationsHouses(param);
        ResponseEntity<List<StationHousesDTO>> result = controller.floodStations(param);

        verify(floodStationsService).floodStationsHouses(param);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isNull();
        ;
    }

    @Test
    void personInfoTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<PersonInfoDTO> personInfoDTOs = new ArrayList<>();
        doReturn(personInfoDTOs).when(personInfoService).personInfo("firstName", "lastName");
        ResponseEntity<List<PersonInfoDTO>> result = controller.personInfo("firstName", "lastName");

        verify(personInfoService).personInfo("firstName", "lastName");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(personInfoDTOs);
    }

    @Test
    void personInfoTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<PersonInfoDTO> personInfoDTOs = null;
        doReturn(personInfoDTOs).when(personInfoService).personInfo("firstName", "lastName");
        ResponseEntity<List<PersonInfoDTO>> result = controller.personInfo("firstName", "lastName");

        verify(personInfoService).personInfo("firstName", "lastName");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isNull();
        ;
    }

    @Test
    void communityEmailTest() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Set<String> set = new HashSet<>();
        doReturn(set).when(communityEmailService).communityEmail("city");
        ResponseEntity<Set<String>> result = controller.communityEmail("city");

        verify(communityEmailService).communityEmail("city");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(set);
    }

    @Test
    void communityEmailTestNull() throws RessourceNotFoundException, TooManyRessourcesFoundException {
        Set<String> set = null;
        doReturn(set).when(communityEmailService).communityEmail("city");
        ResponseEntity<Set<String>> result = controller.communityEmail("city");

        verify(communityEmailService).communityEmail("city");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(result.getBody()).isNull();
        ;
    }
}
