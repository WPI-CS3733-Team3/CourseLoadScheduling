package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.ScheduleHistoryDao;
import org.dselent.scheduling.server.extractor.ScheduleHistoryExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.ScheduleHistory;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleHistoryDaoImpl extends BaseDaoImpl<ScheduleHistory> implements ScheduleHistoryDao{
	@Override
	public int insert(ScheduleHistory scheduleHistoryModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(ScheduleHistory.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, scheduleHistoryModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, scheduleHistoryModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<ScheduleHistory> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		ScheduleHistoryExtractor extractor = new ScheduleHistoryExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(ScheduleHistory.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<ScheduleHistory> scheduleHistoryList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return scheduleHistoryList;
	}

	@Override
	public ScheduleHistory findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(ScheduleHistory.getColumnName(ScheduleHistory.Columns.ID), false);
		List<String> selectColumnNames = ScheduleHistory.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<ScheduleHistory> scheduleHistoryList = select(selectColumnNames, queryTermList, orderByList);
	
	    ScheduleHistory scheduleHistory = null;
	    
	    if(!scheduleHistoryList.isEmpty())
	    {
	    	scheduleHistory = scheduleHistoryList.get(0);
	    }
	    
	    return scheduleHistory;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(ScheduleHistory.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(ScheduleHistory.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, ScheduleHistory scheduleHistoryModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.ID)))
    	{
    		parameters.addValue(parameterName, scheduleHistoryModel.getId());
    	}
    	else if(insertColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.FACULTY_ID)))
    	{
    		parameters.addValue(parameterName, scheduleHistoryModel.getFacultyID());
    	}
    	else if(insertColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.SECTIONS_ID)))
    	{
    		parameters.addValue(parameterName, scheduleHistoryModel.getSectionsID());
    	}
    	else if(insertColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, scheduleHistoryModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, scheduleHistoryModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, scheduleHistoryModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, ScheduleHistory scheduleHistoryModel)
	{
    	if(keyHolderColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.ID)))
    	{
    		scheduleHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.FACULTY_ID)))
    	{
    		scheduleHistoryModel.setFacultyID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.SECTIONS_ID)))
    	{
    		scheduleHistoryModel.setSectionsID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.CREATED_AT)))
    	{
    		scheduleHistoryModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.UPDATED_AT)))
    	{
    		scheduleHistoryModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ScheduleHistory.getColumnName(ScheduleHistory.Columns.DELETED)))
    	{
    		scheduleHistoryModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = ScheduleHistory.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
