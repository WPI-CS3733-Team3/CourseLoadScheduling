package org.dselent.scheduling.server.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.dao.RequestOtherDao;
import org.dselent.scheduling.server.extractor.RequestOtherExtractor;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.RequestOther;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryStringBuilder;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RequestOtherDaoImpl extends BaseDaoImpl<RequestOther> implements RequestOtherDao
{
	@Override
	public int insert(RequestOther requestOtherModel, List<String> insertColumnNameList, List<String> keyHolderColumnNameList) throws SQLException
	{

		validateColumnNames(insertColumnNameList);
		validateColumnNames(keyHolderColumnNameList);

		String queryTemplate = QueryStringBuilder.generateInsertString(RequestOther.TABLE_NAME, insertColumnNameList);
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		List<Map<String, Object>> keyList = new ArrayList<>();
		KeyHolder keyHolder = new GeneratedKeyHolder(keyList);

		for(String insertColumnName : insertColumnNameList)
		{
			addParameterMapValue(parameters, insertColumnName, requestOtherModel);
		}
		// new way, but unfortunately unnecessary class creation is slow and wasteful (i.e. wrong)
		// insertColumnNames.forEach(insertColumnName -> addParameterMap(parameters, insertColumnName, userModel));

		int rowsAffected = namedParameterJdbcTemplate.update(queryTemplate, parameters, keyHolder);

		Map<String, Object> keyMap = keyHolder.getKeys();

		for(String keyHolderColumnName : keyHolderColumnNameList)
		{
			addObjectValue(keyMap, keyHolderColumnName, requestOtherModel);
		}

		return rowsAffected;

	}


	@Override
	public List<RequestOther> select(List<String> selectColumnNameList, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList) throws SQLException
	{
		RequestOtherExtractor extractor = new RequestOtherExtractor();
		String queryTemplate = QueryStringBuilder.generateSelectString(RequestOther.TABLE_NAME, selectColumnNameList, queryTermList, orderByList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		List<RequestOther> requestOtherList = jdbcTemplate.query(queryTemplate, extractor, parameters);

		return requestOtherList;
	}

	@Override
	public RequestOther findById(int id) throws SQLException
	{
		String columnName = QueryStringBuilder.convertColumnName(RequestOther.getColumnName(RequestOther.Columns.ID), false);
		List<String> selectColumnNames = RequestOther.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm idTerm = new QueryTerm(columnName, ComparisonOperator.EQUAL, id, null);
		queryTermList.add(idTerm);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> order = new Pair<String, ColumnOrder>(columnName, ColumnOrder.ASC);
		orderByList.add(order);

		List<RequestOther> requestOtherList = select(selectColumnNames, queryTermList, orderByList);

		RequestOther requestOther = null;

		if(!requestOtherList.isEmpty())
		{
			requestOther = requestOtherList.get(0);
		}

		return requestOther;
	}

	@Override
	public int update(String columnName, Object newValue, List<QueryTerm> queryTermList)
	{
		String queryTemplate = QueryStringBuilder.generateUpdateString(RequestOther.TABLE_NAME, columnName, queryTermList);

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
		String queryTemplate = QueryStringBuilder.generateDeleteString(RequestOther.TABLE_NAME, queryTermList);

		List<Object> objectList = new ArrayList<Object>();

		for(QueryTerm queryTerm : queryTermList)
		{
			objectList.add(queryTerm.getValue());
		}

		Object[] parameters = objectList.toArray();

		int rowsAffected = jdbcTemplate.update(queryTemplate, parameters);

		return rowsAffected;
	}

	private void addParameterMapValue(MapSqlParameterSource parameters, String insertColumnName, RequestOther requestOtherModel)
	{
		String parameterName = QueryStringBuilder.convertColumnName(insertColumnName, false);

		// Wish this could generalize
		// The getter must be distinguished unless the models are designed as simply a map of columns-values
		// Would prefer not being that generic since it may end up leading to all code being collections of strings

		if(insertColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.ID)))
		{
			parameters.addValue(parameterName, requestOtherModel.getId());
		}
		else if(insertColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.REQUESTS_ID)))
		{
			parameters.addValue(parameterName, requestOtherModel.getRequestsID());
		}
		else if(insertColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.MESSAGE)))
		{
			parameters.addValue(parameterName, requestOtherModel.getMessage());
		}
		else if(insertColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.CREATED_AT)))
		{
			parameters.addValue(parameterName, requestOtherModel.getCreatedAt());
		}
		else if(insertColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.UPDATED_AT)))
		{
			parameters.addValue(parameterName, requestOtherModel.getUpdatedAt());
		}
		else if(insertColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.DELETED)))
		{
			parameters.addValue(parameterName, requestOtherModel.getDeleted());
		}
		else
		{
			// should never end up here
			// lists should have already been validated
			throw new IllegalArgumentException("Invalid column name provided: " + insertColumnName);
		}
	}	

	private void addObjectValue(Map<String, Object> keyMap, String keyHolderColumnName, RequestOther requestOtherModel)
	{
		if(keyHolderColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.ID)))
		{
			requestOtherModel.setId((Integer) keyMap.get(keyHolderColumnName));
		}
		else if(keyHolderColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.REQUESTS_ID)))
		{
			requestOtherModel.setRequestsID((Integer) keyMap.get(keyHolderColumnName));
		}
		else if(keyHolderColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.MESSAGE)))
		{
			requestOtherModel.setMessage((String) keyMap.get(keyHolderColumnName));
		}
		else if(keyHolderColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.CREATED_AT)))
		{
			requestOtherModel.setCreatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		}
		else if(keyHolderColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.UPDATED_AT)))
		{
			requestOtherModel.setUpdatedAt((Timestamp) keyMap.get(keyHolderColumnName));
		}
		else if(keyHolderColumnName.equals(RequestOther.getColumnName(RequestOther.Columns.DELETED)))
		{
			requestOtherModel.setDeleted((Boolean) keyMap.get(keyHolderColumnName));
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
		List<String> actualColumnNames = RequestOther.getColumnNameList();
		boolean valid = actualColumnNames.containsAll(columnNameList);

		if(!valid)
		{
			List<String> invalidColumnNames = new ArrayList<>(columnNameList);
			invalidColumnNames.removeAll(actualColumnNames);

			throw new IllegalArgumentException("Invalid column names provided: " + invalidColumnNames);
		}
	}
}
