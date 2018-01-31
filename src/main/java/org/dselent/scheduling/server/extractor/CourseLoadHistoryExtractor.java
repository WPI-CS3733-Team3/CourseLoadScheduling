package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseLoadHistory;

public class CourseLoadHistoryExtractor extends Extractor<List<CourseLoadHistory>>{

	@Override
	public List<CourseLoadHistory> extractData(ResultSet rs) throws SQLException
	{
		List<CourseLoadHistory> resultList = new ArrayList<>();

		while(rs.next())
		{
			CourseLoadHistory result = new CourseLoadHistory();
				
			result.setId(rs.getInt(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setCourseLoadTypeID(rs.getInt(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.COURSE_LOAD_TYPE_ID)));
			result.setCourseLoadHours(rs.getInt(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.COURSE_LOAD_HOURS)));
			result.setFacultyID(rs.getInt(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.FACULTY_ID)));
			result.setCreatedAt(rs.getTimestamp(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(CourseLoadHistory.getColumnName(CourseLoadHistory.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}
	
}
