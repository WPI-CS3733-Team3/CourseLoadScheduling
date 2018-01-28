package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.RequestsOneUserDao;
import org.dselent.scheduling.server.extractor.RequestTablesExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.RequestTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RequestsOneUserDaoImpl implements RequestsOneUserDao{

	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// can make custom models and extractors as needed or reuse existing ones if applicable
	
	@Override
	public List<RequestTables> getOneUserRequestsInfo(int userId){
		RequestTablesExtractor extractor = new RequestTablesExtractor();
		String queryTemplate = new String(QueryPathConstants.REQUESTS_ONE_USER_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		List<RequestTables> fullRequestTablesList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return fullRequestTablesList;
	}
}
