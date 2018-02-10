package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.course.Create;
import org.dselent.scheduling.server.requests.course.Edit;
import org.dselent.scheduling.server.requests.course.RemoveCourse;
import org.dselent.scheduling.server.requests.course.ViewAllCourse;
import org.dselent.scheduling.server.requests.course.ViewOneCourse;
import org.dselent.scheduling.server.requests.schedule.Add;
import org.dselent.scheduling.server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


public class CourseControllerImpl {
	
	@Autowired
    private CourseService courseService;
	
	@Override
	public ResponseEntity<String> create(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String courseName = request.get(Create.getBodyName(Create.BodyKey.COURSE_NAME));
		String courseNumber = request.get(Create.getBodyName(Create.BodyKey.COURSE_NUMBER));
		String frequency = request.get(Create.getBodyName(Create.BodyKey.FREQUENCY));
		
		//build response
		courseService.createCourse(courseName, courseNumber, frequency);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@Transactional
	@Override
	public ResponseEntity<String> edit(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String courseName = request.get(Create.getBodyName(Create.BodyKey.COURSE_NAME));
		String courseNumber = request.get(Create.getBodyName(Create.BodyKey.COURSE_NUMBER));
		String frequency = request.get(Create.getBodyName(Create.BodyKey.FREQUENCY));
		
		//build response
		courseService.editCourse(courseName, courseNumber, frequency);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
    

	@Override
	public ResponseEntity<String> ViewOneCourse(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String courseId = request.get(ViewOneCourse.getBodyName(ViewOneCourse.BodyKey.COURSE_ID));
		
		//build response
		success.add(courseService.viewOneCourse(courseId));
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
    

	@Override
	public ResponseEntity<String> RemoveCourse(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String courseId = request.get(ViewOneCourse.getBodyName(ViewOneCourse.BodyKey.COURSE_ID));
		
		//build response
		courseService.removeCourse(courseId);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
		
	}
    

	@Override
	public ResponseEntity<String> ViewAllCourse(@RequestBody Map<String, String> request) throws Exception{
		// Print is for testing purposes
		System.out.println("controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		//build response
		courseService.viewAllCourses();
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
}
