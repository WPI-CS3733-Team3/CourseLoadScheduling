package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.RequestTime;

public class RequestTimeExtractor extends Extractor<List<RequestTime>>
{
	@Override
	public List<RequestTime> extractData(ResultSet rs) throws SQLException
	{
		List<RequestTime> resultList = new ArrayList<>();

		while(rs.next())
		{
			RequestTime result = new RequestTime();
				
			result.setId(rs.getInt(RequestTime.getColumnName(RequestTime.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setRequestsID(rs.getInt(RequestTime.getColumnName(RequestTime.Columns.REQUESTS_ID)));
			result.setStartID(rs.getInt(RequestTime.getColumnName(RequestTime.Columns.START_ID)));
			result.setEndID(rs.getInt(RequestTime.getColumnName(RequestTime.Columns.END_ID)));
			result.setCreatedAt(rs.getTimestamp(RequestTime.getColumnName(RequestTime.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(RequestTime.getColumnName(RequestTime.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(RequestTime.getColumnName(RequestTime.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
