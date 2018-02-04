package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.ExampleUsersDao;
import org.dselent.scheduling.server.extractor.ExampleUsersExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.ExampleUser;
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
public class ExampleUsersDaoImpl extends BaseDaoImpl<ExampleUser> implements ExampleUsersDao
{
	@Override
	public int insert(ExampleUser userModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(ExampleUser.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, userModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, userModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<ExampleUser> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		ExampleUsersExtractor extractor = new ExampleUsersExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(ExampleUser.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<ExampleUser> usersList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return usersList;
	}

	@Override
	public ExampleUser findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(ExampleUser.getColumnName(ExampleUser.Columns.ID), false);
		List<String> selectColumnNames = ExampleUser.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<ExampleUser> usersList = select(selectColumnNames, queryTermList, orderByList);
	
	    ExampleUser user = null;
	    
	    if(!usersList.isEmpty())
	    {
	    	user = usersList.get(0);
	    }
	    
	    return user;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(ExampleUser.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(ExampleUser.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, ExampleUser userModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.ID)))
    	{
    		parameters.addValue(parameterName, userModel.getId());
    	}
    	else if(insertColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.USER_NAME)))
    	{
    		parameters.addValue(parameterName, userModel.getUserName());
    	}
    	else if(insertColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.FIRST_NAME)))
    	{
    		parameters.addValue(parameterName, userModel.getFirstName());
    	}
    	else if(insertColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.LAST_NAME)))
    	{
    		parameters.addValue(parameterName, userModel.getLastName());
    	}
    	else if(insertColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.EMAIL)))
    	{
    		parameters.addValue(parameterName, userModel.getEmail());
    	}
    	else if(insertColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.ENCRYPTED_PASSWORD)))
    	{
    		parameters.addValue(parameterName, userModel.getEncryptedPassword());
    	}
    	else if(insertColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.SALT)))
    	{
    		parameters.addValue(parameterName, userModel.getSalt());
    	}
    	else if(insertColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.USER_STATE_ID)))
    	{
    		parameters.addValue(parameterName, userModel.getUserStateId());
    	}
    	else if(insertColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, userModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, userModel.getUpdatedAt());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, ExampleUser userModel)
	{
    	if(keyHolderColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.ID)))
    	{
    		userModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.USER_NAME)))
    	{
    		userModel.setUserName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.FIRST_NAME)))
    	{
    		userModel.setFirstName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.LAST_NAME)))
    	{
    		userModel.setLastName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.EMAIL)))
    	{
    		userModel.setEmail((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.ENCRYPTED_PASSWORD)))
    	{
    		userModel.setEncryptedPassword((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.SALT)))
    	{
    		userModel.setSalt((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.USER_STATE_ID)))
    	{
    		userModel.setUserStateId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.CREATED_AT)))
    	{
    		userModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUser.getColumnName(ExampleUser.Columns.UPDATED_AT)))
    	{
    		userModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = ExampleUser.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}