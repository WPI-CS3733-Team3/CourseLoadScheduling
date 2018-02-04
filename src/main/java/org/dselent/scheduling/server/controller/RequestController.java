package org.dselent.scheduling.server.controller;

import java.util.Map;

//Import request classes
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/request")
public interface RequestController
{    
    @RequestMapping(method=RequestMethod.POST, value=)
	public ResponseEntity<String> register(@RequestBody Map<String, String> request) throws Exception;
}
