package org.dselent.scheduling.server.controller.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.ScheduleController;
import org.dselent.scheduling.server.dto.UpdateScheduleDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.schedule.*;
import org.dselent.scheduling.server.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ScheduleControllerImpl implements ScheduleController {

	@Autowired
    private ScheduleService schedulingService;
	
	@Override
	public ResponseEntity<String> viewAll(@RequestBody Map<String, String> request) throws Exception 
    {
		// Print is for testing purposes
		System.out.println("controller (schedule/viewAll) reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		Integer termsId = Integer.parseInt(request.get(ViewAll.getBodyName(ViewAll.BodyKey.TERMS_ID)));
		
		schedulingService.viewAllSchedule(termsId);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
				
		//return null;
    }
	

	public ResponseEntity<String> view(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller (schedule/viewOne) reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		Integer termsId = Integer.parseInt(request.get(ViewOne.getBodyName(ViewOne.BodyKey.TERMS_ID)));
		Integer usersId = Integer.parseInt(request.get(ViewOne.getBodyName(ViewOne.BodyKey.USERS_ID)));
		
		schedulingService.viewOneSchedule(termsId, usersId);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);

		//return null;
	}
    
	public ResponseEntity<String> remove(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller (schedule/remove) reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		Integer sectionId = Integer.parseInt(request.get(Remove.getBodyName(Remove.BodyKey.SECTIONS_ID)));
		
		schedulingService.removeClassSchedule(sectionId);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
		
		//return null;
	}
    
	public ResponseEntity<String> add(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller (schedule/add) reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		Integer sectionId = Integer.parseInt(request.get(Add.getBodyName(Add.BodyKey.SECTIONS_ID)));
		Integer facultyId = Integer.parseInt(request.get(Add.getBodyName(Add.BodyKey.FACULTY_ID)));
		
		schedulingService.addClassSchedule(sectionId, facultyId);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
		
		//return null;
	}
    
	public ResponseEntity<String> update(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller (schedule/update) reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		Integer termsId = Integer.parseInt(request.get(Update.getBodyName(Update.BodyKey.TERMS_ID)));
		Integer sectionId = Integer.parseInt(request.get(Update.getBodyName(Update.BodyKey.SECTIONS_ID)));
		Integer daysId = Integer.parseInt(request.get(Update.getBodyName(Update.BodyKey.DAYS_ID)));
		Integer startId = Integer.parseInt(request.get(Update.getBodyName(Update.BodyKey.START_ID)));
		Integer endId = Integer.parseInt(request.get(Update.getBodyName(Update.BodyKey.END_ID)));
		Integer facultyId = Integer.parseInt(request.get(Update.getBodyName(Update.BodyKey.FACULTY_ID)));
		
		//build dto to pass in here
		UpdateScheduleDto.Builder builder = UpdateScheduleDto.builder();
		UpdateScheduleDto dto = builder.withTermsId(termsId).withSectionsId(sectionId).withDaysId(daysId)
				.withStartId(startId).withEndId(endId).withFacultyId(facultyId).build();
		
		
		schedulingService.updateSchedule(dto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
				
		//return null;
	}
    
	
	
	
}
