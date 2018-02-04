package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.course.Create;
import org.dselent.scheduling.server.requests.course.Edit;
import org.dselent.scheduling.server.requests.course.ViewOneCourse;
import org.dselent.scheduling.server.requests.course.RemoveCourse;
//Import request classes
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/course")
public interface CourseController
{    
    @RequestMapping(method=RequestMethod.POST, value = Create.REQUEST_NAME)
	public ResponseEntity<String> create(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value = Edit.REQUEST_NAME)
	public ResponseEntity<String> edit(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value = ViewOneCourse.REQUEST_NAME)
	public ResponseEntity<String> ViewOneCourse(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value = ViewOneCourse.REQUEST_NAME)
	public ResponseEntity<String> RemoveCourse(@RequestBody Map<String, String> request) throws Exception;
    
    
    
}
