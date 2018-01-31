package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CoursesHistory;

public class CoursesHistoryExtractor extends Extractor<List<CoursesHistory>>{
	@Override
	public List<CoursesHistory> extractData(ResultSet rs) throws SQLException
	{
		List<CoursesHistory> resultList = new ArrayList<>();

		while(rs.next())
		{
			CoursesHistory result = new CoursesHistory();
				
			result.setId(rs.getInt(CoursesHistory.getColumnName(CoursesHistory.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setTitle(rs.getString(CoursesHistory.getColumnName(CoursesHistory.Columns.TITLE)));
			result.setNumber(rs.getString(CoursesHistory.getColumnName(CoursesHistory.Columns.NUMBER)));
			result.setFrequencyID(rs.getInt(CoursesHistory.getColumnName(CoursesHistory.Columns.FREQUENCY_ID)));
			result.setCreatedAt(rs.getTimestamp(CoursesHistory.getColumnName(CoursesHistory.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(CoursesHistory.getColumnName(CoursesHistory.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(CoursesHistory.getColumnName(CoursesHistory.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}
}
