package com.apap.tutorial5.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDB;

/*
 * FlightServiceImpl
 */
@Service
@Transactional
public class FlightServiceImpl implements FlightService {
	@Autowired
	private FlightDB flightDb;
	
	@Override
	public void addFlight(FlightModel flight) {
		flightDb.save(flight);
	}

	@Override
	public FlightModel getFlightByFlightNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
	}
	
	@Override
	public FlightModel getFlightById(Long id) {
		return flightDb.getOne(id);
	}
	
	@Override
	public void deleteFlightById(Long id) {
		flightDb.deleteById(id);
	}
	
	@Override
	public FlightModel updateFlight(FlightModel flight) {
		FlightModel flightToUpdate = flightDb.getOne(flight.getId());
		flightToUpdate.setDestination(flight.getDestination());
		flightToUpdate.setFlightNumber(flight.getFlightNumber());
		flightToUpdate.setOrigin(flight.getOrigin());
		flightToUpdate.setPilot(flight.getPilot());
		flightToUpdate.setTime(flight.getTime());
		
        flightDb.save(flightToUpdate);
        
        return flightToUpdate;
	}
}
