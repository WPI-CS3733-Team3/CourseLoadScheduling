package org.dselent.scheduling.server.dao.impl;

import java.util.List;

import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.extractor.CourseInfoExtractor;
import org.dselent.scheduling.server.extractor.RequestTablesExtractor;
import org.dselent.scheduling.server.extractor.SectionsInfoExtractor;
import org.dselent.scheduling.server.extractor.CourseSectionsExtractor;
import org.dselent.scheduling.server.extractor.AccountInfoExtractor;
import org.dselent.scheduling.server.extractor.CourseFacultyExtractor;
import org.dselent.scheduling.server.extractor.UsersExtractor;
import org.dselent.scheduling.server.miscellaneous.QueryPathConstants;
import org.dselent.scheduling.server.model.CourseInfo;
import org.dselent.scheduling.server.model.RequestTables;
import org.dselent.scheduling.server.model.SectionsInfo;
import org.dselent.scheduling.server.model.CourseSections;
import org.dselent.scheduling.server.model.AccountInfo;
import org.dselent.scheduling.server.model.CourseFaculty;
import org.dselent.scheduling.server.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CustomDaoImpl implements CustomDao
{
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// can make custom models and extractors as needed or reuse existing ones if applicable

	@Override
	public List<User> getAllUsersWithRole(int roleId)
	{
		UsersExtractor extractor = new UsersExtractor();
		String queryTemplate = new String(QueryPathConstants.USERS_WITH_ROLE_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("roleId", roleId);
		List<User> usersWithRoleList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return usersWithRoleList;
	}


	//Gets the information pertaining to every course
	@Override
	public List<CourseInfo> getCourseInfo(){
		CourseInfoExtractor extractor = new CourseInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.COURSE_INFO_QUERY);

		//No parameters for this, since it returns all requests, so do not actually fill parameters
		List<CourseInfo> courseList = namedParameterJdbcTemplate.query(queryTemplate, extractor);
		return courseList;
	}

	//Gets the information pertaining to one course, specified by courseId
	@Override
	public List<CourseInfo> getCourseInfoOne(int courseId){
		CourseInfoExtractor extractor = new CourseInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.COURSE_INFO_ONE_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("courseId", courseId);
		List<CourseInfo> courseList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return courseList;
	}


	//Gets info about all requests, combining the tables together
	@Override
	public List<RequestTables> getAllRequestsInfo(){
		RequestTablesExtractor extractor = new RequestTablesExtractor();
		String queryTemplate = new String(QueryPathConstants.REQUEST_TABLES_QUERY);

		//No parameters for this, since it returns all requests, so do not actually fill parameters
		List<RequestTables> fullRequestTablesList = namedParameterJdbcTemplate.query(queryTemplate, extractor);
		return fullRequestTablesList;
	}

	//Gets info about all requests made by a specific user, specified by userId
	@Override
	public List<RequestTables> getOneUserRequestsInfo(int userId){
		RequestTablesExtractor extractor = new RequestTablesExtractor();
		String queryTemplate = new String(QueryPathConstants.REQUESTS_ONE_USER_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("userId", userId);
		List<RequestTables> fullRequestTablesList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return fullRequestTablesList;
	}

	//Gets info about all sections
	@Override
	public List<SectionsInfo> getSectionsInfo(){
		SectionsInfoExtractor extractor = new SectionsInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.SECTIONS_INFO_QUERY);

		//No parameters for this, since it returns all sections, so do not actually fill parameters
		List<SectionsInfo> sectionsList = namedParameterJdbcTemplate.query(queryTemplate, extractor);
		return sectionsList;
	}

	//Gets info about all sections being taught be one faculty member, specified by facultyId
	@Override
	public List<SectionsInfo> getOneFacultySectionsInfo(int facultyId){
		SectionsInfoExtractor extractor = new SectionsInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.SECTIONS_ONE_FACULTY_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("facultyId", facultyId);
		List<SectionsInfo> sectionsList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsList;
	}

	//Gets all sections belonging to a course, specified by courseId
	public List<CourseSections> getSectionsForCourse(int courseId){
		CourseSectionsExtractor extractor = new CourseSectionsExtractor();
		String queryTemplate = new String(QueryPathConstants.COURSE_SECTIONS_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("courseId", courseId);
		List<CourseSections> sectionsList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsList;
	}

	//Gets all current faculty accounts, with their names, emails, faculty type and account type
	public List<AccountInfo> getAccountInfo(){
		AccountInfoExtractor extractor = new AccountInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.ACCOUNT_INFO_QUERY);

		//no parameters need to be added
		List<AccountInfo> acctList = namedParameterJdbcTemplate.query(queryTemplate, extractor);
		return acctList;
	}

	//Gets the names of all faculty who teach a course specified by courseId
	public List<CourseFaculty> getCourseFaculty(int courseId){
		CourseFacultyExtractor extractor = new CourseFacultyExtractor();
		String queryTemplate = new String(QueryPathConstants.COURSE_FACULTY_QUERY);

		//no parameters need to be added
		List<CourseFaculty> facultyList = namedParameterJdbcTemplate.query(queryTemplate, extractor);
		return facultyList;
	}

	//Gets the info of all deleted accounts
	public List<AccountInfo> getDeletedAccountInfo(){
		AccountInfoExtractor extractor = new AccountInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.DELETED_ACCOUNT_INFO_QUERY);

		//no parameters need to be added
		List<AccountInfo> deletedAcctList = namedParameterJdbcTemplate.query(queryTemplate, extractor);
		return deletedAcctList;
	}


	//Gets info about all sections in the schedule by term
	public List<SectionsInfo> getSectionsInfo(int termId){
		SectionsInfoExtractor extractor = new SectionsInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.SECTIONS_INFO_TERMS_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("termsId", termId);
		List<SectionsInfo> sectionsList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsList;
	}

	//Gets info about all sections being taught be one faculty member in a certain term, specified by facultyId and termId
	public List<SectionsInfo> getOneFacultySectionsInfo(int facultyId, int termId){
		SectionsInfoExtractor extractor = new SectionsInfoExtractor();
		String queryTemplate = new String(QueryPathConstants.SECTIONS_ONE_FACULTY_TERMS_QUERY);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("facultyId", facultyId);
		parameters.addValue("termsId", termId);
		List<SectionsInfo> sectionsList = namedParameterJdbcTemplate.query(queryTemplate, parameters, extractor);
		return sectionsList;
	}


}
