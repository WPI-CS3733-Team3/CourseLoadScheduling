package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.ExampleUsersRolesLinksDao;
import org.dselent.scheduling.server.extractor.ExampleUsersRolesLinksExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.ExampleUser;
import org.dselent.scheduling.server.model.ExampleUsersRolesLink;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ExampleUsersRolesLinksDaoImpl extends BaseDaoImpl<ExampleUsersRolesLink> implements ExampleUsersRolesLinksDao
{
	@Override
	public int insert(ExampleUsersRolesLink usersRolesLinkModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(ExampleUsersRolesLink.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, usersRolesLinkModel);
	    }

	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, usersRolesLinkModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<ExampleUsersRolesLink> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		ExampleUsersRolesLinksExtractor extractor = new ExampleUsersRolesLinksExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(ExampleUsersRolesLink.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<ExampleUsersRolesLink> usersList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return usersList;
	}

	@Override
	public ExampleUsersRolesLink findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.ID), false);
		List<String> selectColumnNames = ExampleUser.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<ExampleUsersRolesLink> usersRolesLinksList = select(selectColumnNames, queryTermList, orderByList);
	
	    ExampleUsersRolesLink usersRolesLink = null;
	    
	    if(!usersRolesLinksList.isEmpty())
	    {
	    	usersRolesLink = usersRolesLinksList.get(0);
	    }
	    
	    return usersRolesLink;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(ExampleUsersRolesLink.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(ExampleUsersRolesLink.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, ExampleUsersRolesLink usersRolesLinkModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	if(insertColumnName.equals(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.ID)))
    	{
    		parameters.addValue(parameterName, usersRolesLinkModel.getId());
    	}
    	else if(insertColumnName.equals(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.USER_ID)))
    	{
    		parameters.addValue(parameterName, usersRolesLinkModel.getUserId());
    	}
    	else if(insertColumnName.equals(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.ROLE_ID)))
    	{
    		parameters.addValue(parameterName, usersRolesLinkModel.getRoleId());
    	}
    	else if(insertColumnName.equals(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, usersRolesLinkModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, usersRolesLinkModel.isDeleted());
    	}
    	else
    	{
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, ExampleUsersRolesLink usersRolesLinkModel)
	{
    	if(keyHolderColumnName.equals(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.ID)))
    	{
    		usersRolesLinkModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.USER_ID)))
    	{
    		usersRolesLinkModel.setUserId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.ROLE_ID)))
    	{
    		usersRolesLinkModel.setRoleId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.CREATED_AT)))
    	{
    		usersRolesLinkModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.DELETED)))
    	{
    		usersRolesLinkModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
    	}
    	else
    	{
    		throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
    	}
	}
	
	@Override
	public void validateColumnNames(List<String> columnNameList)
	{
		List<String> actualColumnNames = ExampleUsersRolesLink.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
