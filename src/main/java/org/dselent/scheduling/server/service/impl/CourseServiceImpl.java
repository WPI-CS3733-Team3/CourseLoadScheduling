package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.dao.SectionsDao;
import org.dselent.scheduling.server.model.CourseInfo;
import org.dselent.scheduling.server.model.Courses;
import org.dselent.scheduling.server.model.Sections;
import org.dselent.scheduling.server.service.CourseService;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CourseServiceImpl implements CourseService
{
	@Autowired
	private CoursesDao coursesDao;
	
	@Autowired
	private SectionsDao sectionsDao;
	
	@Autowired
	private CustomDao customDao;

	public CourseServiceImpl()
	{
		//
	}

	@Transactional
	@Override
	public Integer create(String courseName, String courseNumber, int frequency) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();

		// TODO validate business constraints
		// ^^students should do this^^
		// password strength requirements
		// email requirements
		// null values
		// etc...

		Courses course = new Courses();
		course.setTitle(courseName);
		course.setNumber(courseNumber);
		course.setFrequencyID(frequency);

		List<String> courseInsertColumnNameList = new ArrayList<>();
		List<String> courseKeyHolderColumnNameList = new ArrayList<>();

		courseInsertColumnNameList.add(Courses.getColumnName(Courses.Columns.TITLE));
		courseInsertColumnNameList.add(Courses.getColumnName(Courses.Columns.NUMBER));
		courseInsertColumnNameList.add(Courses.getColumnName(Courses.Columns.FREQUENCY_ID));

		courseKeyHolderColumnNameList.add(Courses.getColumnName(Courses.Columns.ID));
		courseKeyHolderColumnNameList.add(Courses.getColumnName(Courses.Columns.CREATED_AT));
		courseKeyHolderColumnNameList.add(Courses.getColumnName(Courses.Columns.UPDATED_AT));

		rowsAffectedList.add(coursesDao.insert(course, courseInsertColumnNameList, courseKeyHolderColumnNameList));

		return course.getId();//rowsAffectedList;
	}
	
	@Transactional
	@Override
	public Courses edit(int courseId, String courseName, String courseNumber, int frequencyId) throws SQLException
	{

		QueryTerm qt1 = new QueryTerm();
    	
    	qt1.setColumnName(Courses.getColumnName(Courses.Columns.ID));
    	qt1.setComparisonOperator(ComparisonOperator.EQUAL);
    	qt1.setLogicalOperator(null);
    	qt1.setValue(courseId);
    	
    	List<QueryTerm> qtList = new ArrayList<QueryTerm>();
    	qtList.add(qt1);

    	//queries to update the rows.
    	coursesDao.update(Courses.getColumnName(Courses.Columns.TITLE), courseName, qtList);
    	coursesDao.update(Courses.getColumnName(Courses.Columns.NUMBER), courseNumber, qtList);
    	coursesDao.update(Courses.getColumnName(Courses.Columns.FREQUENCY_ID), frequencyId, qtList);
		
		return coursesDao.findById(courseId);
	}

	
	@Transactional
	@Override
	public Integer removeCourse(Integer coursesId) throws SQLException
	{
		if(coursesId == null) {
			return null;
		}
		
		//maybe cascade removing schedule stuff here??*******************************
		
		//deletes all sections  for the course
		QueryTerm deleteTermsCascade = new QueryTerm();
    	
    	deleteTermsCascade.setColumnName(Sections.getColumnName(Sections.Columns.COURSES_ID));
    	deleteTermsCascade.setComparisonOperator(ComparisonOperator.EQUAL);
    	deleteTermsCascade.setLogicalOperator(null);
    	deleteTermsCascade.setValue(coursesId);
    	
    	List<QueryTerm> qtListCas = new ArrayList<QueryTerm>();
    	qtListCas.add(deleteTermsCascade);
    	
    	sectionsDao.delete(qtListCas);
    	
		//specify the scheduleId to be removed and builds the queryTerm for it
    	QueryTerm deleteTerms = new QueryTerm();
    	
    	deleteTerms.setColumnName(Courses.getColumnName(Courses.Columns.ID));
    	deleteTerms.setComparisonOperator(ComparisonOperator.EQUAL);
    	deleteTerms.setLogicalOperator(null);
    	deleteTerms.setValue(coursesId);
    	
    	List<QueryTerm> qtList = new ArrayList<QueryTerm>();
    	qtList.add(deleteTerms);
    	
    	return coursesDao.delete(qtList);
	}  
	
	//view all courses
	@Override
    public List<CourseInfo> viewAllCourse(){
    	return customDao.getCourseInfo();
    }
	
	
    //views one course based on id
	@Override
    public List<CourseInfo> viewOneCourse(Integer id) {
    	if(id == null) {
    		return null;
    	}
    	return customDao.getCourseInfoOne(id);
    }
    
}
