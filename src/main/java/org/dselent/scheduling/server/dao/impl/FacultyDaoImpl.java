package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.FacultyDao;
import org.dselent.scheduling.server.extractor.FacultyExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.Faculty;
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
public class FacultyDaoImpl extends BaseDaoImpl<Faculty> implements FacultyDao {
	@Override
	public int insert(Faculty facultyModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException {
		
		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Faculty.TABLE_NAME, insertColumnNameList);
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    
	    List<Map<String, Object>> keyList = new ArrayList<>();
	    KeyHolder keyHolder = new GeneratedKeyHolder(keyList);
	    
	    for(String insertColumnName : insertColumnNameList) {
	    	addParameterMapValue(parameters, insertColumnName, facultyModel);
	    }
	    // new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
	    // insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));
	    
	    int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);
	    
	    Map<String, Object> keyMap = keyHolder.getKeys();
	    
	    for(String keyHolderColumnName : keyHolderColumnNameList) {
	    	addObjectValue(keyMap, keyHolderColumnName, facultyModel);
	    }
	    	    
	    return rowsAffected;
		
	}

	@Override
	public List<Faculty> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		FacultyExtractor extractor = new FacultyExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Faculty.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    List<Faculty> facultyList = jdbcTemplate.query(queryTemplate, extractor, parameters);
	    
	    return facultyList;
	}

	@Override
	public Faculty findById(int id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(Faculty.getColumnName(Faculty.Columns.ID), false);
		List<String> selectColumnNames = Faculty.getColumnNameList();
		
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);
		
		List<Faculty> facultyList = select(selectColumnNames, queryTermList, orderByList);
	
	    Faculty faculty = null;
	    
	    if(!facultyList.isEmpty()) {
	    	faculty = facultyList.get(0);
	    }
	    
	    return faculty;
	}
	
	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) {
		String queryTemplate = QueryStringBuilder.generateUpdateString(Faculty.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(Faculty.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();
		
		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}
		
	    Object[] parameters = objectList.toArray();
		 
	    int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);
	    
		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Faculty facultyModel) {
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);
    	
    	// Wish this could generalize
    	// The getter must be distinguished unless the models are designed as simply a map of columns-values
    	// Would prefer not being that generic since it may end up leading to all code being collections of strings
		
    	if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.ID))) {
    		parameters.addValue(parameterName, facultyModel.getId());
    	} else if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.FIRST_NAME))) {
    		parameters.addValue(parameterName, facultyModel.getFirstName());
    	} else if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.LAST_NAME))) {
    		parameters.addValue(parameterName, facultyModel.getLastName());
    	} else if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.EMAIL))) {
    		parameters.addValue(parameterName, facultyModel.getEmail());
    	} else if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.FACULTY_TYPE_ID))) {
    		parameters.addValue(parameterName, facultyModel.getFacultyTypeId());
    	} else if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.CREATED_AT))) {
    		parameters.addValue(parameterName, facultyModel.getCreatedAt());
    	} else if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.UPDATED_AT))) {
    		parameters.addValue(parameterName, facultyModel.getUpdatedAt());
    	} else if(insertColumnName.equals(Faculty.getColumnName(Faculty.Columns.DELETED))) {
    		parameters.addValue(parameterName, facultyModel.getDeleted());
    	} else {
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
    	}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Faculty facultyModel) {
    	if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.ID))) {
    		facultyModel.setId((Integer) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.FIRST_NAME))) {
    		facultyModel.setFirstName((String) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.LAST_NAME))) {
    		facultyModel.setLastName((String) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.EMAIL))) {
    		facultyModel.setEmail((String) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.FACULTY_TYPE_ID))) {
    		facultyModel.setFacultyTypeId((Integer) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.CREATED_AT))) {
    		facultyModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.UPDATED_AT))) {
    		facultyModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
    	} else if(keyHolderColumnName.equals(Faculty.getColumnName(Faculty.Columns.DELETED))){
    		facultyModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
    	} else {
    		// should never end up here
    		// lists should have already been validated
    		throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
    	}
	}
	
	@Override
	public void validateColumnNames(List<String> columnNameList) {
		List<String> actualColumnNames = Faculty.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);
		
		if(!valid) {
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);
			
			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
