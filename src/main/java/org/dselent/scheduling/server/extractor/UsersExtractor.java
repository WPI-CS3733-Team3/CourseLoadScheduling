package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Users;

public class UsersExtractor extends Extractor<List<Users>>
{
	@Override
	public List<Users> extractData(ResultSet rs) throws SQLException
	{
		List<Users> resultList = new ArrayList<>();

		while(rs.next())
		{
			Users result = new Users();
				
			result.setId(rs.getInt(Users.getColumnName(Users.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setAccountTypeId(rs.getInt(Users.getColumnName(Users.Columns.ACCOUNT_TYPE_ID)));
			result.setFacultyId(rs.getInt(Users.getColumnName(Users.Columns.FACULTY_ID)));
			result.setEncryptedPassword(rs.getString(Users.getColumnName(Users.Columns.ENCRYPTED_PASSWORD)));
			result.setPasswordSalt(rs.getString(Users.getColumnName(Users.Columns.PASSWORD_SALT)));
			result.setCreatedAt(rs.getTimestamp(Users.getColumnName(Users.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(Users.getColumnName(Users.Columns.UPDATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(Users.getColumnName(Users.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
