package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.ScheduleHistory;

public class ScheduleHistoryExtractor extends Extractor<List<ScheduleHistory>>{
	@Override
	public List<ScheduleHistory> extractData(ResultSet rs) throws SQLException
	{
		List<ScheduleHistory> resultList = new ArrayList<>();

		while(rs.next())
		{
			ScheduleHistory result = new ScheduleHistory();
				
			result.setId(rs.getInt(ScheduleHistory.getColumnName(ScheduleHistory.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setFacultyID(rs.getInt(ScheduleHistory.getColumnName(ScheduleHistory.Columns.FACULTY_ID)));
			result.setSectionsID(rs.getInt(ScheduleHistory.getColumnName(ScheduleHistory.Columns.SECTIONS_ID)));
			result.setCreatedAt(rs.getTimestamp(ScheduleHistory.getColumnName(ScheduleHistory.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(ScheduleHistory.getColumnName(ScheduleHistory.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(ScheduleHistory.getColumnName(ScheduleHistory.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
