package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.AccountInfo;

public class AccountInfoExtractor extends Extractor<List<AccountInfo>>{

	@Override
	public List<AccountInfo> extractData(ResultSet rs) throws SQLException
	{
		List<AccountInfo> resultList = new ArrayList<>();

		while(rs.next())
		{
			AccountInfo result = new AccountInfo();
		
			result.setFacultyFirstName(rs.getString(AccountInfo.getColumnName(AccountInfo.Columns.FACULTY_FIRST_NAME)));
			if(rs.wasNull())
			{
				result.setFacultyFirstName(null);
			}
			
			result.setFacultyLastName(rs.getString(AccountInfo.getColumnName(AccountInfo.Columns.FACULTY_LAST_NAME)));
			if(rs.wasNull())
			{
				result.setFacultyLastName(null);
			}
			
			result.setFacultyEmail(rs.getString(AccountInfo.getColumnName(AccountInfo.Columns.FACULTY_EMAIL)));
			if(rs.wasNull())
			{
				result.setFacultyEmail(null);
			}
			
			result.setFacultyType(rs.getString(AccountInfo.getColumnName(AccountInfo.Columns.FACULTY_TYPE)));
			if(rs.wasNull())
			{
				result.setFacultyType(null);
			}
			
			result.setAccountType(rs.getString(AccountInfo.getColumnName(AccountInfo.Columns.ACCOUNT_TYPE)));
			if(rs.wasNull())
			{
				result.setAccountType(null);
			}
			
			resultList.add(result);
		}
		
		return resultList;
	}
	
}
