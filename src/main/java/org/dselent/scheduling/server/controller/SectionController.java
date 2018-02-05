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
	public ResponseEntity<String> add(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Edit.REQUEST_NAME)
	public ResponseEntity<String> edit(@RequestBody Map<String, String> request) throws Exception;
    
    @RequestMapping(method=RequestMethod.POST, value=Remove.REQUEST_NAME)
   	public ResponseEntity<String> remove(@RequestBody Map<String, String> request) throws Exception;   
}
