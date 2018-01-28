package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.RequestTablesDao;
import org.dselent.scheduling.server.extractor.RequestTablesExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.RequestTables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RequestTablesDaoImpl implements RequestTablesDao{
	
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// can make custom models and extractors as needed or reuse existing ones if applicable
	
	@Override
	public List<RequestTables> getAllRequestsInfo(){
		RequestTablesExtractor extractor = new RequestTablesExtractor();
		String queryTemplate = new String(QueryPathConstants.REQUEST_TABLES_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		//No parameters for this, since it returns all requests, so do not actually fill parameters
		List<RequestTables> fullRequestTablesList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return fullRequestTablesList;
	}
	
}