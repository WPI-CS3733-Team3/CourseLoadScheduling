package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.extractor.CoursesExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Courses;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CoursesDaoImpl extends BaseDaoImpl<Courses> implements CoursesDao
{
	@Override
	public int insert(Courses coursesModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Courses.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, coursesModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, coursesModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<Courses> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		CoursesExtractor extractor = new CoursesExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Courses.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<Courses> coursesList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return coursesList;
	}

	@Override
	public Courses findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(Courses.getColumnName(Courses.Columns.ID), false);
		List<String> selectColumnNames = Courses.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Courses> coursesList = select(selectColumnNames, queryTermList, orderByList);
	
	    Courses courses = null;
	    
	    if(!coursesList.isEmpty())
	    {
	    	courses = coursesList.get(0);
	    }
	    
	    return courses;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(Courses.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(Courses.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Courses coursesModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(Courses.getColumnName(Courses.Columns.ID)))
    	{
    		parameters.addValue(parameterName, coursesModel.getId());
    	}
    	else if(insertColumnName.equals(Courses.getColumnName(Courses.Columns.TITLE)))
    	{
    		parameters.addValue(parameterName, coursesModel.getTitle());
    	}
    	else if(insertColumnName.equals(Courses.getColumnName(Courses.Columns.NUMBER)))
    	{
    		parameters.addValue(parameterName, coursesModel.getNumber());
    	}
    	else if(insertColumnName.equals(Courses.getColumnName(Courses.Columns.FREQUENCY_ID)))
    	{
    		parameters.addValue(parameterName, coursesModel.getFrequencyID());
    	}
    	else if(insertColumnName.equals(Courses.getColumnName(Courses.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName,coursesModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(Courses.getColumnName(Courses.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, coursesModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(Courses.getColumnName(Courses.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, coursesModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Courses coursesModel)
	{
    	if(keyHolderColumnName.equals(Courses.getColumnName(Courses.Columns.ID)))
    	{
    		coursesModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Courses.getColumnName(Courses.Columns.TITLE)))
    	{
    		coursesModel.setTitle((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Courses.getColumnName(Courses.Columns.NUMBER)))
    	{
    		coursesModel.setNumber((String) keyMap.get(keyHolderColumnName));
    	}else if(keyHolderColumnName.equals(Courses.getColumnName(Courses.Columns.FREQUENCY_ID)))
    	{
    		coursesModel.setFrequencyID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Courses.getColumnName(Courses.Columns.CREATED_AT)))
    	{
    		coursesModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Courses.getColumnName(Courses.Columns.UPDATED_AT)))
    	{
    		coursesModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Courses.getColumnName(Courses.Columns.DELETED)))
    	{
    		coursesModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = Courses.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
