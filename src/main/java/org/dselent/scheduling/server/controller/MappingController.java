package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.mapping.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/map")
public interface MappingController {
	@RequestMapping(method=RequestMethod.POST, value=GetStartTimes.REQUEST_NAME)
	public ResponseEntity<String> getStartTimes(@RequestBody Map<String, String> request) throws Exception;
    
	@RequestMapping(method=RequestMethod.POST, value=GetEndTimes.REQUEST_NAME)
	public ResponseEntity<String> getEndTimes(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=GetTerms.REQUEST_NAME)
	public ResponseEntity<String> getTerms(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=GetFrequencies.REQUEST_NAME)
	public ResponseEntity<String> getFrequencies(@RequestBody Map<String, String> request) throws Exception;
	
	@RequestMapping(method=RequestMethod.POST, value=GetSectionTypes.REQUEST_NAME)
	public ResponseEntity<String> getSectionTypes(@RequestBody Map<String, String> request) throws Exception;
	
}
