package com.example.safetynet.DTO;

import java.util.List;

public class PersonInfoDTO {

    String firstname;
    String lastname;
    String address;
    int age;
    String email;

    List<String> medications;
    List<String> allergies;

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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
