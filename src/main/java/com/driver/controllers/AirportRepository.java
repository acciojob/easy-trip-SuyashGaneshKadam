package com.driver.controllers;

import com.driver.model.Airport;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class AirportRepository {
    HashMap<String, Airport> airportDb = new HashMap<>();
    public void addAirport(Airport airport){
        airportDb.put(airport.getAirportName(), airport);
    }

    public HashMap<String, Airport> getAirportDb()
    {
        return airportDb;
    }
}
