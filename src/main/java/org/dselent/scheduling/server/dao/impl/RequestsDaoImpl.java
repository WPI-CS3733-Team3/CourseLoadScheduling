package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.RequestsDao;
import org.dselent.scheduling.server.extractor.RequestsExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Request;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RequestsDaoImpl extends BaseDaoImpl<Request> implements RequestsDao
{
	@Override
	public int insert(Request requestsModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{

		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(Request.TABLE_NAME, insertColumnNameList);
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		List<Map<String, Object>> keyList = new ArrayList<>();
		KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

		for(String insertColumnName : insertColumnNameList)
		{
			addParameterMapValue(parameters, insertColumnName, requestsModel);
		}
		// new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
		// insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));

		int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

		Map<String, Object> keyMap = keyHolder.getKeys();

		for(String keyHolderColumnName : keyHolderColumnNameList)
		{
			addObjectValue(keyMap, keyHolderColumnName, requestsModel);
		}

		return rowsAffected;

	}


	@Override
	public List<Request> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		RequestsExtractor extractor = new RequestsExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(Request.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		List<Request> requestsList = jdbcTemplate.query(queryTemplate, extractor, parameters);

		return requestsList;
	}

	@Override
	public Request findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(Request.getColumnName(Request.Columns.ID), false);
		List<String> selectColumnNames = Request.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<Request> requestsList = select(selectColumnNames, queryTermList, orderByList);

		Request requests = null;

		if(!requestsList.isEmpty())
		{
			requests = requestsList.get(0);
		}

		return requests;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(Request.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(Request.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, Request requestsModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		// Wish this could generalize
		// The getter must be distinguished unless the models are designed as simply a map of columns-values
		// Would prefer not being that generic since it may end up leading to all code being collections of strings

		if(insertColumnName.equals(Request.getColumnName(Request.Columns.ID)))
		{
			parameters.addValue(parameterName, requestsModel.getId());
		}
		else if(insertColumnName.equals(Request.getColumnName(Request.Columns.USERS_ID)))
		{
			parameters.addValue(parameterName, requestsModel.getUserId());
		}
		else if(insertColumnName.equals(Request.getColumnName(Request.Columns.REQUEST_STATUS_ID)))
		{
			parameters.addValue(parameterName, requestsModel.getStatusId());
		}
		else if(insertColumnName.equals(Request.getColumnName(Request.Columns.CREATED_AT)))
		{
			parameters.addValue(parameterName, requestsModel.getCreatedAt());
		}
		else if(insertColumnName.equals(Request.getColumnName(Request.Columns.UPDATED_AT)))
		{
			parameters.addValue(parameterName, requestsModel.getUpdatedAt());
		}
		else if(insertColumnName.equals(Request.getColumnName(Request.Columns.DELETED)))
		{
			parameters.addValue(parameterName, requestsModel.getDeleted());
		}
		else
		{
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, Request requestsModel)
	{
		if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.ID)))
		{
			requestsModel.setId((Integer) keyMap.get(keyHolderColumnName));
		}
		else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.USERS_ID)))
		{
			requestsModel.setUserId((Integer) keyMap.get(keyHolderColumnName));
		}
		else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.REQUEST_STATUS_ID)))
		{
			requestsModel.setStatusId((Integer) keyMap.get(keyHolderColumnName));
		}
		else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.CREATED_AT)))
		{
			requestsModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		}
		else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.UPDATED_AT)))
		{
			requestsModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		}
		else if(keyHolderColumnName.equals(Request.getColumnName(Request.Columns.DELETED)))
		{
			requestsModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = Request.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);

		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);

			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
