package org.dselent.scheduling.server.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.controller.UsersController;
import org.dselent.scheduling.server.miscellaneous.JsonResponseCreator;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.requests.user.*;
import org.dselent.scheduling.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller for handling requests related to the user such as logging in or registering.
 * Controller methods are the first methods reached by the request server-side (with special exception).
 * They parse the request and then call the appropriate service method to execute the business logic.
 * Any results or data is then sent back to the client.
 * 
 * @author dselent
 */
@Controller
public class UsersControllerImpl implements UsersController
{
	@Autowired
    private UserService userService;
    
	/**
	 * 
	 * @param request The body of the request expected to contain a map of String key-value pairs
	 * @return A ResponseEntity for the response object(s) and the status code
	 * @throws Exception 
	 */
	@Override
	public ResponseEntity<String> createAccount(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("Create Account controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String email = request.get(CreateAccount.getBodyName(CreateAccount.BodyKey.EMAIL));
		String password = request.get(CreateAccount.getBodyName(CreateAccount.BodyKey.PASSWORD));
		String accountType = request.get(CreateAccount.getBodyName(CreateAccount.BodyKey.ACCOUNT_TYPE));

		success.add(userService.createUser(email, password, accountType));
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	@Override
	public ResponseEntity<String> login(@RequestBody Map<String, String> request) throws Exception 
    {
    	// Print is for testing purposes
		System.out.println("Login controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String username = request.get(Login.getBodyName(Login.BodyKey.USER_NAME));
		String password = request.get(Login.getBodyName(Login.BodyKey.PASSWORD));

		User user = userService.login(username, password); 
		success.add(user);
		
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
    }
	
	@Override
	public ResponseEntity<String> promote(@RequestBody Map<String, String> request) throws Exception
	{
		// Print is for testing purposes
		System.out.println("Promote controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		
		String email = request.get(Promote.getBodyName(Promote.BodyKey.EMAIL));

		userService.promote(email);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> editAccount(@RequestBody Map<String, String> request) throws Exception 
	{
		System.out.println("Edit Account controller reached");
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		int id = Integer.parseInt(request.get(EditAccount.getHeaderName(EditAccount.HeaderKey.ID)));
		String firstName = request.get(EditAccount.getBodyName(EditAccount.BodyKey.FIRST_NAME));
		String lastName = request.get(EditAccount.getBodyName(EditAccount.BodyKey.LAST_NAME));
		String password = request.get(EditAccount.getBodyName(EditAccount.BodyKey.PASSWORD));

		userService.editUser(id, firstName, lastName, password);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> deleteAccount(@RequestBody Map<String, String> request) throws Exception 
	{
		// Print is for testing purposes
		System.out.println("Delete Account controller reached");
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();
		int id = Integer.parseInt(request.get(DeleteAccount.getHeaderName(DeleteAccount.HeaderKey.ID)));
		int idToDelete = Integer.parseInt(request.get(DeleteAccount.getBodyName(DeleteAccount.BodyKey.ID_TO_DELETE)));
		
		userService.deleteUser(id, idToDelete);
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> viewRoster(@RequestBody Map<String, String> request) throws Exception
	{
		// Print is for testing purposes
		System.out.println("View Roster controller reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		success.add(userService.viewRoster());
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> viewUser(@RequestBody Map<String, String> request) throws Exception {
		// Print is for testing purposes
		System.out.println("controller (user/view/users) reached");
    	
		// add any objects that need to be returned to the success list
		String response = "";
		List<Object> success = new ArrayList<Object>();

		success.add(userService.viewUsers());
		response = JsonResponseCreator.getJSONResponse(JsonResponseCreator.ResponseKey.SUCCESS, success);

		return new ResponseEntity<String>(response, HttpStatus.OK);
		
	}
	
	
	
	
}

	