package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.extractor.SectionsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Sections;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SectionsDaoImpl extends BaseDaoImpl<Sections> implements SectionsDao
{
	@Override
	public int insert(Sections sectionsModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Sections.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, sectionsModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, sectionsModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<Sections> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		SectionsExtractor extractor = new SectionsExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Sections.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<Sections> sectionsList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return sectionsList;
	}

	@Override
	public Sections findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(Sections.getColumnName(Sections.Columns.ID), false);
		List<String> selectColumnNames = Sections.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Sections> sectionsList = select(selectColumnNames, queryTermList, orderByList);
	
	    Sections sections = null;
	    
	    if(!sectionsList.isEmpty())
	    {
	    	sections = sectionsList.get(0);
	    }
	    
	    return sections;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(Sections.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(Sections.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Sections sectionsModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.ID)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getId());
    	}
    	else if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.NAME)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getName());
    	}
    	else if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.CRN)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getCrn());
    	}
    	else if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.TERMS_ID)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getTermsID());
    	}
    	else if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.SECTION_TYPE_ID)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getSectionTypeID());
    	}
    	else if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.DAYS_ID)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getDaysID());
    	}
    	else if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.COURSES_ID)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getCoursesID());
    	}
    	else if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.START_ID)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getStartID());
    	}
    	else if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.END_ID)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getEndID());
    	}
    	else if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(Sections.getColumnName(Sections.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, sectionsModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Sections sectionsModel)
	{
    	if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.ID)))
    	{
    		sectionsModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.NAME)))
    	{
    		sectionsModel.setName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.CRN)))
    	{
    		sectionsModel.setCrn((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.TERMS_ID)))
    	{
    		sectionsModel.setTermsID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.SECTION_TYPE_ID)))
    	{
    		sectionsModel.setSectionTypeID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.DAYS_ID)))
    	{
    		sectionsModel.setDaysID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.COURSES_ID)))
    	{
    		sectionsModel.setCoursesID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.START_ID)))
    	{
    		sectionsModel.setStartID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.END_ID)))
    	{
    		sectionsModel.setEndID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.CREATED_AT)))
    	{
    		sectionsModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.UPDATED_AT)))
    	{
    		sectionsModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(Sections.getColumnName(Sections.Columns.DELETED)))
    	{
    		sectionsModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = Sections.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
