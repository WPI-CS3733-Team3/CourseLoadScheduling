package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.CourseLoadTypeDao;
import org.dselent.scheduling.server.extractor.CourseLoadTypeExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.CourseLoadType;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseLoadTypeDaoImpl extends BaseDaoImpl<CourseLoadType> implements CourseLoadTypeDao {

	@Override
	public int insert(CourseLoadType courseLoadTypeModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList)
			throws SQLException {

		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(CourseLoadType.TABLE_NAME, insertColumnNameList);
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		List<Map<String, Object>> keyList = new ArrayList<>();
		KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

		for(String insertColumnName : insertColumnNameList) {
			addParameterMapValue(parameters, insertColumnName, courseLoadTypeModel);
		}
		// new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
		// insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));

		int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

		Map<String, Object> keyMap = keyHolder.getKeys();

		for(String keyHolderColumnName : keyHolderColumnNameList) {
			addObjectValue(keyMap, keyHolderColumnName, courseLoadTypeModel);
		}

		return rowsAffected;

	}

	@Override
	public List<CourseLoadType> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		CourseLoadTypeExtractor extractor = new CourseLoadTypeExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(CourseLoadType.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		List<CourseLoadType> CourseLoadTypeList = jdbcTemplate.query(queryTemplate, extractor, parameters);

		return CourseLoadTypeList;
	}

	@Override
	public CourseLoadType findById(int id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(CourseLoadType.getColumnName(CourseLoadType.Columns.ID), false);
		List<String> selectColumnNames = CourseLoadType.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<CourseLoadType> CourseLoadTypeList = select(selectColumnNames, queryTermList, orderByList);

		CourseLoadType CourseLoadType = null;

		if(!CourseLoadTypeList.isEmpty()) {
			CourseLoadType = CourseLoadTypeList.get(0);
		}

		return CourseLoadType;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) {
		String queryTemplate = QueryStringBuilder.generateUpdateString(CourseLoadType.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(CourseLoadType.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, CourseLoadType CourseLoadTypeModel) {
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		// Wish this could generalize
		// The getter must be distinguished unless the models are designed as simply a map of columns-values
		// Would prefer not being that generic since it may end up leading to all code being collections of strings

		if(insertColumnName.equals(CourseLoadType.getColumnName(CourseLoadType.Columns.ID))) {
			parameters.addValue(parameterName, CourseLoadTypeModel.getId());
		} else if(insertColumnName.equals(CourseLoadType.getColumnName(CourseLoadType.Columns.NAME))) {
			parameters.addValue(parameterName, CourseLoadTypeModel.getName());
		} else {
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, CourseLoadType CourseLoadTypeModel) {
		if(keyHolderColumnName.equals(CourseLoadType.getColumnName(CourseLoadType.Columns.ID))) {
			CourseLoadTypeModel.setId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(CourseLoadType.getColumnName(CourseLoadType.Columns.NAME))) {
			CourseLoadTypeModel.setName((String) keyMap.get(keyHolderColumnName));
		} else {
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
	}

	@Override
	public void validateColumnNames(List<String> columnNameList) {
		List<String> actualColumnNames = CourseLoadType.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);

		if(!valid) {
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);

			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
