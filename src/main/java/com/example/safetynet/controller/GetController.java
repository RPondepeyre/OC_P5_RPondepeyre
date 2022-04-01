package com.ocprojet5.safetynet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {
    
    @GetMapping("/firestation")
    public ResponseEntity<String> fireStation(@RequestParam int stationNumber){
        
        return new ResponseEntity<>(null);
    }

    @GetMapping("/childAlert")
    public String childAlert(@RequestParam String adress){
        return "Exemple:"+ adress;
    }

    @GetMapping("/phoneAlert")
    public String phoneAlert(@RequestParam int firestation){
        return "Exemple:"+ firestation;
    }

    @GetMapping("/fire")
    public String fire(@RequestParam String adress){
        return "Exemple:"+ adress;
    }
}
