package com.example.safetynet.dtobjects;

import java.util.List;

public class HouseDTO {
    String address;
    List<PersonMedicalInfoDTO> persons;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonMedicalInfoDTO> getPersons() {
        return this.persons;
    }

    public void setPersons(List<PersonMedicalInfoDTO> persons) {
        this.persons = persons;
    }

}
