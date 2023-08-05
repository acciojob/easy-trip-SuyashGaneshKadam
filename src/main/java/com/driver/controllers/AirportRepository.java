package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class AirportRepository {
    HashMap<String, Airport> airportDb = new HashMap<>();
    HashMap<Integer, Flight> flightDb = new HashMap<>();
    HashMap<Integer, Passenger> passengerDb = new HashMap<>();
    public void addAirport(Airport airport){
        airportDb.put(airport.getAirportName(), airport);
    }
    public HashMap<String, Airport> getAirportDb()
    {
        return airportDb;
    }
    public void addFlight(Flight flight){
        flightDb.put(flight.getFlightId(), flight);
    }
    public void addPassenger(Passenger passenger){
        passengerDb.put(passenger.getPassengerId(), passenger);
    }
}
