package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.Group3UsersDao;
import org.dselent.scheduling.server.extractor.Group3UsersExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Group3User;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
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
public class Group3UsersDaoImpl extends BaseDaoImpl<Group3User> implements Group3UsersDao
{
	@Override
	public int insert(Group3User userModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Group3User.TABLE_NAME, insertColumnNameList);
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
	public List<Group3User> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		Group3UsersExtractor extractor = new Group3UsersExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Group3User.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<Group3User> usersList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return usersList;
	}

	@Override
	public Group3User findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(Group3User.getColumnName(Group3User.Columns.ID), false);
		List<String> selectColumnNames = Group3User.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Group3User> usersList = select(selectColumnNames, queryTermList, orderByList);
	
	    Group3User user = null;
	    
	    if(!usersList.isEmpty())
	    {
	    	user = usersList.get(0);
	    }
	    
	    return user;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(Group3User.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(Group3User.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Group3User userModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(Group3User.getColumnName(Group3User.Columns.ID)))
    	{
    		parameters.addValue(parameterName, userModel.getId());
    	}
    	else if(insertColumnName.equals(Group3User.getColumnName(Group3User.Columns.ACCOUNT_TYPE_ID)))
    	{
    		parameters.addValue(parameterName, userModel.getAccountTypeId());
    	}
    	else if(insertColumnName.equals(Group3User.getColumnName(Group3User.Columns.FACULTY_ID)))
    	{
    		parameters.addValue(parameterName, userModel.getFacultyId());
    	}
    	else if(insertColumnName.equals(Group3User.getColumnName(Group3User.Columns.ENCRYPTED_PASSWORD)))
    	{
    		parameters.addValue(parameterName, userModel.getEncryptedPassword());
    	}
    	else if(insertColumnName.equals(Group3User.getColumnName(Group3User.Columns.PASSWORD_SALT)))
    	{
    		parameters.addValue(parameterName, userModel.getPasswordSalt());
    	}
    	else if(insertColumnName.equals(Group3User.getColumnName(Group3User.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, userModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(Group3User.getColumnName(Group3User.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, userModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(Group3User.getColumnName(Group3User.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, userModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Group3User userModel)
	{
    	if(keyHolderColumnName.equals(Group3User.getColumnName(Group3User.Columns.ID)))
    	{
    		userModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Group3User.getColumnName(Group3User.Columns.ACCOUNT_TYPE_ID)))
    	{
    		userModel.setAccountTypeId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Group3User.getColumnName(Group3User.Columns.FACULTY_ID)))
    	{
    		userModel.setFacultyId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Group3User.getColumnName(Group3User.Columns.ENCRYPTED_PASSWORD)))
    	{
    		userModel.setEncryptedPassword((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Group3User.getColumnName(Group3User.Columns.PASSWORD_SALT)))
    	{
    		userModel.setPasswordSalt((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Group3User.getColumnName(Group3User.Columns.CREATED_AT)))
    	{
    		userModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Group3User.getColumnName(Group3User.Columns.UPDATED_AT)))
    	{
    		userModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Group3User.getColumnName(Group3User.Columns.DELETED)))
    	{
    		userModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = Group3User.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
