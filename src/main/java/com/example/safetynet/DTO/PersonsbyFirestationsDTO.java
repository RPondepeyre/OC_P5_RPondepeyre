package com.example.safetynet.DTO;

import java.util.List;

public class PersonsbyFirestationsDTO {

    List<PersonsDTO> persons;
    int nbAdults;
    int nbChildren;

    public PersonsbyFirestationsDTO(List<PersonsDTO> persons, int nbAdults, int nbChildren) {
        this.persons = persons;
        this.nbAdults = nbAdults;
        this.nbChildren = nbChildren;
    }

    public List<PersonsDTO> getPersons() {
        return this.persons;
    }

    public void setPersons(List<PersonsDTO> persons) {
        this.persons = persons;
    }

    public int getNbAdults() {
        return this.nbAdults;
    }

    public void setNbAdults(int nbAdults) {
        this.nbAdults = nbAdults;
    }

    public int getNbChildren() {
        return this.nbChildren;
    }

    public void setNbChildren(int nbChildren) {
        this.nbChildren = nbChildren;
    }
}
