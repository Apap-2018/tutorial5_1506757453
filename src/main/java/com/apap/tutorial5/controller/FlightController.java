package com.apap.tutorial5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;

/*
 * FlightController
 */
@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/flight/view/{flightNumber}")
	private String viewFlight(@PathVariable(value = "flightNumber") String flightNumber, Model model) {
		FlightModel flight = flightService.getFlightByFlightNumber(flightNumber);
		model.addAttribute("flight", flight);
		return "view-flight";
	}
	
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
    private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
	    for(FlightModel flight : pilot.getPilotFlight()) {
	    	flightService.deleteFlightById(flight.getId());
	    }
		
        return "delete";
	}

	 @RequestMapping(value = "/flight/update/{id}", method = RequestMethod.GET)
	 private String updateFlight(@PathVariable(value = "id") Long id, Model model) {
	    FlightModel flight = flightService.getFlightById(id);
	    
	    model.addAttribute("flight", flight);
	    model.addAttribute("pilots", ((PilotService) pilotService).getPilotDb().findAll());
	    return "update-flight";
	 }

	 @RequestMapping(value = "/flight/update/{id}", method = RequestMethod.POST)
	 private String updatePilotSubmit(@PathVariable(value = "id") Long id, @ModelAttribute FlightModel flight) {
         flight.setId(id);
		 flightService.updateFlight(flight);
         
	     return "add";
	 }
	
}
