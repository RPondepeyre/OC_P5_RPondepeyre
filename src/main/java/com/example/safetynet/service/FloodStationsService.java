package com.example.safetynet.service;

import java.util.ArrayList;
import java.util.List;

import com.example.safetynet.DTO.HouseDTO;
import com.example.safetynet.DTO.PersonMedicalInfoDTO;
import com.example.safetynet.DTO.StationHousesDTO;
import com.example.safetynet.config.exceptions.RessourceNotFoundException;
import com.example.safetynet.config.exceptions.TooManyRessourcesFoundException;
import com.example.safetynet.model.Firestation;
import com.example.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloodStationsService {

    @Autowired
    SortDataService sortDataService;

    @Autowired
    FirestationService firestationService;

    @Autowired
    PersonService personService;

    public List<StationHousesDTO> floodStationsHouses(List<Integer> stationsID)
            throws RessourceNotFoundException, TooManyRessourcesFoundException {
        List<StationHousesDTO> result = new ArrayList<>();
        for (Integer station : stationsID) {
            StationHousesDTO housesDTO = new StationHousesDTO();
            List<HouseDTO> houses = new ArrayList<>();
            List<Firestation> stations = firestationService.findByStation(station);
            for (Firestation firestation : stations) {
                HouseDTO house = new HouseDTO();
                house.setAddress(firestation.getAddress());
                List<PersonMedicalInfoDTO> persons = new ArrayList<>();
                List<Person> rawlist = personService.findByAddress(firestation.getAddress());
                for (Person person : rawlist) {
                    PersonMedicalInfoDTO personInfo = sortDataService.createPersonInfo(person);
                    persons.add(personInfo);
                }
                house.setPersons(persons);
                houses.add(house);
            }
            housesDTO.setStationID(station);
            housesDTO.setHouses(houses);
            result.add(housesDTO);
        }
        return result;
    }

}
