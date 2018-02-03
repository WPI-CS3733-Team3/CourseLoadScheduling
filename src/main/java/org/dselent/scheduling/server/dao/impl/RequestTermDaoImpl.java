package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.RequestTermDao;
import org.dselent.scheduling.server.extractor.RequestTermExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.RequestTerm;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RequestTermDaoImpl extends BaseDaoImpl<RequestTerm> implements RequestTermDao
{
	@Override
	public int insert(RequestTerm requestTermModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(RequestTerm.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, requestTermModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, requestTermModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<RequestTerm> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		RequestTermExtractor extractor = new RequestTermExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(RequestTerm.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<RequestTerm> requestTermList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return requestTermList;
	}

	@Override
	public RequestTerm findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(RequestTerm.getColumnName(RequestTerm.Columns.ID), false);
		List<String> selectColumnNames = RequestTerm.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<RequestTerm> requestTermList = select(selectColumnNames, queryTermList, orderByList);
	
		RequestTerm requestTerm = null;
	    
	    if(!requestTermList.isEmpty())
	    {
	    	requestTerm = requestTermList.get(0);
	    }
	    
	    return requestTerm;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(RequestTerm.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(RequestTerm.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, RequestTerm requestTermModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.ID)))
    	{
    		parameters.addValue(parameterName, requestTermModel.getId());
    	}
    	else if(insertColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.REQUESTS_ID)))
    	{
    		parameters.addValue(parameterName, requestTermModel.getRequestsID());
    	}
    	else if(insertColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.TERMS_ID)))
    	{
    		parameters.addValue(parameterName, requestTermModel.getTermsID());
    	}
    	else if(insertColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, requestTermModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, requestTermModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, requestTermModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, RequestTerm requestTermModel)
	{
    	if(keyHolderColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.ID)))
    	{
    		requestTermModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.REQUESTS_ID)))
    	{
    		requestTermModel.setRequestsID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.TERMS_ID)))
    	{
    		requestTermModel.setTermsID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.CREATED_AT)))
    	{
    		requestTermModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.UPDATED_AT)))
    	{
    		requestTermModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(RequestTerm.getColumnName(RequestTerm.Columns.DELETED)))
    	{
    		requestTermModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = RequestTerm.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
