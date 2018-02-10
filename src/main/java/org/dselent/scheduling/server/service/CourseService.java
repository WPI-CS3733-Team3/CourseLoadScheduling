package org.dselent.scheduling.server.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;


public class CourseService {
    public List<Integer> create(String courseName, String courseNumber, int frequency) throws SQLException;
    public List<Integer> edit(EditCourseDto editCourseDto) throws SQLException;
   public Course removeCourse(Integer id);
   public viewAllCourse(Integer id);
       public viewOneCourse(Integer id);
}