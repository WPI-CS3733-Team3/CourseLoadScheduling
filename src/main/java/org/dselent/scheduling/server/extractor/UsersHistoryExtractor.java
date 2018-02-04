package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.UsersHistory;

public class UsersHistoryExtractor extends Extractor<List<UsersHistory>>
{
	@Override
	public List<UsersHistory> extractData(ResultSet rs) throws SQLException
	{
		List<UsersHistory> resultList = new ArrayList<>();

		while(rs.next())
		{
			UsersHistory result = new UsersHistory();
				
			result.setId(rs.getInt(UsersHistory.getColumnName(UsersHistory.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setAccountTypeId(rs.getInt(UsersHistory.getColumnName(UsersHistory.Columns.ACCOUNT_TYPE_ID)));
			if(rs.wasNull())
			{
				result.setAccountTypeId(null);
			}
			
			result.setFacultyId(rs.getInt(UsersHistory.getColumnName(UsersHistory.Columns.FACULTY_ID)));
			if(rs.wasNull())
			{
				result.setFacultyId(null);
			}
			
			result.setEncryptedPassword(rs.getString(UsersHistory.getColumnName(UsersHistory.Columns.ENCRYPTED_PASSWORD)));
			result.setPasswordSalt(rs.getString(UsersHistory.getColumnName(UsersHistory.Columns.PASSWORD_SALT)));
			result.setCreatedAt(rs.getTimestamp(UsersHistory.getColumnName(UsersHistory.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(UsersHistory.getColumnName(UsersHistory.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(UsersHistory.getColumnName(UsersHistory.Columns.DELETED)));
			if(rs.wasNull())
			{
				result.setDeleted(null);
			}
			
			resultList.add(result);
		}
			
		return resultList;
	}

}
