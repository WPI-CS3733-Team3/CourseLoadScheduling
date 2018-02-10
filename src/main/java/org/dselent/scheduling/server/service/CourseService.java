package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.dselent.scheduling.server.model.CourseInfo;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
   //creates a course
	public List<Integer> create(String courseName, String courseNumber, int frequency) throws SQLException;
    
	//edits a course
    public Courses edit(int courseId, String courseName, String courseNumber, int frequencyId) throws SQLException;
   
    //remove a course, returns id of deleted course
    public Integer removeCourse(Integer id) throws SQLException;
  
    //view all courses
    public List<CourseInfo> viewAllCourse();
       
    //views one course based on id
    public List<CourseInfo> viewOneCourse(Integer id);
}
