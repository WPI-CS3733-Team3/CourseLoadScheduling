package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.RequestTables;

public class RequestTablesExtractor extends Extractor<List<RequestTables>>{
	@Override
	public List<RequestTables> extractData(ResultSet rs) throws SQLException
	{
		List<RequestTables> resultList = new ArrayList<>();

		while(rs.next())
		{
			RequestTables result = new RequestTables();
				
			result.setRequestsId(rs.getInt(RequestTables.getColumnName(RequestTables.Columns.REQUESTS_ID)));
			if(rs.wasNull())
			{
				result.setRequestsId(null);
			}
			
			result.setRequestsUserId(rs.getInt(RequestTables.getColumnName(RequestTables.Columns.REQUESTS_USER_ID)));
			if(rs.wasNull())
			{
				result.setRequestsUserId(null);
			}
			
			result.setTermsName(rs.getString(RequestTables.getColumnName(RequestTables.Columns.TERMS_NAME)));
			if(rs.wasNull())
			{
				result.setTermsName(null);
			}

			result.setStartTime(rs.getTime(RequestTables.getColumnName(RequestTables.Columns.START_TIME)));
			if(rs.wasNull())
			{
				result.setStartTime(null);
			}
			
			result.setEndTime(rs.getTime(RequestTables.getColumnName(RequestTables.Columns.END_TIME)));
			if(rs.wasNull())
			{
				result.setEndTime(null);
			}
			
			result.setCoursesTitle(rs.getString(RequestTables.getColumnName(RequestTables.Columns.COURSES_TITLE)));
			if(rs.wasNull())
			{
				result.setCoursesTitle(null);
			}
			
			result.setCoursesNumber(rs.getString(RequestTables.getColumnName(RequestTables.Columns.COURSES_NUMBER)));
			if(rs.wasNull())
			{
				result.setCoursesNumber(null);
			}
			
			result.setRequestOtherMessage(rs.getString(RequestTables.getColumnName(RequestTables.Columns.REQUEST_OTHER_MESSAGE)));
			if(rs.wasNull())
			{
				result.setRequestOtherMessage(null);
			}
			
			result.setRequestStatus(rs.getString(RequestTables.getColumnName(RequestTables.Columns.REQUEST_STATUS)));
			if(rs.wasNull())
			{
				result.setRequestStatus(null);
			}	
			
			
			resultList.add(result);
		}
			
		return resultList;
	}

	
}
