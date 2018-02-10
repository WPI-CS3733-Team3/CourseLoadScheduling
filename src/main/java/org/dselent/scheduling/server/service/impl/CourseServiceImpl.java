package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.CoursesDao;
import org.dselent.scheduling.server.dao.CustomDao;
import org.dselent.scheduling.server.model.CourseInfo;
import org.dselent.scheduling.server.model.Courses;
import org.dselent.scheduling.server.model.Schedule;
import org.dselent.scheduling.server.model.SectionsInfo;
import org.dselent.scheduling.server.service.CourseService;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CourseServiceImpl implements CourseService
{
	@Autowired
	private CoursesDao coursesDao;
	
	@Autowired
	private CustomDao customDao;

	public CourseServiceImpl()
	{
		//
	}

	@Transactional
	@Override
	public List<Integer> createCourse(RegisterUserDto dto) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();

		// TODO validate business constraints
		// ^^students should do this^^
		// password strength requirements
		// email requirements
		// null values
		// etc...

		Courses course = new Courses();
		course.setUserName(dto.getUserName());
		course.setFirstName(dto.getFirstName());
		course.setLastName(dto.getLastName());
		course.setEmail(dto.getEmail());
		course.setEncryptedPassword(encryptedPassword);
		course.setSalt(salt);
		course.setUserStateId(1);

		List<String> userInsertColumnNameList = new ArrayList<>();
		List<String> userKeyHolderColumnNameList = new ArrayList<>();

		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.USER_NAME));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.FIRST_NAME));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.LAST_NAME));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.EMAIL));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.ENCRYPTED_PASSWORD));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.SALT));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.USER_STATE_ID));

		userKeyHolderColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.ID));
		userKeyHolderColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.CREATED_AT));
		userKeyHolderColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.UPDATED_AT));

		rowsAffectedList.add(usersDao.insert(user, userInsertColumnNameList, userKeyHolderColumnNameList));

		//

		// for now, assume users can only register with default role id
		// may change in the future

		ExampleUsersRolesLink usersRolesLink = new ExampleUsersRolesLink();
		usersRolesLink.setUserId(user.getId());
		usersRolesLink.setRoleId(1); // hard coded as regular user

		List<String> usersRolesLinksInsertColumnNameList = new ArrayList<>();
		List<String> usersRolesLinksKeyHolderColumnNameList = new ArrayList<>();

		usersRolesLinksInsertColumnNameList.add(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.USER_ID));
		usersRolesLinksInsertColumnNameList.add(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.ROLE_ID));

		usersRolesLinksKeyHolderColumnNameList.add(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.ID));
		usersRolesLinksKeyHolderColumnNameList.add(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.CREATED_AT));
		usersRolesLinksKeyHolderColumnNameList.add(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.DELETED));

		rowsAffectedList.add(usersRolesLinksDao.insert(usersRolesLink, usersRolesLinksInsertColumnNameList, usersRolesLinksKeyHolderColumnNameList));

		return rowsAffectedList;
	}

	//

	@Override
	public ExampleUser loginUser(String userName, String password)
	{
		// TODO Auto-generated method stub
		return null;
	}   

	
	@Transactional
	@Override
	public Integer removeCourse(Integer coursesId) throws SQLException
	{
		if(coursesId == null) {
			return null;
		}
		
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
    public List<CourseInfo> viewAllCourse(){
    	return customDao.getCourseInfo();
    }
	
	
    //views one course based on id
    public List<CourseInfo> viewOneCourse(Integer id) {
    	if(id == null) {
    		return null;
    	}
    	
    	return customDao.getCourseInfoOne(id);
    	
    }
    
}
