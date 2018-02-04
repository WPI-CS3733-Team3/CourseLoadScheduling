package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.RequestTerm;

public class RequestTermExtractor extends Extractor<List<RequestTerm>>
{
	@Override
	public List<RequestTerm> extractData(ResultSet rs) throws SQLException
	{
		List<RequestTerm> resultList = new ArrayList<>();

		while(rs.next())
		{
			RequestTerm result = new RequestTerm();
				
			result.setId(rs.getInt(RequestTerm.getColumnName(RequestTerm.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setRequestsID(rs.getInt(RequestTerm.getColumnName(RequestTerm.Columns.REQUESTS_ID)));
			if(rs.wasNull())
			{
				result.setRequestsID(null);
			}
			
			result.setTermsID(rs.getInt(RequestTerm.getColumnName(RequestTerm.Columns.TERMS_ID)));
			if(rs.wasNull())
			{
				result.setTermsID(null);
			}
			
			result.setCreatedAt(rs.getTimestamp(RequestTerm.getColumnName(RequestTerm.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(RequestTerm.getColumnName(RequestTerm.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(RequestTerm.getColumnName(RequestTerm.Columns.DELETED)));
			if(rs.wasNull())
			{
				result.setDeleted(null);
			}
			
			resultList.add(result);
		}
			
		return resultList;
	}

}
