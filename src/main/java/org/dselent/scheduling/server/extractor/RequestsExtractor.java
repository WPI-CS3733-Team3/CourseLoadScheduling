package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Requests;

public class RequestsExtractor extends Extractor<List<Requests>>
{
	@Override
	public List<Requests> extractData(ResultSet rs) throws SQLException
	{
		List<Requests> resultList = new ArrayList<>();

		while(rs.next())
		{
			Requests result = new Requests();
				
			result.setId(rs.getInt(Requests.getColumnName(Requests.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setUsersID(rs.getInt(Requests.getColumnName(Requests.Columns.USERS_ID)));
			if(rs.wasNull())
			{
				result.setUsersID(null);
			}
			
			result.setRequestStatusID(rs.getInt(Requests.getColumnName(Requests.Columns.REQUEST_STATUS_ID)));
			if(rs.wasNull())
			{
				result.setRequestStatusID(null);
			}
			
			result.setCreatedAt(rs.getTimestamp(Requests.getColumnName(Requests.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(Requests.getColumnName(Requests.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(Requests.getColumnName(Requests.Columns.DELETED)));
			if(rs.wasNull())
			{
				result.setDeleted(null);
			}
			
			resultList.add(result);
		}
			
		return resultList;
	}

}
