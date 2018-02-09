package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.dto.RequestDto;
import org.dselent.scheduling.server.model.RequestTables;
import org.dselent.scheduling.server.model.Request;



public interface RequestService {
	/**
	 * Registers a user into the system
	 * Performs an insert into the users table and users_roles_links table as a transaction
	 * 
	 * @param registerUserDto DTO container information for the insertions
	 * @return A list of rows affected for each insert operation
	 * @throws SQLException
	 */
	public List<RequestTables> viewAllRequests() throws SQLException;
	public List<Integer> reviewRequest(Integer requestId, Integer reviewedStatusId) throws SQLException;
	public List<Integer> submitRequest(RequestDto submitRequestDto) throws SQLException;
	public List<Integer> deleteRequest(Integer id) throws SQLException;
	public List<RequestTables> viewOwnRequest(Integer userId) throws SQLException;
}
