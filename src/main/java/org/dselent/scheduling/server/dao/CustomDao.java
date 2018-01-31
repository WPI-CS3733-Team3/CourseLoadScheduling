package org.dselent.scheduling.server.dao;

import java.util.List;

import org.dselent.scheduling.server.model.CourseInfo;
import org.dselent.scheduling.server.model.RequestTables;
import org.dselent.scheduling.server.model.SectionsInfo;
import org.dselent.scheduling.server.model.CourseSections;
import org.dselent.scheduling.server.model.AccountInfo;
import org.dselent.scheduling.server.model.CourseFaculty;
import org.dselent.scheduling.server.model.User;
import org.springframework.stereotype.Repository;

/**
 * Interface for all daos for custom queries.
 * 
 * @author dselent
 * Modified by Hannah Jauris
 *
 */
@Repository
public interface CustomDao
{
	// custom queries here
	
	public List<User> getAllUsersWithRole(int roleId);//**example, delete this one once finished!**
	
	//Gets the information pertaining to every course
	public List<CourseInfo> getCourseInfo();
	
	//Gets the information pertaining to one course, specified by courseId
	public List<CourseInfo> getCourseInfoOne(int courseId);
	
	//Gets info about all requests, combining the tables together
	public List<RequestTables> getAllRequestsInfo();
	
	//Gets info about all requests made by a specific user, specified by userId
	public List<RequestTables> getOneUserRequestsInfo(int userId);
	
	//Gets info about all sections
	public List<SectionsInfo> getSectionsInfo();
	
	//Gets info about all sections being taught be one faculty member, specified by facultyId
	public List<SectionsInfo> getOneFacultySectionsInfo(int facultyId);
	
	//Gets all sections belonging to a course, specified by courseId
	public List<CourseSections> getSectionsForCourse(int courseId);
	
	//Gets all current faculty accounts, with their names, emails, faculty type and account type
	public List<AccountInfo> getAccountInfo();
	
	//Gets the names of all faculty who teach a course specified by courseId
	public List<CourseFaculty> getCourseFaculty(int courseId);
	
	//Gets the info of all deleted accounts
	public List<AccountInfo> getDeletedAccountInfo();
	
	
}
