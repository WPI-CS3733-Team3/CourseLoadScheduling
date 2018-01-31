package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CourseLoadHistoryDao;
import org.dselent.scheduling.server.extractor.CourseLoadHistoryExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.CourseLoadHistory;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseLoadHistoryDaoImpl extends BaseDaoImpl<CourseLoadHistory> implements CourseLoadHistoryDao{
	@Override
	public int insert(CourseLoadHistory courseLoadHistoryModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(CourseLoadHistory.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, courseLoadHistoryModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, courseLoadHistoryModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<CourseLoadHistory> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		CourseLoadHistoryExtractor extractor = new CourseLoadHistoryExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(CourseLoadHistory.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<CourseLoadHistory> courseLoadHistoryList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return courseLoadHistoryList;
	}

	@Override
	public CourseLoadHistory findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.ID), false);
		List<String> selectColumnNames = CourseLoadHistory.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<CourseLoadHistory> courseLoadHistoryList = select(selectColumnNames, queryTermList, orderByList);
	
		CourseLoadHistory courseLoadHistory = null;
	    
	    if(!courseLoadHistoryList.isEmpty())
	    {
	    	courseLoadHistory = courseLoadHistoryList.get(0);
	    }
	    
	    return courseLoadHistory;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(CourseLoadHistory.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(CourseLoadHistory.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseLoadHistory courseLoadHistoryModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.ID)))
    	{
    		parameters.addValue(parameterName, courseLoadHistoryModel.getId());
    	}
    	else if(insertColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.COURSE_LOAD_TYPE_ID)))
    	{
    		parameters.addValue(parameterName, courseLoadHistoryModel.getCourseLoadTypeID());
    	}
    	else if(insertColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.COURSE_LOAD_HOURS)))
    	{
    		parameters.addValue(parameterName, courseLoadHistoryModel.getCourseLoadHours());
    	}
    	else if(insertColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.FACULTY_ID)))
    	{
    		parameters.addValue(parameterName, courseLoadHistoryModel.getFacultyID());
    	}
    	else if(insertColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, courseLoadHistoryModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, courseLoadHistoryModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, courseLoadHistoryModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseLoadHistory courseLoadHistoryModel)
	{
    	if(keyHolderColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.ID)))
    	{
    		courseLoadHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.COURSE_LOAD_TYPE_ID)))
    	{
    		courseLoadHistoryModel.setCourseLoadTypeID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.COURSE_LOAD_HOURS)))
    	{
    		courseLoadHistoryModel.setCourseLoadHours((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.FACULTY_ID)))
    	{
    		courseLoadHistoryModel.setFacultyID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.CREATED_AT)))
    	{
    		courseLoadHistoryModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.UPDATED_AT)))
    	{
    		courseLoadHistoryModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.DELETED)))
    	{
    		courseLoadHistoryModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = CourseLoadHistory.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
