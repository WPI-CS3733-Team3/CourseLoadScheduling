package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.CourseInfoOneDao;
import org.dselent.scheduling.server.extractor.CourseInfoExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.CourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseInfoOneDaoImpl implements CourseInfoOneDao{

	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	// can make custom models and extractors as needed or reuse existing ones if applicable
	
	@Override
	public List<CourseInfo> getCourseInfoOne(int courseId){
		CourseInfoExtractor extractor = new CourseInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.COURSE_INFO_ONE_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("courseId", courseId);
		List<CourseInfo> fullRequestTablesList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return fullRequestTablesList;
	}
}
