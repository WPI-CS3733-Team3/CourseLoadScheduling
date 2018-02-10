package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.model.Courses;
import org.dselent.scheduling.server.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CourseServiceImpl implements CourseService
{
	@Autowired
	private CoursesDao coursesDao;

	public CourseServiceImpl()
	{
		//
	}

	@Transactional
	@Override
	public List<Integer> create(String courseName, String courseNumber, int frequency) throws SQLException
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

		return rowsAffectedList;
	}
	
	public List<Integer> edit(String courseName, String courseNumber, int frequency) throws SQLException
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

		return rowsAffectedList;
	}

	//

	@Override
	public ExampleUser loginUser(String userName, String password)
	{
		// TODO Auto-generated method stub
		return null;
	}   

}
