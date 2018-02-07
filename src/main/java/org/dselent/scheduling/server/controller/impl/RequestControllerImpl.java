package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.RequestController;
import org.dselent.scheduling.server.dto.AddSectionDto;
import org.dselent.scheduling.server.dto.EditSectionDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.request.*;
import org.dselent.scheduling.server.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

public class RequestControllerImpl implements RequestController {

	@Override
	public ResponseEntity<String> viewAll(Map<String, String> request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> submit(Map<String, String> request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> delete(Map<String, String> request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> viewOwn(Map<String, String> request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
