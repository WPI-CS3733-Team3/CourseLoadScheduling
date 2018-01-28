package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Sections;

public class SectionsExtractor extends Extractor<List<Sections>>
{
	@Override
	public List<Sections> extractData(ResultSet rs) throws SQLException
	{
		List<Sections> resultList = new ArrayList<>();

		while(rs.next())
		{
			Sections result = new Sections();
				
			result.setId(rs.getInt(Sections.getColumnName(Sections.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setName(rs.getString(Sections.getColumnName(Sections.Columns.NAME)));
			result.setCrn(rs.getInt(Sections.getColumnName(Sections.Columns.CRN)));
			result.setTermsID(rs.getInt(Sections.getColumnName(Sections.Columns.TERMS_ID)));
			result.setSectionTypeID(rs.getInt(Sections.getColumnName(Sections.Columns.SECTION_TYPE_ID)));
			result.setDaysID(rs.getInt(Sections.getColumnName(Sections.Columns.DAYS_ID)));
			result.setCoursesID(rs.getInt(Sections.getColumnName(Sections.Columns.COURSES_ID)));
			result.setStartID(rs.getInt(Sections.getColumnName(Sections.Columns.START_ID)));
			result.setEndID(rs.getInt(Sections.getColumnName(Sections.Columns.END_ID)));
			result.setCreatedAt(rs.getTimestamp(Sections.getColumnName(Sections.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(Sections.getColumnName(Sections.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(Sections.getColumnName(Sections.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
