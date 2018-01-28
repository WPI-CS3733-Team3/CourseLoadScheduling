package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.RequestStatus;

public class RequestStatusExtractor extends Extractor<List<RequestStatus>>
{
	@Override
	public List<RequestStatus> extractData(ResultSet rs) throws SQLException
	{
		List<RequestStatus> resultList = new ArrayList<>();

		while(rs.next())
		{
			RequestStatus result = new RequestStatus();
				
			result.setId(rs.getInt(RequestStatus.getColumnName(RequestStatus.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setStatus(rs.getString(RequestStatus.getColumnName(RequestStatus.Columns.STATUS)));
			resultList.add(result);
		}
			
		return resultList;
	}

}
