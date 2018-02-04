package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CoursesHistoryDao;
import org.dselent.scheduling.server.extractor.CoursesHistoryExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CoursesHistory;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CoursesHistoryDaoImpl extends BaseDaoImpl<CoursesHistory> implements CoursesHistoryDao{
	@Override
	public int insert(CoursesHistory coursesHistoryModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(CoursesHistory.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, coursesHistoryModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, coursesHistoryModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<CoursesHistory> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		CoursesHistoryExtractor extractor = new CoursesHistoryExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(CoursesHistory.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<CoursesHistory> coursesHistoryList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return coursesHistoryList;
	}

	@Override
	public CoursesHistory findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(CoursesHistory.getColumnName(CoursesHistory.Columns.ID), false);
		List<String> selectColumnNames = CoursesHistory.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<CoursesHistory> coursesHistoryList = select(selectColumnNames, queryTermList, orderByList);
	
	    CoursesHistory coursesHistory = null;
	    
	    if(!coursesHistoryList.isEmpty())
	    {
	    	coursesHistory = coursesHistoryList.get(0);
	    }
	    
	    return coursesHistory;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(CoursesHistory.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(CoursesHistory.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CoursesHistory coursesHistoryModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.ID)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getId());
    	}
    	else if(insertColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.TITLE)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getTitle());
    	}
    	else if(insertColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.NUMBER)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getNumber());
    	}
    	else if(insertColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.FREQUENCY_ID)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getFrequencyID());
    	}
    	else if(insertColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName,coursesHistoryModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, coursesHistoryModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CoursesHistory coursesHistoryModel)
	{
    	if(keyHolderColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.ID)))
    	{
    		coursesHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.TITLE)))
    	{
    		coursesHistoryModel.setTitle((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.NUMBER)))
    	{
    		coursesHistoryModel.setNumber((String) keyMap.get(keyHolderColumnName));
    	}else if(keyHolderColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.FREQUENCY_ID)))
    	{
    		coursesHistoryModel.setFrequencyID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.CREATED_AT)))
    	{
    		coursesHistoryModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.UPDATED_AT)))
    	{
    		coursesHistoryModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(CoursesHistory.getColumnName(CoursesHistory.Columns.DELETED)))
    	{
    		coursesHistoryModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = CoursesHistory.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
	
}
