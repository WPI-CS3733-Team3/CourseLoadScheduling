package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.MappingController;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MappingControllerImpl implements MappingController {
	@Autowired
	private MappingService mapService;
	
	//returns the list of start times
	@Override
	public ResponseEntity<String> getStartTimes(@RequestBody Map<String, String> request) throws Exception 
	{
		// Print is for testing purposes
		System.out.println("controller (map/time/start) reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		
		success.add(mapService.getStartTimes());
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	//returns the list of end times
	@Override
	public ResponseEntity<String> getEndTimes(@RequestBody Map<String, String> request) throws Exception 
	{
		// Print is for testing purposes
		System.out.println("controller (map/time/end) reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		
		success.add(mapService.getEndTimes());
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	//returns the list of terms
	@Override
	public ResponseEntity<String> getTerms(@RequestBody Map<String, String> request) throws Exception 
	{
		// Print is for testing purposes
		System.out.println("controller (map/terms) reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		
		success.add(mapService.getTerms());
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	//returns the list of frequencies
	@Override
	public ResponseEntity<String> getFrequencies(@RequestBody Map<String, String> request) throws Exception 
	{
		// Print is for testing purposes
		System.out.println("controller (map/frequencies) reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		
		success.add(mapService.getFrequencies());
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}	
	
	//returns the list of section types
	@Override
	public ResponseEntity<String> getSectionTypes(@RequestBody Map<String, String> request) throws Exception 
	{
		// Print is for testing purposes
		System.out.println("controller (map/sectionType) reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		
		success.add(mapService.getSectionTypes());
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
}
