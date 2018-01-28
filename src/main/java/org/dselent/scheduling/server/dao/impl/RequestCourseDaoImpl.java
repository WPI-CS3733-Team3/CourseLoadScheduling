package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.RequestCourseDao;
import org.dselent.scheduling.server.extractor.RequestCourseExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.RequestCourse;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RequestCourseDaoImpl extends BaseDaoImpl<RequestCourse> implements RequestCourseDao
{
	@Override
	public int insert(RequestCourse requestCourseModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(RequestCourse.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, requestCourseModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, requestCourseModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<RequestCourse> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		RequestCourseExtractor extractor = new RequestCourseExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(RequestCourse.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<RequestCourse> requestCourseList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return requestCourseList;
	}

	@Override
	public RequestCourse findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(RequestCourse.getColumnName(RequestCourse.Columns.ID), false);
		List<String> selectColumnNames = RequestCourse.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<RequestCourse> requestCourseList = select(selectColumnNames, queryTermList, orderByList);
	
		RequestCourse requestCourse = null;
	    
	    if(!requestCourseList.isEmpty())
	    {
	    	requestCourse = requestCourseList.get(0);
	    }
	    
	    return requestCourse;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(RequestCourse.TABLE_NAME, columnName, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		objectList.add(newValue);
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}
	
	@Override
	public int delete(List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateDeleteString(RequestCourse.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, RequestCourse requestCourseModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.ID)))
    	{
    		parameters.addValue(parameterName, requestCourseModel.getId());
    	}
    	else if(insertColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.REQUESTS_ID)))
    	{
    		parameters.addValue(parameterName, requestCourseModel.getRequestsID());
    	}
    	else if(insertColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.COURSES_ID)))
    	{
    		parameters.addValue(parameterName, requestCourseModel.getCoursesID());
    	}
    	else if(insertColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, requestCourseModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, requestCourseModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, requestCourseModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, RequestCourse requestCourseModel)
	{
    	if(keyHolderColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.ID)))
    	{
    		requestCourseModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.REQUESTS_ID)))
    	{
    		requestCourseModel.setRequestsID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.COURSES_ID)))
    	{
    		requestCourseModel.setCoursesID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.CREATED_AT)))
    	{
    		requestCourseModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.UPDATED_AT)))
    	{
    		requestCourseModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestCourse.getColumnName(RequestCourse.Columns.DELETED)))
    	{
    		requestCourseModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
    	}
	}
	
	@Override
	public void validateColumnNames(List<String> columnNameList)
	{
		List<String> actualColumnNames = RequestCourse.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
