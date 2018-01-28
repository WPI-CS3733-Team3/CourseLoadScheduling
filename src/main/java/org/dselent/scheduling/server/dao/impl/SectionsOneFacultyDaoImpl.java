package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.SectionsOneFacultyDao;
import org.dselent.scheduling.server.extractor.SectionsInfoExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.SectionsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SectionsOneFacultyDaoImpl implements SectionsOneFacultyDao {

	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// can make custom models and extractors as needed or reuse existing ones if applicable
	
	@Override
	public List<SectionsInfo> getOneFacultySectionsInfo(int facultyId){
		SectionsInfoExtractor extractor = new SectionsInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.USERS_WITH_ROLE_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		//No parameters for this, since it returns all requests, so do not actually fill parameters
		List<SectionsInfo> fullRequestTablesList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return fullRequestTablesList;
	}
}
