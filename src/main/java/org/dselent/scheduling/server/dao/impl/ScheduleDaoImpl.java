package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.ScheduleDao;
import org.dselent.scheduling.server.extractor.ScheduleExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDaoImpl extends BaseDaoImpl<Schedule> implements ScheduleDao
{
	@Override
	public int insert(Schedule scheduleModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Schedule.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, scheduleModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, scheduleModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<Schedule> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		ScheduleExtractor extractor = new ScheduleExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Schedule.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<Schedule> scheduleList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return scheduleList;
	}

	@Override
	public Schedule findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(Schedule.getColumnName(Schedule.Columns.ID), false);
		List<String> selectColumnNames = Schedule.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Schedule> scheduleList = select(selectColumnNames, queryTermList, orderByList);
	
	    Schedule schedule = null;
	    
	    if(!scheduleList.isEmpty())
	    {
	    	schedule = scheduleList.get(0);
	    }
	    
	    return schedule;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(Schedule.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(Schedule.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Schedule scheduleModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(Schedule.getColumnName(Schedule.Columns.ID)))
    	{
    		parameters.addValue(parameterName, scheduleModel.getId());
    	}
    	else if(insertColumnName.equals(Schedule.getColumnName(Schedule.Columns.FACULTY_ID)))
    	{
    		parameters.addValue(parameterName, scheduleModel.getFacultyID());
    	}
    	else if(insertColumnName.equals(Schedule.getColumnName(Schedule.Columns.SECTIONS_ID)))
    	{
    		parameters.addValue(parameterName, scheduleModel.getSectionsID());
    	}
    	else if(insertColumnName.equals(Schedule.getColumnName(Schedule.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, scheduleModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(Schedule.getColumnName(Schedule.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, scheduleModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(Schedule.getColumnName(Schedule.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, scheduleModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Schedule scheduleModel)
	{
    	if(keyHolderColumnName.equals(Schedule.getColumnName(Schedule.Columns.ID)))
    	{
    		scheduleModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Schedule.getColumnName(Schedule.Columns.FACULTY_ID)))
    	{
    		scheduleModel.setFacultyID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Schedule.getColumnName(Schedule.Columns.SECTIONS_ID)))
    	{
    		scheduleModel.setSectionsID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Schedule.getColumnName(Schedule.Columns.CREATED_AT)))
    	{
    		scheduleModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Schedule.getColumnName(Schedule.Columns.UPDATED_AT)))
    	{
    		scheduleModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Schedule.getColumnName(Schedule.Columns.DELETED)))
    	{
    		scheduleModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = Schedule.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
