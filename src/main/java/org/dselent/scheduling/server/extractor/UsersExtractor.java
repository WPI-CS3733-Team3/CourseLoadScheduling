package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.User;

public class UsersExtractor extends Extractor<List<User>>
{
	@Override
	public List<User> extractData(ResultSet rs) throws SQLException
	{
		List<User> resultList = new ArrayList<>();

		while(rs.next())
		{
			User result = new User();
				
			result.setId(rs.getInt(User.getColumnName(User.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setAccountTypeId(rs.getInt(User.getColumnName(User.Columns.ACCOUNT_TYPE_ID)));
			if(rs.wasNull())
			{
				result.setAccountTypeId(null);
			}
			
			result.setFacultyId(rs.getInt(User.getColumnName(User.Columns.FACULTY_ID)));
			if(rs.wasNull())
			{
				result.setFacultyId(null);
			}
			
			result.setEncryptedPassword(rs.getString(User.getColumnName(User.Columns.ENCRYPTED_PASSWORD)));
			result.setPasswordSalt(rs.getString(User.getColumnName(User.Columns.PASSWORD_SALT)));
			result.setCreatedAt(rs.getTimestamp(User.getColumnName(User.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(User.getColumnName(User.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(User.getColumnName(User.Columns.DELETED)));
			if(rs.wasNull())
			{
				result.setDeleted(null);
			}
			
			resultList.add(result);
		}
			
		return resultList;
	}

}
