package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;

/*
 * FlightService
 */
public interface FlightService {
	void addFlight(FlightModel flight);
	
	void deleteFlightById(Long id);
	
	FlightModel updateFlight(FlightModel flight);
	
	FlightModel getFlightById(Long id);
	
	FlightModel getFlightByFlightNumber(String flightNumber);
}
