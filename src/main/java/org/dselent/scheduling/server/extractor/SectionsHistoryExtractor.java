package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.SectionsHistory;

public class SectionsHistoryExtractor extends Extractor<List<SectionsHistory>>{
	@Override
	public List<SectionsHistory> extractData(ResultSet rs) throws SQLException
	{
		List<SectionsHistory> resultList = new ArrayList<>();

		while(rs.next())
		{
			SectionsHistory result = new SectionsHistory();
				
			result.setId(rs.getInt(SectionsHistory.getColumnName(SectionsHistory.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setName(rs.getString(SectionsHistory.getColumnName(SectionsHistory.Columns.NAME)));
			result.setCrn(rs.getInt(SectionsHistory.getColumnName(SectionsHistory.Columns.CRN)));
			if(rs.wasNull())
			{
				result.setCrn(null);
			}
			
			result.setTermsID(rs.getInt(SectionsHistory.getColumnName(SectionsHistory.Columns.TERMS_ID)));
			if(rs.wasNull())
			{
				result.setTermsID(null);
			}
			
			result.setSectionTypeID(rs.getInt(SectionsHistory.getColumnName(SectionsHistory.Columns.SECTION_TYPE_ID)));
			if(rs.wasNull())
			{
				result.setSectionTypeID(null);
			}
			
			result.setDaysID(rs.getInt(SectionsHistory.getColumnName(SectionsHistory.Columns.DAYS_ID)));
			if(rs.wasNull())
			{
				result.setDaysID(null);
			}
			
			result.setCoursesID(rs.getInt(SectionsHistory.getColumnName(SectionsHistory.Columns.COURSES_ID)));
			if(rs.wasNull())
			{
				result.setCoursesID(null);
			}
			
			result.setStartID(rs.getInt(SectionsHistory.getColumnName(SectionsHistory.Columns.START_ID)));
			if(rs.wasNull())
			{
				result.setStartID(null);
			}
			
			result.setEndID(rs.getInt(SectionsHistory.getColumnName(SectionsHistory.Columns.END_ID)));
			if(rs.wasNull())
			{
				result.setEndID(null);
			}
			
			result.setCreatedAt(rs.getTimestamp(SectionsHistory.getColumnName(SectionsHistory.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(SectionsHistory.getColumnName(SectionsHistory.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(SectionsHistory.getColumnName(SectionsHistory.Columns.DELETED)));
			if(rs.wasNull())
			{
				result.setDeleted(null);
			}
			
			resultList.add(result);
		}
			
		return resultList;
	}
}
