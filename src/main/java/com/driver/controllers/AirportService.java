package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    public void addFlight(Flight flight){
        airportRepositoryObject.addFlight(flight);
    }
    public String getAirportNameFromFlightId(Integer flightId){
        HashMap<Integer, Flight> flightDb = airportRepositoryObject.getFlightDb();
        HashMap<String, Airport> airportDb = airportRepositoryObject.getAirportDb();
        if(flightDb.size() == 0) return null;
        if(airportDb.size() == 0) return null;
        if(!flightDb.containsKey(flightId)) return null;
        String airportName = null;
        City cityName = flightDb.get(flightId).getFromCity();
        for(Airport airport : airportDb.values())
        {
            if(cityName == airport.getCity())
            {
                airportName = airport.getAirportName();
                break;
            }
        }
        return airportName;
    }
    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity){

        //Find the duration by finding the shortest flight that connects these 2 cities directly
        //If there is no direct flight between 2 cities return -1.
        HashMap<Integer, Flight> flightDb = airportRepositoryObject.getFlightDb();
        double shortestDuration = -1;
        for(Flight flight : flightDb.values()){
            if(fromCity == flight.getFromCity() && toCity == flight.getToCity()){
                if(shortestDuration == -1 || flight.getDuration() < shortestDuration) {
                    shortestDuration = flight.getDuration();
                }
            }
        }
        return shortestDuration;
    }
    public int getNumberOfPeopleOn(Date date,String airportName){
        HashMap<Integer, Flight> flightDb = airportRepositoryObject.getFlightDb();
        HashMap<String, Airport> airportDb = airportRepositoryObject.getAirportDb();
        if(airportDb.size() == 0 || !airportDb.containsKey(airportName)) return 0;
        int countOfPeopleOnAirport = 0;
        for(Flight flight : flightDb.values()){
            if(flight.getFlightDate().equals(date)){
                City currentAirportCity = airportDb.get(airportName).getCity();
                if(currentAirportCity == flight.getFromCity() || currentAirportCity == flight.getToCity()){
                    countOfPeopleOnAirport++;
                }
            }
        }
        return countOfPeopleOnAirport;
    }
    public String bookATicket(Integer flightId, Integer passengerId){
        HashMap<Integer, List<Passenger>> flightPassengerBookingDb = airportRepositoryObject.getFlightPassengerBookingDb();
        HashMap<Integer, Flight> flightDb = airportRepositoryObject.getFlightDb();
        HashMap<Integer, Passenger> passengerDb = airportRepositoryObject.getPassengerDb();
        if(!flightDb.containsKey(flightId) || !passengerDb.containsKey(passengerId)) { return "FAILURE"; }
        if(flightPassengerBookingDb.containsKey(flightId))
        {
            int passengersWhoBookedFlight = flightPassengerBookingDb.get(flightId).size();
            int maxCapacityOfFlight = flightDb.get(flightId).getMaxCapacity();
            if(passengersWhoBookedFlight == maxCapacityOfFlight){ return "FAILURE"; }
            if(flightPassengerBookingDb.get(flightId).contains(passengerDb.get(passengerId))){ return "FAILURE"; }
        }
        airportRepositoryObject.bookATicket(flightId, passengerId);
        return "SUCCESS";
    }
    public void addPassenger(Passenger passenger){
        airportRepositoryObject.addPassenger(passenger);
    }
}
