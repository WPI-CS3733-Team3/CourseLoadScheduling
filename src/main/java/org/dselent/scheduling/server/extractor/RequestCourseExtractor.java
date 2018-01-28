package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.RequestCourse;

public class RequestCourseExtractor extends Extractor<List<RequestCourse>>
{
	@Override
	public List<RequestCourse> extractData(ResultSet rs) throws SQLException
	{
		List<RequestCourse> resultList = new ArrayList<>();

		while(rs.next())
		{
			RequestCourse result = new RequestCourse();
				
			result.setId(rs.getInt(RequestCourse.getColumnName(RequestCourse.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setRequestsID(rs.getInt(RequestCourse.getColumnName(RequestCourse.Columns.REQUESTS_ID)));
			result.setCoursesID(rs.getInt(RequestCourse.getColumnName(RequestCourse.Columns.COURSES_ID)));
			result.setCreatedAt(rs.getTimestamp(RequestCourse.getColumnName(RequestCourse.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(RequestCourse.getColumnName(RequestCourse.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(RequestCourse.getColumnName(RequestCourse.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
