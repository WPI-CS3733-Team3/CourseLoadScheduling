package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.SectionsHistoryDao;
import org.dselent.scheduling.server.extractor.SectionsHistoryExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.SectionsHistory;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SectionsHistoryDaoImpl extends BaseDaoImpl<SectionsHistory> implements SectionsHistoryDao{
	@Override
	public int insert(SectionsHistory sectionsHistoryModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(SectionsHistory.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList)
	    {
	    	addParameterMapValue(parameters, insertColumnName, sectionsHistoryModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList)
	    {
	    	addObjectValue(keyMap, keyHolderColumnName, sectionsHistoryModel);
	    }
	    	    
	    return rowsAffected;
		
	}
	
	
	@Override
	public List<SectionsHistory> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		SectionsHistoryExtractor extractor = new SectionsHistoryExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(SectionsHistory.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<SectionsHistory> sectionsList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return sectionsList;
	}

	@Override
	public SectionsHistory findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(SectionsHistory.getColumnName(SectionsHistory.Columns.ID), false);
		List<String> selectColumnNames = SectionsHistory.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<SectionsHistory> sectionsList = select(selectColumnNames, queryTermList, orderByList);
	
	    SectionsHistory sections = null;
	    
	    if(!sectionsList.isEmpty())
	    {
	    	sections = sectionsList.get(0);
	    }
	    
	    return sections;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(SectionsHistory.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(SectionsHistory.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, SectionsHistory sectionsHistoryModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.ID)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getId());
    	}
    	else if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.NAME)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getName());
    	}
    	else if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.CRN)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getCrn());
    	}
    	else if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.TERMS_ID)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getTermsID());
    	}
    	else if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.SECTION_TYPE_ID)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getSectionTypeID());
    	}
    	else if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.DAYS_ID)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getDaysID());
    	}
    	else if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.COURSES_ID)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getCoursesID());
    	}
    	else if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.START_ID)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getStartID());
    	}
    	else if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.END_ID)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getEndID());
    	}
    	else if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.CREATED_AT)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getCreatedAt());
    	}
    	else if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.UPDATED_AT)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getUpdatedAt());
    	}
    	else if(insertColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.DELETED)))
    	{
    		parameters.addValue(parameterName, sectionsHistoryModel.getDeleted());
    	}
    	else
    	{
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, SectionsHistory sectionsHistoryModel)
	{
    	if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.ID)))
    	{
    		sectionsHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.NAME)))
    	{
    		sectionsHistoryModel.setName((String) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.CRN)))
    	{
    		sectionsHistoryModel.setCrn((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.TERMS_ID)))
    	{
    		sectionsHistoryModel.setTermsID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.SECTION_TYPE_ID)))
    	{
    		sectionsHistoryModel.setSectionTypeID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.DAYS_ID)))
    	{
    		sectionsHistoryModel.setDaysID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.COURSES_ID)))
    	{
    		sectionsHistoryModel.setCoursesID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.START_ID)))
    	{
    		sectionsHistoryModel.setStartID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.END_ID)))
    	{
    		sectionsHistoryModel.setEndID((Integer) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.CREATED_AT)))
    	{
    		sectionsHistoryModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.UPDATED_AT)))
    	{
    		sectionsHistoryModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	}
    	else if(keyHolderColumnName.equals(SectionsHistory.getColumnName(SectionsHistory.Columns.DELETED)))
    	{
    		sectionsHistoryModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = SectionsHistory.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}

}
