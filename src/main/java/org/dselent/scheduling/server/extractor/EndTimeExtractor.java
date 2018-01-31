package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.EndTime;

public class EndTimeExtractor extends Extractor<List<EndTime>> {

	@Override
	public List<EndTime> extractData(ResultSet rs) throws SQLException
	{
		List<EndTime> resultList = new ArrayList<>();

		while(rs.next())
		{
			EndTime result = new EndTime();
				
			result.setId(rs.getInt(EndTime.getColumnName(EndTime.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setTime(rs.getTime(EndTime.getColumnName(EndTime.Columns.TIME)));
			
			resultList.add(result);
		}
			
		return resultList;
	}

}