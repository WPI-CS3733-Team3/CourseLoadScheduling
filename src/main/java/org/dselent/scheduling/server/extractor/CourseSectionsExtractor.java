package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseSections;

public class CourseSectionsExtractor extends Extractor<List<CourseSections>>{

	@Override
	public List<CourseSections> extractData(ResultSet rs) throws SQLException
	{
		List<CourseSections> resultList = new ArrayList<>();

		while(rs.next())
		{
			CourseSections result = new CourseSections();
		
			result.setTermsName(rs.getString(CourseSections.getColumnName(CourseSections.Columns.TERMS_NAME)));
			if(rs.wasNull())
			{
				result.setTermsName(null);
			}
			
			result.setSectionType(rs.getString(CourseSections.getColumnName(CourseSections.Columns.SECTION_TYPE)));
			if(rs.wasNull())
			{
				result.setSectionType(null);
			}
			
			result.setSectionsName(rs.getString(CourseSections.getColumnName(CourseSections.Columns.SECTIONS_NAME)));
			if(rs.wasNull())
			{
				result.setSectionsName(null);
			}
			
			result.setDays(rs.getString(CourseSections.getColumnName(CourseSections.Columns.DAYS)));
			if(rs.wasNull())
			{
				result.setDays(null);
			}
			
			result.setCoursesNumber(rs.getString(CourseSections.getColumnName(CourseSections.Columns.COURSES_NUMBER)));
			if(rs.wasNull())
			{
				result.setCoursesNumber(null);
			}
			
			result.setCoursesTitle(rs.getString(CourseSections.getColumnName(CourseSections.Columns.COURSES_TITLE)));
			if(rs.wasNull())
			{
				result.setCoursesTitle(null);
			}
			
			result.setStartTime(rs.getTimestamp(CourseSections.getColumnName(CourseSections.Columns.START_TIME)));
			if(rs.wasNull())
			{
				result.setStartTime(null);
			}
			
			result.setEndTime(rs.getTimestamp(CourseSections.getColumnName(CourseSections.Columns.END_TIME)));
			if(rs.wasNull())
			{
				result.setEndTime(null);
			}
			
		}
		
		return resultList;
	
	}
}
