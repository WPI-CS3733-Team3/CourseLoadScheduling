package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Days;

public class DaysExtractor extends Extractor<List<Days>> {
	
	@Override
	public List<Days> extractData(ResultSet rs) throws SQLException
	{
		List<Days> resultList = new ArrayList<>();

		while(rs.next())
		{
			Days result = new Days();
				
			result.setId(rs.getInt(Days.getColumnName(Days.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setDay(rs.getString(Days.getColumnName(Days.Columns.DAY)));
			
			resultList.add(result);
		}
			
		return resultList;
	}

}