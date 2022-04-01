package com.example.safetynet.model;

import java.time.LocalDate;
import java.util.List;

public class Medicalrecord {
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private List<String> medications;
    private List<String> allergies;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<String> getMedications() {
        return this.medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return this.allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
    
}
// "firstName": "John", "lastName": "Boyd", "birthdate": "03/06/1984", "medications": ["aznol:350mg", "hydrapermazol:100mg"], "allergies": ["nillacilan"] 