package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.RequestTimeDao;
import org.dselent.scheduling.server.extractor.RequestTimeExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.RequestTime;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RequestTimeDaoImpl extends BaseDaoImpl<RequestTime> implements RequestTimeDao
{
	@Override
	public int insert(RequestTime requestTimeModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(RequestTime.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, requestTimeModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, requestTimeModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<RequestTime> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		RequestTimeExtractor extractor = new RequestTimeExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(RequestTime.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<RequestTime> requestTimeList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return requestTimeList;
	}

	@Override
	public RequestTime findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(RequestTime.getColumnName(RequestTime.Columns.ID), false);
		List<String> selectColumnNames = RequestTime.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<RequestTime> requestTimeList = select(selectColumnNames, queryTermList, orderByList);
	
		RequestTime requestTime = null;
	    
	    if(!requestTimeList.isEmpty())
	    {
	    	requestTime = requestTimeList.get(0);
	    }
	    
	    return requestTime;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(RequestTime.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(RequestTime.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, RequestTime requestTimeModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.ID)))
    	{
    		parameters.addValue(parameterName, requestTimeModel.getId());
    	}
    	else if(insertColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.REQUESTS_ID)))
    	{
    		parameters.addValue(parameterName, requestTimeModel.getRequestsID());
    	}
    	else if(insertColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.START_ID)))
    	{
    		parameters.addValue(parameterName, requestTimeModel.getStartID());
    	}
    	else if(insertColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.END_ID)))
    	{
    		parameters.addValue(parameterName, requestTimeModel.getEndID());
    	}
    	else if(insertColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, requestTimeModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, requestTimeModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, requestTimeModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, RequestTime requestTimeModel)
	{
    	if(keyHolderColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.ID)))
    	{
    		requestTimeModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.REQUESTS_ID)))
    	{
    		requestTimeModel.setRequestsID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.START_ID)))
    	{
    		requestTimeModel.setStartID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.END_ID)))
    	{
    		requestTimeModel.setEndID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.CREATED_AT)))
    	{
    		requestTimeModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.UPDATED_AT)))
    	{
    		requestTimeModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestTime.getColumnName(RequestTime.Columns.DELETED)))
    	{
    		requestTimeModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = RequestTime.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
