package com.example.safetynet.DTO;

import java.util.List;

public class StationHousesDTO {
    int stationID;
    List<HouseDTO> houses;

    public int getStationID() {
        return this.stationID;
    }

    public void setStationID(int stationsID) {
        this.stationID = stationsID;
    }

    public List<HouseDTO> getHouses() {
        return this.houses;
    }

    public void setHouses(List<HouseDTO> houses) {
        this.houses = houses;
    }

}
