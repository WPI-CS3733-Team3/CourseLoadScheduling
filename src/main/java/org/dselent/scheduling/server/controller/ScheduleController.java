package org.dselent.scheduling.server.controller;

import java.util.Map;

//Import request classes
import org.dselent.scheduling.server.requests.schedule.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/schedule")
public interface ScheduleController
{    
    @RequestMapping(method=RequestMethod.POST, value=ViewAll.REQUEST_NAME)
	public ResponseEntity<String> viewAll(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=ViewOne.REQUEST_NAME)
	public ResponseEntity<String> view(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Remove.REQUEST_NAME)
	public ResponseEntity<String> remove(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Add.REQUEST_NAME)
	public ResponseEntity<String> add(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Update.REQUEST_NAME)
	public ResponseEntity<String> update(@RequestBody Map<String, String> request) throws Exception;
    
}
