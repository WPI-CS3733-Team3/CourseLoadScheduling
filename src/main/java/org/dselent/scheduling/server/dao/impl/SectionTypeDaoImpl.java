package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.SectionTypeDao;
import org.dselent.scheduling.server.extractor.SectionTypeExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.miscellaneous.QueryStringBuilder;
import org.dselent.scheduling.server.model.SectionType;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SectionTypeDaoImpl extends BaseDaoImpl<SectionType> implements SectionTypeDao {

	@Override
	public int insert(SectionType sectionTypeModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList)
			throws SQLException {

		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(SectionType.TABLE_NAME, insertColumnNameList);
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		List<Map<String, Object>> keyList = new ArrayList<>();
		KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

		for(String insertColumnName : insertColumnNameList) {
			addParameterMapValue(parameters, insertColumnName, sectionTypeModel);
		}
		// new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
		// insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));

		int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

		Map<String, Object> keyMap = keyHolder.getKeys();

		for(String keyHolderColumnName : keyHolderColumnNameList) {
			addObjectValue(keyMap, keyHolderColumnName, sectionTypeModel);
		}

		return rowsAffected;

	}

	@Override
	public List<SectionType> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException {
		SectionTypeExtractor extractor = new SectionTypeExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(SectionType.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		List<SectionType> SectionTypeList = jdbcTemplate.query(queryTemplate, extractor, parameters);

		return SectionTypeList;
	}

	@Override
	public SectionType findById(int id) throws SQLException {
		String columnName = QueryStringBuilder.convertColumnName(SectionType.getColumnName(SectionType.Columns.ID), false);
		List<String> selectColumnNames = SectionType.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<SectionType> SectionTypeList = select(selectColumnNames, queryTermList, orderByList);

		SectionType SectionType = null;

		if(!SectionTypeList.isEmpty()) {
			SectionType = SectionTypeList.get(0);
		}

		return SectionType;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList) {
		String queryTemplate = QueryStringBuilder.generateUpdateString(SectionType.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(SectionType.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList) {
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, SectionType SectionTypeModel) {
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		// Wish this could generalize
		// The getter must be distinguished unless the models are designed as simply a map of columns-values
		// Would prefer not being that generic since it may end up leading to all code being collections of strings

		if(insertColumnName.equals(SectionType.getColumnName(SectionType.Columns.ID))) {
			parameters.addValue(parameterName, SectionTypeModel.getId());
		} else if(insertColumnName.equals(SectionType.getColumnName(SectionType.Columns.TYPE))) {
			parameters.addValue(parameterName, SectionTypeModel.getType());
		} else {
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, SectionType SectionTypeModel) {
		if(keyHolderColumnName.equals(SectionType.getColumnName(SectionType.Columns.ID))) {
			SectionTypeModel.setId((Integer) keyMap.get(keyHolderColumnName));
		} else if(keyHolderColumnName.equals(SectionType.getColumnName(SectionType.Columns.TYPE))) {
			SectionTypeModel.setType((String) keyMap.get(keyHolderColumnName));
		} else {
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + keyHolderColumnName);
		}
	}

	@Override
	public void validateColumnNames(List<String> columnNameList) {
		List<String> actualColumnNames = SectionType.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);

		if(!valid) {
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);

			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
