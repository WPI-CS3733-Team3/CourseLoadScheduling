package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Schedule;

public class ScheduleExtractor extends Extractor<List<Schedule>>
{
	@Override
	public List<Schedule> extractData(ResultSet rs) throws SQLException
	{
		List<Schedule> resultList = new ArrayList<>();

		while(rs.next())
		{
			Schedule result = new Schedule();
				
			result.setId(rs.getInt(Schedule.getColumnName(Schedule.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setFacultyID(rs.getInt(Schedule.getColumnName(Schedule.Columns.FACULTY_ID)));
			result.setSectionsID(rs.getInt(Schedule.getColumnName(Schedule.Columns.SECTIONS_ID)));
			result.setCreatedAt(rs.getTimestamp(Schedule.getColumnName(Schedule.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(Schedule.getColumnName(Schedule.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(Schedule.getColumnName(Schedule.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
