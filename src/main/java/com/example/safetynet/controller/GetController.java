package com.example.safetynet.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.safetynet.DTO.ChildAlertDTO;
import com.example.safetynet.DTO.FireAddressDTO;
import com.example.safetynet.DTO.PersonInfoDTO;
import com.example.safetynet.DTO.PersonsbyFirestationsDTO;
import com.example.safetynet.DTO.StationHousesDTO;
import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.service.ChildAlertService;
import com.example.safetynet.service.CommunityEmailService;
import com.example.safetynet.service.FireAddressService;
import com.example.safetynet.service.FloodStationsService;
import com.example.safetynet.service.PersonInfoService;
import com.example.safetynet.service.PersonsbyFirestationService;
import com.example.safetynet.service.PhoneAlertService;

@RestController
public class GetController {

    @Autowired
    PersonsbyFirestationService personsbyFirestationService;

    @Autowired
    ChildAlertService childAlertService;

    @Autowired
    PhoneAlertService phoneAlertService;

    @Autowired
    FireAddressService fireAddressService;

    @Autowired
    FloodStationsService floodStationsService;

    @Autowired
    PersonInfoService personInfoService;

    @Autowired
    CommunityEmailService communityEmailService;

    // localhost:8080/firestation?stationNumber=<station_number>
    @GetMapping("/firestation")
    public ResponseEntity<PersonsbyFirestationsDTO> fireStation(@RequestParam int stationNumber)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        PersonsbyFirestationsDTO result = personsbyFirestationService.personsbyFirestationDTO(stationNumber);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // localhost:8080/childAlert?address=<address>
    @GetMapping("/childAlert")
    public ResponseEntity<List<ChildAlertDTO>> childAlert(@RequestParam String address)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<ChildAlertDTO> result = childAlertService.childAlertDTO(address);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // localhost:8080/phoneAlert?firestation=<firestation_number>

    @GetMapping("/phoneAlert")
    public ResponseEntity<Set<String>> phoneAlert(@RequestParam int firestation) throws RessourceNotFoundException {
        Set<String> result = phoneAlertService.createPhoneList(firestation);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // localhost:8080/fire?address=<address>
    @GetMapping("/fire")
    public ResponseEntity<FireAddressDTO> fire(@RequestParam String address)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        FireAddressDTO result = fireAddressService.createFireAddressDTO(address);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // http://localhost:8080/flood/stations?stations=<a list of station_numbers>
    @GetMapping("/flood/stations")
    public ResponseEntity<List<StationHousesDTO>> floodStations(@RequestParam List<Integer> stations)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<StationHousesDTO> result = floodStationsService.floodStationsHouses(stations);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    // http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>

    @GetMapping("/personInfo")
    public ResponseEntity<List<PersonInfoDTO>> personInfo(@RequestParam String firstName,
            @RequestParam String lastName) throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<PersonInfoDTO> result = personInfoService.personInfo(firstName, lastName);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    // http://localhost:8080/communityEmail?city=<city>
    @GetMapping("/communityEmail")
    public ResponseEntity<Set<String>> communityEmail(@RequestParam String city) {
        Set<String> result = communityEmailService.communityEmail(city);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
