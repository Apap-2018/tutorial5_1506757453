package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDB;

/*
 * PilotService
 */
public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	
	PilotModel getPilotById(Long id);
	
	void addPilot(PilotModel pilot);
	
	void updatePilot(PilotModel pilot);
	
	void deletePilotById(Long id);
	
	PilotDB getPilotDb();
}
