package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.ExampleUser;

public class ExampleUsersExtractor extends Extractor<List<ExampleUser>>
{
	@Override
	public List<ExampleUser> extractData(ResultSet rs) throws SQLException
	{
		List<ExampleUser> resultList = new ArrayList<>();

		while(rs.next())
		{
			ExampleUser result = new ExampleUser();
				
			result.setId(rs.getInt(ExampleUser.getColumnName(ExampleUser.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setUserName(rs.getString(ExampleUser.getColumnName(ExampleUser.Columns.USER_NAME)));
			result.setFirstName(rs.getString(ExampleUser.getColumnName(ExampleUser.Columns.FIRST_NAME)));
			result.setLastName(rs.getString(ExampleUser.getColumnName(ExampleUser.Columns.LAST_NAME)));
			result.setEmail(rs.getString(ExampleUser.getColumnName(ExampleUser.Columns.EMAIL)));
			result.setEncryptedPassword(rs.getString(ExampleUser.getColumnName(ExampleUser.Columns.ENCRYPTED_PASSWORD)));
			result.setSalt(rs.getString(ExampleUser.getColumnName(ExampleUser.Columns.SALT)));
			
			result.setUserStateId(rs.getInt(ExampleUser.getColumnName(ExampleUser.Columns.USER_STATE_ID)));
			
			if(rs.wasNull())
			{
				result.setUserStateId(null);
			}
			
			result.setCreatedAt(rs.getTimestamp(ExampleUser.getColumnName(ExampleUser.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(ExampleUser.getColumnName(ExampleUser.Columns.UPDATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}