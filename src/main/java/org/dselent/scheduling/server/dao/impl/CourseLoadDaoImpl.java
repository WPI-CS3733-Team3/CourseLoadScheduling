package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CourseLoadDao;
import org.dselent.scheduling.server.extractor.CourseLoadExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.CourseLoad;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseLoadDaoImpl extends BaseDaoImpl<CourseLoad> implements CourseLoadDao
{
	@Override
	public int insert(CourseLoad courseLoadModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(CourseLoad.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, courseLoadModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, courseLoadModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<CourseLoad> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		CourseLoadExtractor extractor = new CourseLoadExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(CourseLoad.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<CourseLoad> courseLoadList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return courseLoadList;
	}

	@Override
	public CourseLoad findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(CourseLoad.getColumnName(CourseLoad.Columns.ID), false);
		List<String> selectColumnNames = CourseLoad.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<CourseLoad> courseLoadList = select(selectColumnNames, queryTermList, orderByList);
	
		CourseLoad courseLoad = null;
	    
	    if(!courseLoadList.isEmpty())
	    {
	    	courseLoad = courseLoadList.get(0);
	    }
	    
	    return courseLoad;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(CourseLoad.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(CourseLoad.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseLoad courseLoadModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.ID)))
    	{
    		parameters.addValue(parameterName, courseLoadModel.getId());
    	}
    	else if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.COURSE_LOAD_TYPE_ID)))
    	{
    		parameters.addValue(parameterName, courseLoadModel.getCourseLoadTypeID());
    	}
    	else if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.COURSE_LOAD_HOURS)))
    	{
    		parameters.addValue(parameterName, courseLoadModel.getCourseLoadHours());
    	}
    	else if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.FACULTY_ID)))
    	{
    		parameters.addValue(parameterName, courseLoadModel.getFacultyID());
    	}
    	else if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, courseLoadModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, courseLoadModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, courseLoadModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseLoad courseLoadModel)
	{
    	if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.ID)))
    	{
    		courseLoadModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.COURSE_LOAD_TYPE_ID)))
    	{
    		courseLoadModel.setCourseLoadTypeID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.COURSE_LOAD_HOURS)))
    	{
    		courseLoadModel.setCourseLoadHours((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.FACULTY_ID)))
    	{
    		courseLoadModel.setFacultyID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.CREATED_AT)))
    	{
    		courseLoadModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.UPDATED_AT)))
    	{
    		courseLoadModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CourseLoad.getColumnName(CourseLoad.Columns.DELETED)))
    	{
    		courseLoadModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = CourseLoad.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
