package com.driver.controllers;

import com.driver.model.Airport;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
@Service
public class AirportService {

    AirportRepository airportRepositoryObject = new AirportRepository();

    public void addAirport(Airport airport){
        airportRepositoryObject.addAirport(airport);
    }

    public String getLargestAirportName() {
        HashMap<String, Airport> airportDb = airportRepositoryObject.getAirportDb();
        if(airportDb.size() == 0) return "";
        int maxNoOfTerminals = 0;
        String largestAirportName = "";
        for(Airport airport : airportDb.values()) {
            int currentAirportTerminals = airport.getNoOfTerminals();
            String currentAirportName = airport.getAirportName();
            if(currentAirportTerminals < maxNoOfTerminals) continue;
            else if(currentAirportTerminals > maxNoOfTerminals) {
                largestAirportName = currentAirportName;
                maxNoOfTerminals = currentAirportTerminals;
            }
            else{
                if(largestAirportName.length() == 0 || currentAirportName.compareTo(largestAirportName) < 0)
                {
                    largestAirportName = currentAirportName;
                }
            }
        }
        return largestAirportName;
    }
}
