package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.RequestCourseDao;
import org.dselent.scheduling.server.dao.RequestOtherDao;
import org.dselent.scheduling.server.dao.RequestTermDao;
import org.dselent.scheduling.server.dao.RequestTimeDao;
import org.dselent.scheduling.server.dao.RequestsDao;
import org.dselent.scheduling.server.dto.RequestDto;
import org.dselent.scheduling.server.model.Request;
import org.dselent.scheduling.server.model.RequestCourse;
import org.dselent.scheduling.server.model.RequestOther;
import org.dselent.scheduling.server.model.RequestTables;
import org.dselent.scheduling.server.model.RequestTerm;
import org.dselent.scheduling.server.model.RequestTime;
import org.dselent.scheduling.server.service.RequestService;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private CustomDao customDao;

	@Autowired
	private RequestsDao requestsDao;

	@Autowired
	private RequestCourseDao requestCourseDao;

	@Autowired
	private RequestOtherDao requestOtherDao;

	@Autowired
	private RequestTermDao requestTermDao;

	@Autowired
	private RequestTimeDao requestTimeDao;

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
	public List<Integer> reviewRequest(Integer requestId, Integer reviewedStatusId) throws SQLException {

		List<Integer> rowsAffectedList = new ArrayList<>();
		//Generate QueeryTerm for selecting the request based on its ID
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm qt = new QueryTerm();
		qt.setColumnName(Request.getColumnName(Request.Columns.ID));
		qt.setComparisonOperator(ComparisonOperator.EQUAL);
		qt.setValue(requestId);
		qt.setLogicalOperator(null);
		queryTermList.add(qt);
		rowsAffectedList.add(requestsDao.update(Request.getColumnName(Request.Columns.REQUEST_STATUS_ID), reviewedStatusId, queryTermList));

		return rowsAffectedList;
	}

	/* (non-Javadoc)
	 * @see org.dselent.scheduling.server.service.RequestService#submitRequest(org.dselent.scheduling.server.dto.RequestDto)
	 * ~~~~We have have to do some querying to get the actual row IDs for the pre-populated tables~~~~
	 */
	@Override
	public List<Integer> submitRequest(RequestDto submitRequestDto) throws SQLException {

		List<Integer> rowsAffectedList = new ArrayList<Integer>();

		//Extract Requests table data and insert
		Request request = new Request();
		request.setUserId(submitRequestDto.getUserId());
		request.setStatusId(submitRequestDto.getStatusId()); //Will always be status 2 (pending) for new requests until their are reviewed
		request.setDeleted(false);
		List<String> requestInsertColumnNameList = new ArrayList<>();
		List<String> requestKeyHolderColumnNameList = new ArrayList<>();
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.USERS_ID));
		requestInsertColumnNameList.add(Request.getColumnName(Request.Columns.REQUEST_STATUS_ID));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.ID));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.CREATED_AT));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.UPDATED_AT));
		requestKeyHolderColumnNameList.add(Request.getColumnName(Request.Columns.DELETED));
		//Adds the ID of the inserted row to rowsAffectedList
		rowsAffectedList.add(requestsDao.insert(request, requestInsertColumnNameList, requestKeyHolderColumnNameList));

		//Extract RequestCourse table data and insert
		RequestCourse requestCourse = new RequestCourse();
		requestCourse.setCoursesID(submitRequestDto.getCoursesId());
		//Get request being submitted row ID
		requestCourse.setRequestsID(rowsAffectedList.get(0));
		requestCourse.setDeleted(false);
		List<String> requestCourseInsertColumnNameList = new ArrayList<>();
		List<String> requestCourseKeyHolderColumnNameList = new ArrayList<>();
		requestCourseInsertColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.COURSES_ID));
		requestCourseInsertColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.REQUESTS_ID));
		requestCourseKeyHolderColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.ID));
		requestCourseKeyHolderColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.CREATED_AT));
		requestCourseKeyHolderColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.UPDATED_AT));
		requestCourseKeyHolderColumnNameList.add(RequestCourse.getColumnName(RequestCourse.Columns.DELETED));
		rowsAffectedList.add(requestCourseDao.insert(requestCourse, requestInsertColumnNameList, requestKeyHolderColumnNameList));

		//Extract RequestOther table data and insert
		RequestOther requestOther = new RequestOther();
		requestOther.setMessage(submitRequestDto.getMessage());
		//Get request being submitted row ID
		requestOther.setRequestsID(rowsAffectedList.get(0));
		requestOther.setDeleted(false);
		List<String> requestOtherInsertColumnNameList = new ArrayList<>();
		List<String> requestOtherKeyHolderColumnNameList = new ArrayList<>();
		requestOtherInsertColumnNameList.add(RequestOther.getColumnName(RequestOther.Columns.MESSAGE));
		requestOtherInsertColumnNameList.add(RequestOther.getColumnName(RequestOther.Columns.REQUESTS_ID));
		requestOtherKeyHolderColumnNameList.add(RequestOther.getColumnName(RequestOther.Columns.ID));
		requestOtherKeyHolderColumnNameList.add(RequestOther.getColumnName(RequestOther.Columns.CREATED_AT));
		requestOtherKeyHolderColumnNameList.add(RequestOther.getColumnName(RequestOther.Columns.UPDATED_AT));
		requestOtherKeyHolderColumnNameList.add(RequestOther.getColumnName(RequestOther.Columns.DELETED));
		rowsAffectedList.add(requestOtherDao.insert(requestOther, requestInsertColumnNameList, requestKeyHolderColumnNameList));

		//Extract RequestTerm table data and insert		
		RequestTerm requestTerm = new RequestTerm();
		requestTerm.setTermsID(submitRequestDto.getTermsId());
		//Get request being submitted row ID
		requestTerm.setRequestsID(rowsAffectedList.get(0));
		requestTerm.setDeleted(false);
		List<String> requestTermInsertColumnNameList = new ArrayList<>();
		List<String> requestTermKeyHolderColumnNameList = new ArrayList<>();
		requestTermInsertColumnNameList.add(RequestTerm.getColumnName(RequestTerm.Columns.TERMS_ID));
		requestTermInsertColumnNameList.add(RequestTerm.getColumnName(RequestTerm.Columns.REQUESTS_ID));
		requestTermKeyHolderColumnNameList.add(RequestTerm.getColumnName(RequestTerm.Columns.ID));
		requestTermKeyHolderColumnNameList.add(RequestTerm.getColumnName(RequestTerm.Columns.CREATED_AT));
		requestTermKeyHolderColumnNameList.add(RequestTerm.getColumnName(RequestTerm.Columns.UPDATED_AT));
		requestTermKeyHolderColumnNameList.add(RequestTerm.getColumnName(RequestTerm.Columns.DELETED));
		rowsAffectedList.add(requestTermDao.insert(requestTerm, requestInsertColumnNameList, requestKeyHolderColumnNameList));

		//Extract RequestTerm table data and insert
		RequestTime requestTime = new RequestTime();
		requestTime.setStartID(submitRequestDto.getStartId());
		requestTime.setEndID(submitRequestDto.getEndId());
		//Get request being submitted row ID
		requestTime.setRequestsID(rowsAffectedList.get(0));
		requestTime.setDeleted(false);
		List<String> requestTimeInsertColumnNameList = new ArrayList<>();
		List<String> requestTimeKeyHolderColumnNameList = new ArrayList<>();
		requestTimeInsertColumnNameList.add(RequestTime.getColumnName(RequestTime.Columns.START_ID));
		requestTimeInsertColumnNameList.add(RequestTime.getColumnName(RequestTime.Columns.END_ID));
		requestTimeInsertColumnNameList.add(RequestTime.getColumnName(RequestTime.Columns.REQUESTS_ID));
		requestTimeKeyHolderColumnNameList.add(RequestTime.getColumnName(RequestTime.Columns.ID));
		requestTimeKeyHolderColumnNameList.add(RequestTime.getColumnName(RequestTime.Columns.CREATED_AT));
		requestTimeKeyHolderColumnNameList.add(RequestTime.getColumnName(RequestTime.Columns.UPDATED_AT));
		requestTimeKeyHolderColumnNameList.add(RequestTime.getColumnName(RequestTime.Columns.DELETED));
		rowsAffectedList.add(requestTimeDao.insert(requestTime, requestInsertColumnNameList, requestKeyHolderColumnNameList));

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
		List<RequestTables> requestTablesList = new ArrayList<RequestTables>(customDao.getOneUserRequestsInfo(userId));
		return requestTablesList;
	}

}
