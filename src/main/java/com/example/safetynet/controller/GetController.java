package com.example.safetynet.controller;

import java.util.List;
import java.util.Set;

import com.example.safetynet.DTO.FireAdressDTO;
import com.example.safetynet.DTO.PersonInfoDTO;
import com.example.safetynet.DTO.PersonsbyFirestationsDTO;
import com.example.safetynet.DTO.StationHousesDTO;
import com.example.safetynet.DTO.childAlertDTO;
import com.example.safetynet.service.CommunityEmailService;
import com.example.safetynet.service.FireAdressService;
import com.example.safetynet.service.FloodStationsService;
import com.example.safetynet.service.PersonInfoService;
import com.example.safetynet.service.PersonsbyFirestationService;
import com.example.safetynet.service.PhoneAlertService;
import com.example.safetynet.service.childAlertService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
@ControllerAdvice
public class ExceptionsHandle

@ExceptionHandler(FirestationAlreadyExistsException.class)
    public ResponseEntity<Object> handleFirestationAlreadyExistsExceptions(FirestationAlreadyExistsException e){
        logger.error("Firestation Already exists");
        CustomErrorResponse res = new CustomErrorResponse(e.getMessage(),e, HttpStatus.CONFLICT, ZonedDateTime.now());
        return new ResponseEntity<>(res,HttpStatus.CONFLICT);
    }

*/

@RestController
public class GetController {

    @Autowired
    PersonsbyFirestationService personsbyFirestationService;

    @Autowired
    childAlertService childAlertService;

    @Autowired
    PhoneAlertService phoneAlertService;

    @Autowired
    FireAdressService fireAdressService;

    @Autowired
    FloodStationsService floodStationsService;

    @Autowired
    PersonInfoService personInfoService;

    @Autowired
    CommunityEmailService communityEmailService;

    // localhost:8080/firestation?stationNumber=<station_number>
    @GetMapping("/firestation")
    public ResponseEntity<PersonsbyFirestationsDTO> fireStation(@RequestParam int stationNumber) {
        PersonsbyFirestationsDTO result = personsbyFirestationService.personsbyFirestationDTO(stationNumber);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // localhost:8080/childAlert?address=<address>
    @GetMapping("/childAlert")
    public ResponseEntity<List<childAlertDTO>> childAlert(@RequestParam String address) {
        List<childAlertDTO> result = childAlertService.childAlertDTO(address);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // localhost:8080/phoneAlert?firestation=<firestation_number>

    @GetMapping("/phoneAlert")
    public ResponseEntity<Set<String>> phoneAlert(@RequestParam int firestation) {
        Set<String> result = phoneAlertService.createPhoneList(firestation);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // localhost:8080/fire?address=<address>
    @GetMapping("/fire")
    public ResponseEntity<FireAdressDTO> fire(@RequestParam String address) {
        FireAdressDTO result = fireAdressService.createFireAdressDTO(address);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // http://localhost:8080/flood/stations?stations=<a list of station_numbers>
    @GetMapping("/flood/stations")
    public ResponseEntity<List<StationHousesDTO>> floodStations(@RequestParam List<Integer> stations) {
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
            @RequestParam String lastName) {
        List<PersonInfoDTO> result = personInfoService.personInfo(firstName, lastName);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    // http://localhost:8080/communityEmail?city=<city>
    @GetMapping("/communityEmail")
    public ResponseEntity<Set<String>> personInfo(@RequestParam String city) {
        Set<String> result = communityEmailService.communityEmail(city);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }
}
