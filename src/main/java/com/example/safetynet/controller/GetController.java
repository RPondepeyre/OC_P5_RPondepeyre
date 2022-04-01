package com.example.safetynet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {
    
    @GetMapping("/firestation")
    public ResponseEntity<String> fireStation(@RequestParam int stationNumber){
        //Insert Method
        return new ResponseEntity<String>("firestations" + stationNumber, HttpStatus.OK);
    }

    @GetMapping("/childAlert")
    public ResponseEntity<String> childAlert(@RequestParam String adress){
        //Insert Method
        return new ResponseEntity<String>("ChildAlert" + adress, HttpStatus.OK);
    }

    @GetMapping("/phoneAlert")
    public ResponseEntity<String> phoneAlert(@RequestParam int firestation){
        //Insert Method
        return new ResponseEntity<String>("phoneAlert"+ firestation, HttpStatus.OK);
    }

    @GetMapping("/fire")
    public ResponseEntity<String> fire(@RequestParam String adress){
        //Insert Method
        return new ResponseEntity<String>("fire" + adress, HttpStatus.OK);
    }
}
