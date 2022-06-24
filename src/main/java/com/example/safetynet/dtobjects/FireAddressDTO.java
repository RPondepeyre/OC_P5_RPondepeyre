package com.example.safetynet.dtobjects;

import java.util.List;

public class FireAddressDTO {

    List<PersonMedicalInfoDTO> persons;
    int station;

    public List<PersonMedicalInfoDTO> getPersons() {
        return this.persons;
    }

    public void setPersons(List<PersonMedicalInfoDTO> persons) {
        this.persons = persons;
    }

    public int getStation() {
        return this.station;
    }

    public void setStation(int station) {
        this.station = station;
    }

}
