package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.RequestStatusDao;
import org.dselent.scheduling.server.extractor.RequestStatusExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.RequestStatus;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RequestStatusDaoImpl extends BaseDaoImpl<RequestStatus> implements RequestStatusDao
{
	@Override
	public int insert(RequestStatus requestStatusModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{

		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(RequestStatus.TABLE_NAME, insertColumnNameList);
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		List<Map<String, Object>> keyList = new ArrayList<>();
		KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

		for(String insertColumnName : insertColumnNameList)
		{
			addParameterMapValue(parameters, insertColumnName, requestStatusModel);
		}
		// new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
		// insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));

		int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

		Map<String, Object> keyMap = keyHolder.getKeys();

		for(String keyHolderColumnName : keyHolderColumnNameList)
		{
			addObjectValue(keyMap, keyHolderColumnName, requestStatusModel);
		}

		return rowsAffected;

	}


	@Override
	public List<RequestStatus> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		RequestStatusExtractor extractor = new RequestStatusExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(RequestStatus.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		List<RequestStatus> requestStatusList = jdbcTemplate.query(queryTemplate, extractor, parameters);

		return requestStatusList;
	}

	@Override
	public RequestStatus findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(RequestStatus.getColumnName(RequestStatus.Columns.ID), false);
		List<String> selectColumnNames = RequestStatus.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<RequestStatus> requestStatusList = select(selectColumnNames, queryTermList, orderByList);

		RequestStatus requestStatus = null;

		if(!requestStatusList.isEmpty())
		{
			requestStatus = requestStatusList.get(0);
		}

		return requestStatus;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(RequestStatus.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(RequestStatus.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, RequestStatus requestStatusModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		// Wish this could generalize
		// The getter must be distinguished unless the models are designed as simply a map of columns-values
		// Would prefer not being that generic since it may end up leading to all code being collections of strings

		if(insertColumnName.equals(RequestStatus.getColumnName(RequestStatus.Columns.ID)))
		{
			parameters.addValue(parameterName, requestStatusModel.getId());
		}
		else if(insertColumnName.equals(RequestStatus.getColumnName(RequestStatus.Columns.STATUS)))
		{
			parameters.addValue(parameterName, requestStatusModel.getStatus());
		}
		else
		{
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, RequestStatus requestStatusModel)
	{
		if(keyHolderColumnName.equals(RequestStatus.getColumnName(RequestStatus.Columns.ID)))
		{
			requestStatusModel.setId((Integer) keyMap.get(keyHolderColumnName));
		}
		else if(keyHolderColumnName.equals(RequestStatus.getColumnName(RequestStatus.Columns.STATUS)))
		{
			requestStatusModel.setStatus((String) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = RequestStatus.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);

		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);

			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
