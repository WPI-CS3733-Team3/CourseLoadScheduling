package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.ExampleUsersHistory;

public class ExampleUsersHistoryExtractor extends Extractor<List<ExampleUsersHistory>>
{
	@Override
	public List<ExampleUsersHistory> extractData(ResultSet rs) throws SQLException
	{
		List<ExampleUsersHistory> resultList = new ArrayList<>();

		while(rs.next())
		{
			ExampleUsersHistory result = new ExampleUsersHistory();
				
			result.setId(rs.getInt(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setAccountTypeId(rs.getInt(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.ACCOUNT_TYPE_ID)));
			if(rs.wasNull())
			{
				result.setAccountTypeId(null);
			}
			
			result.setFacultyId(rs.getInt(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.FACULTY_ID)));
			if(rs.wasNull())
			{
				result.setFacultyId(null);
			}
			
			result.setEncryptedPassword(rs.getString(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.ENCRYPTED_PASSWORD)));
			result.setPasswordSalt(rs.getString(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.PASSWORD_SALT)));
			result.setCreatedAt(rs.getTimestamp(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(ExampleUsersHistory.getColumnName(ExampleUsersHistory.Columns.DELETED)));
			if(rs.wasNull())
			{
				result.setDeleted(null);
			}
			
			resultList.add(result);
		}
			
		return resultList;
	}

}
