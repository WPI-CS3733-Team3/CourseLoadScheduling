package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.RequestController;
import org.dselent.scheduling.server.dto.RequestDto;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.requests.request.Delete;
import org.dselent.scheduling.server.requests.request.Review;
import org.dselent.scheduling.server.requests.request.Submit;
import org.dselent.scheduling.server.requests.request.ViewOwn;
import org.dselent.scheduling.server.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for handling requests related to course load requests such as submitting or reviewing.
 * Controller methods are the first methods reached by the request server-side (with special exception).
 * They parse the request and then call the appropriate service method to execute the business logic.
 * Any results or data is then sent back to the client.
 * 
 * @author dselent, modified by fcampanelli
 */
@Controller
public class RequestControllerImpl implements RequestController {

	@Autowired
	private RequestService requestService;

	@Override
	public ResponseEntity<String> delete(@RequestBody Map<String, String> request) throws Exception {

		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		//Delete functions
		Integer requestId = Integer.parseInt(request.get(Delete.getBodyName(Delete.BodyKey.REQUEST_ID)));

		requestService.deleteRequest(requestId);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> review(@RequestBody Map<String, String> request) throws Exception {

		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		//This is the id of the row that the request to be reviewed exists on
		Integer requestId = Integer.parseInt(request.get(Review.getBodyName(Review.BodyKey.REQUEST_ID)));
		//This is the id of the approved or denied request, after the client has made their decision
		Integer requestStatusId = Integer.parseInt(request.get(Review.getBodyName(Review.BodyKey.REQUEST_STATUS_ID)));

		requestService.reviewRequest(requestId, requestStatusId);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<String> submit(@RequestBody Map<String, String> request) throws Exception {

		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		//Submit functions
		Integer userId = Integer.parseInt(request.get(Submit.getBodyName(Submit.BodyKey.USER_ID)));
		Integer coursesID = Integer.parseInt(request.get(Submit.getBodyName(Submit.BodyKey.COURSES_ID)));
		Integer startID = Integer.parseInt(request.get(Submit.getBodyName(Submit.BodyKey.START_ID)));
		Integer endID = Integer.parseInt(request.get(Submit.getBodyName(Submit.BodyKey.END_ID)));
		Integer termsID = Integer.parseInt(request.get(Submit.getBodyName(Submit.BodyKey.TERMS_ID)));
		String message = request.get(Submit.getBodyName(Submit.BodyKey.MESSAGE));

		//Submit DTO
		RequestDto.Builder builder = RequestDto.builder();
		RequestDto requestDto = builder.withUsersId(userId)
				.withCoursesId(coursesID)
				.withStartId(startID)
				.withEndId(endID)
				.withTermsId(termsID)
				.withMessage(message)
				.build();

		requestService.submitRequest(requestDto);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> viewAll(@RequestBody Map<String, String> request) throws Exception {

		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		requestService.viewAllRequests();
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> viewOwn(@RequestBody Map<String, String> request) throws Exception {

		// Print is for testing purposes
		System.out.println("controller reached");

		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		//View own functions
		Integer userId = Integer.parseInt(request.get(ViewOwn.getHeaderName(ViewOwn.HeaderKey.USER_ID)));

		requestService.viewOwnRequest(userId);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

}