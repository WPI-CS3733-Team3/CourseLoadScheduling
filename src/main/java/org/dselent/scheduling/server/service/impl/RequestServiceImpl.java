package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.RequestCourseDao;
import org.dselent.scheduling.server.dao.RequestsDao;
import org.dselent.scheduling.server.dto.RequestDto;
import org.dselent.scheduling.server.model.RequestTables;
import org.dselent.scheduling.server.model.Request;
import org.dselent.scheduling.server.model.RequestCourse;
import org.dselent.scheduling.server.service.RequestService;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;

public class RequestServiceImpl implements RequestService {

	@Autowired
	private CustomDao customDao;

	@Autowired
	private RequestsDao requestsDao;

	@Autowired
	private RequestCourseDao requestCourseDao;

	public RequestServiceImpl() {
		//
	}

	@Override
	public List<RequestTables> viewAllRequests() throws SQLException {

		List<RequestTables> requestTablesList = new ArrayList<>(customDao.getAllRequestsInfo());
		return requestTablesList;
	}

	/**Selects the requests to review and then updates the request status
	 * @param requestId The id of the request being updated
	 * @param reviewedStatusId The id of the new status
	 * @return The updated review to be shown to the user
	 */
	@Override
	public Request reviewRequest(Integer requestId, Integer reviewedStatusId) throws SQLException {
		// TODO Do what Matt did for Users: select by the reqtestId and then change its status id to the reviewed one
		return null;
	}

	@Override
	public List<Integer> submitRequest(RequestDto submitRequestDto) throws SQLException {

		List<Integer> rowsAffectedList = new ArrayList<>();

		//Extract Requests table data and insert
		Request request = new Request();
		request.setUserId(submitRequestDto.getUserId());
		request.setStatusId(submitRequestDto.getStatusId());
		request.setDeleted(false);
		List<String> requestInsertColumnNameList = new ArrayList<>();
		List<String> requestKeyHolderColumnNameList = new ArrayList<>();
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.USERS_ID));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.REQUEST_STATUS_ID));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.ID));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.CREATED_AT));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.UPDATED_AT));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.DELETED));
		rowsAffectedList.add(requestsDao.insert(request, requestInsertColumnNameList, requestKeyHolderColumnNameList));

		//Extract RequestCourse table data and insert
		RequestCourse requestCourse = new RequestCourse();
		requestCourse.setCoursesID(submitRequestDto.getCoursesId());
		//requestCourse.setRequestsID(<somehow get the request ID>);
		List<String> requestCourseInsertColumnNameList = new ArrayList<>();
		List<String> requestCourseKeyHolderColumnNameList = new ArrayList<>();
		requestCourseInsertColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.COURSES_ID));
		requestCourseInsertColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.REQUESTS_ID));
		requestCourseKeyHolderColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.ID));
		requestCourseKeyHolderColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.CREATED_AT));
		requestCourseKeyHolderColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.UPDATED_AT));
		requestCourseKeyHolderColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.DELETED));
		rowsAffectedList.add(requestCourseDao.insert(requestCourse, requestInsertColumnNameList, requestKeyHolderColumnNameList));

		//TODO Repeat the above for the following Request models:
		/*RequestOther requestOther = new RequestOther();
		RequestStatus requestStatus = new RequestStatus();
		RequestTerm requestTerm = new RequestTerm();
		RequestTime requestTime = new RequestTime();


		request.setTermsID(submitRequestDto.getTermID());
		request.setSectionTypeID(submitRequestDto.getSectionTypeID());
		request.setDaysID(submitRequestDto.getDaysID());
		request.setCoursesID(submitRequestDto.getCoursesID());
		request.setStartID(submitRequestDto.getStartID());
		request.setEndID(submitRequestDto.getEndID());

		List<String> requestInsertColumnNameList = new ArrayList<>();
		List<String> requestKeyHolderColumnNameList = new ArrayList<>();


		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.TERMS_ID));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.SECTION_TYPE_ID));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.DAYS_ID));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.COURSES_ID));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.START_ID));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.END_ID));

		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.ID));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.CREATED_AT));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.UPDATED_AT));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.DELETED));
		 */

		return rowsAffectedList;

	}

	@Override
	public List<Integer> deleteRequest(Integer id) throws SQLException {

		List<Integer> rowsAffectedList = new ArrayList<>();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm qt = new QueryTerm();
		qt.setColumnName(Request.getColumnName(Request.Columns.ID));
		qt.setComparisonOperator(ComparisonOperator.EQUAL);
		qt.setValue(id);
		qt.setLogicalOperator(null);
		queryTermList.add(qt);
		rowsAffectedList.add(requestsDao.delete(queryTermList));
		return rowsAffectedList;
	}

	@Override
	public List<RequestTables> viewOwnRequest(Integer userId) throws SQLException {
		List<RequestTables> requestTablesList = new ArrayList<>(customDao.getOneUserRequestsInfo(userId));
		return requestTablesList;
	}

}
