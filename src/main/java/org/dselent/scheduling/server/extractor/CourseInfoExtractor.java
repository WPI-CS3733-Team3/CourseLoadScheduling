package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseInfo;

public class CourseInfoExtractor extends Extractor<List<CourseInfo>>{

	@Override
	public List<CourseInfo> extractData(ResultSet rs) throws SQLException
	{
		List<CourseInfo> resultList = new ArrayList<>();

		while(rs.next())
		{
			CourseInfo result = new CourseInfo();
		
			result.setCoursesId(rs.getInt(CourseInfo.getColumnName(CourseInfo.Columns.COURSES_ID)));
			if(rs.wasNull())
			{
				result.setCoursesId(null);
			}
			
			result.setCoursesNumber(rs.getString(CourseInfo.getColumnName(CourseInfo.Columns.COURSES_NUMBER)));
			if(rs.wasNull())
			{
				result.setCoursesNumber(null);
			}
			
			result.setCoursesTitle(rs.getString(CourseInfo.getColumnName(CourseInfo.Columns.COURSES_TITLE)));
			if(rs.wasNull())
			{
				result.setCoursesTitle(null);
			}
			
			result.setFrequencyId(rs.getInt(CourseInfo.getColumnName(CourseInfo.Columns.FREQUENCY_ID)));
			if(rs.wasNull())
			{
				result.setFrequencyId(null);
			}
			
			result.setFrequency(rs.getString(CourseInfo.getColumnName(CourseInfo.Columns.FREQUENCY)));
			if(rs.wasNull())
			{
				result.setFrequency(null);
			}
			
			resultList.add(result);
		}	
		
		return resultList;
	
	}

	
}
