package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.times.GetStartTimes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/time")
public interface TimeController {
	@RequestMapping(method=RequestMethod.POST, value=GetStartTimes.REQUEST_NAME)
	public ResponseEntity<String> getStartTimes(@RequestBody Map<String, String> request) throws Exception;
    
}
