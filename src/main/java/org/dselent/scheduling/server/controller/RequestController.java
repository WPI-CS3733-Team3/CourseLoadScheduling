package org.dselent.scheduling.server.controller;

import java.util.Map;

import org.dselent.scheduling.server.requests.request.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/request")
public interface RequestController
{    
	@RequestMapping(method=RequestMethod.POST, value=ViewAll.REQUEST_NAME)
	public ResponseEntity<String> viewAll(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=Review.REQUEST_NAME)
	public ResponseEntity<String> review(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=Submit.REQUEST_NAME)
	public ResponseEntity<String> submit(@RequestBody Map<String, String> request, @RequestHeader Map<String, String> header) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=Delete.REQUEST_NAME)
	public ResponseEntity<String> delete(@RequestBody Map<String, String> request) throws Exception;

	@RequestMapping(method=RequestMethod.POST, value=ViewOwn.REQUEST_NAME)
	public ResponseEntity<String> viewOwn(@RequestBody Map<String, String> request, @RequestHeader Map<String, String> header) throws Exception;
}
