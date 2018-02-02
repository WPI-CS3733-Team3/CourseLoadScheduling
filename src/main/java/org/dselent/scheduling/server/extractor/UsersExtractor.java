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
			
			result.setUserName(rs.getString(User.getColumnName(User.Columns.USER_NAME)));
			result.setFirstName(rs.getString(User.getColumnName(User.Columns.FIRST_NAME)));
			result.setLastName(rs.getString(User.getColumnName(User.Columns.LAST_NAME)));
			result.setEmail(rs.getString(User.getColumnName(User.Columns.EMAIL)));
			result.setEncryptedPassword(rs.getString(User.getColumnName(User.Columns.ENCRYPTED_PASSWORD)));
			result.setSalt(rs.getString(User.getColumnName(User.Columns.SALT)));
			
			result.setUserStateId(rs.getInt(User.getColumnName(User.Columns.USER_STATE_ID)));
			
			if(rs.wasNull())
			{
				result.setUserStateId(null);
			}
			
			result.setCreatedAt(rs.getTimestamp(User.getColumnName(User.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(User.getColumnName(User.Columns.UPDATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}