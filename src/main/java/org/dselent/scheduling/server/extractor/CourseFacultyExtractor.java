package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseFaculty;

public class CourseFacultyExtractor extends Extractor<List<CourseFaculty>>{

	@Override
	public List<CourseFaculty> extractData(ResultSet rs) throws SQLException
	{
		List<CourseFaculty> resultList = new ArrayList<>();

		while(rs.next())
		{
			CourseFaculty result = new CourseFaculty();
		
			result.setCoursesTitle(rs.getString(CourseFaculty.getColumnName(CourseFaculty.Columns.COURSES_TITLE)));
			if(rs.wasNull())
			{
				result.setCoursesTitle(null);
			}
			
			result.setCoursesNumber(rs.getString(CourseFaculty.getColumnName(CourseFaculty.Columns.COURSES_NUMBER)));
			if(rs.wasNull())
			{
				result.setCoursesNumber(null);
			}
			
			result.setFacultyFirstName(rs.getString(CourseFaculty.getColumnName(CourseFaculty.Columns.FACULTY_FIRST_NAME)));
			if(rs.wasNull())
			{
				result.setFacultyFirstName(null);
			}
			
			result.setFacultyLastName(rs.getString(CourseFaculty.getColumnName(CourseFaculty.Columns.FACULTY_LAST_NAME)));
			if(rs.wasNull())
			{
				result.setFacultyLastName(null);
			}
			
		}
		
		return resultList;
	
	}
}
