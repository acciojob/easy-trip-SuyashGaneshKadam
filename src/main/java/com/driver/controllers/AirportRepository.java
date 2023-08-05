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
    HashMap<Integer, List<Flight>> passengerFlightBookingDb = new HashMap<>();
    HashMap<Integer, List<Passenger>> flightPassengerBookingDb = new HashMap<>();
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
    public HashMap<Integer, Flight> getFlightDb()
    {
        return flightDb;
    }
    public void addPassenger(Passenger passenger){
        passengerDb.put(passenger.getPassengerId(), passenger);
    }
    public HashMap<Integer, Passenger> getPassengerDb()
    {
        return passengerDb;
    }
    public HashMap<Integer, List<Flight>> getPassengerFlightBookingDb(){ return passengerFlightBookingDb; }
    public HashMap<Integer, List<Passenger>> getFlightPassengerBookingDb(){ return flightPassengerBookingDb; }

    public void bookATicket(Integer flightId, Integer passengerId){
        List<Flight> flights = passengerFlightBookingDb.getOrDefault(passengerId, new ArrayList<>());
        flights.add(flightDb.get(flightId));
        passengerFlightBookingDb.put(passengerId, flights);
        List<Passenger> passengers = flightPassengerBookingDb.getOrDefault(flightId, new ArrayList<>());
        passengers.add(passengerDb.get(passengerId));
        flightPassengerBookingDb.put(flightId, passengers);
    }
}
