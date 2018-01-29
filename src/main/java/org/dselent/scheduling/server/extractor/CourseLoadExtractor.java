package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseLoad;

public class CourseLoadExtractor extends Extractor<List<CourseLoad>>
{
	@Override
	public List<CourseLoad> extractData(ResultSet rs) throws SQLException
	{
		List<CourseLoad> resultList = new ArrayList<>();

		while(rs.next())
		{
			CourseLoad result = new CourseLoad();
				
			result.setId(rs.getInt(CourseLoad.getColumnName(CourseLoad.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setCourseLoadTypeID(rs.getInt(CourseLoad.getColumnName(CourseLoad.Columns.COURSE_LOAD_TYPE_ID)));
			result.setCourseLoadHours(rs.getInt(CourseLoad.getColumnName(CourseLoad.Columns.COURSE_LOAD_HOURS)));
			result.setFacultyID(rs.getInt(CourseLoad.getColumnName(CourseLoad.Columns.FACULTY_ID)));
			result.setCreatedAt(rs.getTimestamp(CourseLoad.getColumnName(CourseLoad.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(CourseLoad.getColumnName(CourseLoad.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(CourseLoad.getColumnName(CourseLoad.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
