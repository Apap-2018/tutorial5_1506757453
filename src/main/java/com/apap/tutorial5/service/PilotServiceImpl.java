package com.apap.tutorial5.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDB;

/*
 * PilotServiceImpl
 */
@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDB pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public PilotModel getPilotById (Long id) {
		return pilotDb.getOne(id);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}
	
	@Override
	public void updatePilot(PilotModel pilot) {
		PilotModel pilotToUpdate = pilotDb.getOne(pilot.getId());
		pilotToUpdate.setFlyHour(pilot.getFlyHour());
		pilotToUpdate.setLicenseNumber(pilot.getLicenseNumber());
        pilotToUpdate.setName(pilot.getName());
        
        pilotDb.save(pilot);
	}
	
	@Override 
	public void deletePilotById(Long id) {
//		List<FlightModel> pilotFlight = pilotDb.getOne(id).getPilotFlight();
//		for (int i=0; i < pilotFlight.size(); i++) {
//			pilotFlight.remove(i);
//		}
		pilotDb.deleteById(id);
		
	}
	
	public PilotDB getPilotDb() {
        return pilotDb;
    }
}
