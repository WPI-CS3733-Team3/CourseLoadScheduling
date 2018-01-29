package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.StartTime;

public class StartTimeExtractor extends Extractor<List<StartTime>> {

	@Override
	public List<StartTime> extractData(ResultSet rs) throws SQLException
	{
		List<StartTime> resultList = new ArrayList<>();

		while(rs.next())
		{
			StartTime result = new StartTime();
				
			result.setId(rs.getInt(StartTime.getColumnName(StartTime.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setTime(rs.getTimestamp(StartTime.getColumnName(StartTime.Columns.TIME)));
			
			resultList.add(result);
		}
			
		return resultList;
	}

}