package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.SectionsInfo;

public class SectionsInfoExtractor extends Extractor<List<SectionsInfo>>{
	
	@Override
	public List<SectionsInfo> extractData(ResultSet rs) throws SQLException
	{
		List<SectionsInfo> resultList = new ArrayList<>();

		while(rs.next())
		{
			SectionsInfo result = new SectionsInfo();
		
			result.setTermsName(rs.getString(SectionsInfo.getColumnName(SectionsInfo.Columns.TERMS_NAME)));
			if(rs.wasNull())
			{
				result.setTermsName(null);
			}
			
			result.setSectionType(rs.getString(SectionsInfo.getColumnName(SectionsInfo.Columns.SECTION_TYPE)));
			if(rs.wasNull())
			{
				result.setSectionType(null);
			}
			
			result.setDays(rs.getString(SectionsInfo.getColumnName(SectionsInfo.Columns.DAYS)));
			if(rs.wasNull())
			{
				result.setDays(null);
			}
			
			result.setCoursesNumber(rs.getString(SectionsInfo.getColumnName(SectionsInfo.Columns.COURSES_NUMBER)));
			if(rs.wasNull())
			{
				result.setCoursesNumber(null);
			}
			
			result.setCoursesTitle(rs.getString(SectionsInfo.getColumnName(SectionsInfo.Columns.COURSES_TITLE)));
			if(rs.wasNull())
			{
				result.setCoursesTitle(null);
			}
			
			result.setDays(rs.getString(SectionsInfo.getColumnName(SectionsInfo.Columns.DAYS)));
			if(rs.wasNull())
			{
				result.setDays(null);
			}
			
			result.setStartTime(rs.getTime(SectionsInfo.getColumnName(SectionsInfo.Columns.START_TIME)));
			if(rs.wasNull())
			{
				result.setStartTime(null);
			}

			result.setEndTime(rs.getTime(SectionsInfo.getColumnName(SectionsInfo.Columns.END_TIME)));
			if(rs.wasNull())
			{
				result.setEndTime(null);
			}
			
			result.setSectionsName(rs.getString(SectionsInfo.getColumnName(SectionsInfo.Columns.SECTIONS_NAME)));
			if(rs.wasNull())
			{
				result.setSectionsName(null);
			}
			
			result.setFacultyFirstName(rs.getString(SectionsInfo.getColumnName(SectionsInfo.Columns.FACULTY_FIRST_NAME)));
			if(rs.wasNull())
			{
				result.setFacultyFirstName(null);
			}
			
			result.setFacultyLastName(rs.getString(SectionsInfo.getColumnName(SectionsInfo.Columns.FACULTY_LAST_NAME)));
			if(rs.wasNull())
			{
				result.setFacultyLastName(null);
			}
			
			result.setSectionsId(rs.getInt(SectionsInfo.getColumnName(SectionsInfo.Columns.SECTION_ID)));
			if(rs.wasNull())
			{
				result.setSectionsId(null);
			}
			
			result.setTermsId(rs.getInt(SectionsInfo.getColumnName(SectionsInfo.Columns.TERMS_ID)));
			if(rs.wasNull())
			{
				result.setTermsId(null);
			}
			
			result.setSectionTypeId(rs.getInt(SectionsInfo.getColumnName(SectionsInfo.Columns.SECTION_TYPE_ID)));
			if(rs.wasNull())
			{
				result.setSectionTypeId(null);
			}
			
			result.setDaysId(rs.getInt(SectionsInfo.getColumnName(SectionsInfo.Columns.DAYS_ID)));
			if(rs.wasNull())
			{
				result.setDaysId(null);
			}

			result.setStartTimeId(rs.getInt(SectionsInfo.getColumnName(SectionsInfo.Columns.START_TIME_ID)));
			if(rs.wasNull())
			{
				result.setStartTimeId(null);
			}
			
			result.setEndTimeId(rs.getInt(SectionsInfo.getColumnName(SectionsInfo.Columns.END_TIME_ID)));
			if(rs.wasNull())
			{
				result.setEndTimeId(null);
			}
			
			result.setCoursesId(rs.getInt(SectionsInfo.getColumnName(SectionsInfo.Columns.COURSES_ID)));
			if(rs.wasNull())
			{
				result.setCoursesId(null);
			}
			
			result.setFacultyId(rs.getInt(SectionsInfo.getColumnName(SectionsInfo.Columns.FACULTY_ID)));
			if(rs.wasNull())
			{
				result.setFacultyId(null);
			}
			
			resultList.add(result);
		}	
		
		return resultList;
	
	}	
	
}
