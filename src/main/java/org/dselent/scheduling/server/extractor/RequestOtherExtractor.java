package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.RequestOther;

public class RequestOtherExtractor extends Extractor<List<RequestOther>>
{
	@Override
	public List<RequestOther> extractData(ResultSet rs) throws SQLException
	{
		List<RequestOther> resultList = new ArrayList<>();

		while(rs.next())
		{
			RequestOther result = new RequestOther();
				
			result.setId(rs.getInt(RequestOther.getColumnName(RequestOther.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setRequestsID(rs.getInt(RequestOther.getColumnName(RequestOther.Columns.REQUESTS_ID)));
			result.setMessage(rs.getString(RequestOther.getColumnName(RequestOther.Columns.MESSAGE)));
			result.setCreatedAt(rs.getTimestamp(RequestOther.getColumnName(RequestOther.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(RequestOther.getColumnName(RequestOther.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(RequestOther.getColumnName(RequestOther.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
