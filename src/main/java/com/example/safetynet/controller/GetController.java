package com.example.safetynet.controller;

import java.util.List;

import com.example.safetynet.model.Person;
import com.example.safetynet.service.SortDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {
    
    @Autowired
    SortDataService service;



    //localhost:8080/firestation?stationNumber=<station_number>
    @GetMapping("/firestation")
    public ResponseEntity<List<Person>> fireStation(@RequestParam int stationNumber){
        return new ResponseEntity<>(service.findPersonsbystation(stationNumber), HttpStatus.OK);
    }

    //localhost:8080/childAlert?address=<address>
    @GetMapping("/childAlert")
    public ResponseEntity<String> childAlert(@RequestParam String address){
        //Insert Method
        return new ResponseEntity<>("ChildAlert" + address, HttpStatus.OK);
    }

    //localhost:8080/phoneAlert?firestation=<firestation_number>
    @GetMapping("/phoneAlert")
    public ResponseEntity<String> phoneAlert(@RequestParam int firestation){
        //Insert Method
        return new ResponseEntity<>("phoneAlert"+ firestation, HttpStatus.OK);
    }

    //localhost:8080/fire?address=<address>
    @GetMapping("/fire")
    public ResponseEntity<String> fire(@RequestParam String address){
        //Insert Method
        return new ResponseEntity<>("fire" + address, HttpStatus.OK);
    }
}
