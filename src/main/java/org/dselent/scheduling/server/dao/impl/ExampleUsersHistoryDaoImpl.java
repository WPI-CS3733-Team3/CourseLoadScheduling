package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.ExampleUsersHistoryDao;
import org.dselent.scheduling.server.extractor.ExampleUsersHistoryExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.ExampleUsersHistory;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


/*
 * @Repository annotation
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Repository.html
 * 
 * Useful link
 * https://howtodoinjava.com/spring/spring-core/how-to-use-spring-component-repository-service-and-controller-annotations/
 */
@Repository
public class ExampleUsersHistoryDaoImpl extends BaseDaoImpl<ExampleUsersHistory> implements ExampleUsersHistoryDao
{
	@Override
	public int insert(ExampleUsersHistory usersHistoryModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(ExampleUsersHistory.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, usersHistoryModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, usersHistoryModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<ExampleUsersHistory> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		ExampleUsersHistoryExtractor extractor = new ExampleUsersHistoryExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(ExampleUsersHistory.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<ExampleUsersHistory> usersHistoryList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return usersHistoryList;
	}

	@Override
	public ExampleUsersHistory findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.ID), false);
		List<String> selectColumnNames = ExampleUsersHistory.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<ExampleUsersHistory> usersHistoryList = select(selectColumnNames, queryTermList, orderByList);
	
		ExampleUsersHistory usersHistory = null;
	    
	    if(!usersHistoryList.isEmpty())
	    {
	    	usersHistory = usersHistoryList.get(0);
	    }
	    
	    return usersHistory;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(ExampleUsersHistory.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(ExampleUsersHistory.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, ExampleUsersHistory usersHistoryModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.ID)))
    	{
    		parameters.addValue(parameterName, usersHistoryModel.getId());
    	}
    	else if(insertColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.ACCOUNT_TYPE_ID)))
    	{
    		parameters.addValue(parameterName, usersHistoryModel.getAccountTypeId());
    	}
    	else if(insertColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.FACULTY_ID)))
    	{
    		parameters.addValue(parameterName, usersHistoryModel.getFacultyId());
    	}
    	else if(insertColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.ENCRYPTED_PASSWORD)))
    	{
    		parameters.addValue(parameterName, usersHistoryModel.getEncryptedPassword());
    	}
    	else if(insertColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.PASSWORD_SALT)))
    	{
    		parameters.addValue(parameterName, usersHistoryModel.getPasswordSalt());
    	}
    	else if(insertColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, usersHistoryModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, usersHistoryModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, usersHistoryModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, ExampleUsersHistory usersHistoryModel)
	{
    	if(keyHolderColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.ID)))
    	{
    		usersHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.ACCOUNT_TYPE_ID)))
    	{
    		usersHistoryModel.setAccountTypeId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.FACULTY_ID)))
    	{
    		usersHistoryModel.setFacultyId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.ENCRYPTED_PASSWORD)))
    	{
    		usersHistoryModel.setEncryptedPassword((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.PASSWORD_SALT)))
    	{
    		usersHistoryModel.setPasswordSalt((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.CREATED_AT)))
    	{
    		usersHistoryModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.UPDATED_AT)))
    	{
    		usersHistoryModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.DELETED)))
    	{
    		usersHistoryModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = ExampleUsersHistory.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
