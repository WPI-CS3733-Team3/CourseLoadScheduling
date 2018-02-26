package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.section.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/section")
public interface SectionController
{    
    @RequestMapping(method=RequestMethod.POST, value=Add.REQUEST_NAME)
	public ResponseEntity<String> addSection(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Edit.REQUEST_NAME)
	public ResponseEntity<String> editSection(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Remove.REQUEST_NAME)
   	public ResponseEntity<String> removeSection(@RequestBody Map<String, String> request) throws Exception;  
    
    @RequestMapping(method=RequestMethod.POST, value=View.REQUEST_NAME)
   	public ResponseEntity<String> viewSection(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=ViewAllInfo.REQUEST_NAME)
   	public ResponseEntity<String> viewAllInfo(@RequestBody Map<String, String> request) throws Exception;  
}
