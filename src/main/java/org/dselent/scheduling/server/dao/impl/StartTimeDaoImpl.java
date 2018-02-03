package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.StartTimeDao;
import org.dselent.scheduling.server.extractor.StartTimeExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.StartTime;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class StartTimeDaoImpl extends BaseDaoImpl<StartTime> implements StartTimeDao {

	@Override
	public int insert(StartTime startTimeModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList)
			throws SQLException {

		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(StartTime.TABLE_NAME, insertColumnNameList);
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		List<Map<String, Object>> keyList = new ArrayList<>();
		KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

		for(String insertColumnName : insertColumnNameList) {
			addParameterMapValue(parameters, insertColumnName, startTimeModel);
		}
		// new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
		// insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));

		int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

		Map<String, Object> keyMap = keyHolder.getKeys();

		for(String keyHolderColumnName : keyHolderColumnNameList) {
			addObjectValue(keyMap, keyHolderColumnName, startTimeModel);
		}

		return rowsAffected;

	}

	@Override
	public List<StartTime> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		StartTimeExtractor extractor = new StartTimeExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(StartTime.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		List<StartTime> StartTimeList = jdbcTemplate.query(queryTemplate, extractor, parameters);

		return StartTimeList;
	}

	@Override
	public StartTime findById(int id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(StartTime.getColumnName(StartTime.Columns.ID), false);
		List<String> selectColumnNames = StartTime.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<StartTime> StartTimeList = select(selectColumnNames, queryTermList, orderByList);

		StartTime StartTime = null;

		if(!StartTimeList.isEmpty()) {
			StartTime = StartTimeList.get(0);
		}

		return StartTime;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) {
		String queryTemplate = QueryStringBuilder.generateUpdateString(StartTime.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(StartTime.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, StartTime StartTimeModel) {
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		// Wish this could generalize
		// The getter must be distinguished unless the models are designed as simply a map of columns-values
		// Would prefer not being that generic since it may end up leading to all code being collections of strings

		if(insertColumnName.equals(StartTime.getColumnName(StartTime.Columns.ID))) {
			parameters.addValue(parameterName, StartTimeModel.getId());
		} else if(insertColumnName.equals(StartTime.getColumnName(StartTime.Columns.TIME))) {
			parameters.addValue(parameterName, StartTimeModel.getTime());
		} else {
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, StartTime StartTimeModel) {
		if(keyHolderColumnName.equals(StartTime.getColumnName(StartTime.Columns.ID))) {
			StartTimeModel.setId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(StartTime.getColumnName(StartTime.Columns.TIME))) {
			StartTimeModel.setTime((Time) keyMap.get(keyHolderColumnName));
		} else {
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
	}

	@Override
	public void validateColumnNames(List<String> columnNameList) {
		List<String> actualColumnNames = StartTime.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);

		if(!valid) {
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);

			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}