package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.FacultyHistoryDao;
import org.dselent.scheduling.server.extractor.FacultyHistoryExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.FacultyHistory;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class FacultyHistoryDaoImpl extends BaseDaoImpl<FacultyHistory> implements FacultyHistoryDao{
	@Override
	public int insert(FacultyHistory facultyHistoryModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException {
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(FacultyHistory.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList) {
	    	addParameterMapValue(parameters, insertColumnName, facultyHistoryModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList) {
	    	addObjectValue(keyMap, keyHolderColumnName, facultyHistoryModel);
	    }
	    	    
	    return rowsAffected;
		
	}

	@Override
	public List<FacultyHistory> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		FacultyHistoryExtractor extractor = new FacultyHistoryExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(FacultyHistory.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<FacultyHistory> facultyHistoryList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return facultyHistoryList;
	}

	@Override
	public FacultyHistory findById(int id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(FacultyHistory.getColumnName(FacultyHistory.Columns.ID), false);
		List<String> selectColumnNames = FacultyHistory.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<FacultyHistory> facultyHistoryList = select(selectColumnNames, queryTermList, orderByList);
	
	    FacultyHistory facultyHistory = null;
	    
	    if(!facultyHistoryList.isEmpty()) {
	    	facultyHistory = facultyHistoryList.get(0);
	    }
	    
	    return facultyHistory;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) {
		String queryTemplate = QueryStringBuilder.generateUpdateString(FacultyHistory.TABLE_NAME, columnName, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		objectList.add(newValue);
		
		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}
	
	@Override
	public int delete(List<QueryTerm> queryTermList) {
		String queryTemplate = QueryStringBuilder.generateDeleteString(FacultyHistory.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, FacultyHistory facultyHistoryModel) {
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.ID))) {
    		parameters.addValue(parameterName, facultyHistoryModel.getId());
    	} else if(insertColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.FIRST_NAME))) {
    		parameters.addValue(parameterName, facultyHistoryModel.getFirstName());
    	} else if(insertColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.LAST_NAME))) {
    		parameters.addValue(parameterName, facultyHistoryModel.getLastName());
    	} else if(insertColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.EMAIL))) {
    		parameters.addValue(parameterName, facultyHistoryModel.getEmail());
    	} else if(insertColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.FACULTY_TYPE_ID))) {
    		parameters.addValue(parameterName, facultyHistoryModel.getFacultyTypeId());
    	} else if(insertColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.CREATED_AT))) {
    		parameters.addValue(parameterName, facultyHistoryModel.getCreatedAt());
    	} else if(insertColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.UPDATED_AT))) {
    		parameters.addValue(parameterName, facultyHistoryModel.getUpdatedAt());
    	} else if(insertColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.DELETED))) {
    		parameters.addValue(parameterName, facultyHistoryModel.getDeleted());
    	} else {
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, FacultyHistory facultyHistoryModel) {
    	if(keyHolderColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.ID))) {
    		facultyHistoryModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.FIRST_NAME))) {
    		facultyHistoryModel.setFirstName((String) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.LAST_NAME))) {
    		facultyHistoryModel.setLastName((String) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.EMAIL))) {
    		facultyHistoryModel.setEmail((String) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.FACULTY_TYPE_ID))) {
    		facultyHistoryModel.setFacultyTypeId((Integer) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.CREATED_AT))) {
    		facultyHistoryModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.UPDATED_AT))) {
    		facultyHistoryModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(FacultyHistory.getColumnName(FacultyHistory.Columns.DELETED))){
    		facultyHistoryModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
    	} else {
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
    	}
	}
	
	@Override
	public void validateColumnNames(List<String> columnNameList) {
		List<String> actualColumnNames = FacultyHistory.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid) {
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
