package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Group3User;

public class Group3UsersExtractor extends Extractor<List<Group3User>>
{
	@Override
	public List<Group3User> extractData(ResultSet rs) throws SQLException
	{
		List<Group3User> resultList = new ArrayList<>();

		while(rs.next())
		{
			Group3User result = new Group3User();
				
			result.setId(rs.getInt(Group3User.getColumnName(Group3User.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setAccountTypeId(rs.getInt(Group3User.getColumnName(Group3User.Columns.ACCOUNT_TYPE_ID)));
			result.setFacultyId(rs.getInt(Group3User.getColumnName(Group3User.Columns.FACULTY_ID)));
			result.setEncryptedPassword(rs.getString(Group3User.getColumnName(Group3User.Columns.ENCRYPTED_PASSWORD)));
			result.setPasswordSalt(rs.getString(Group3User.getColumnName(Group3User.Columns.PASSWORD_SALT)));
			result.setCreatedAt(rs.getTimestamp(Group3User.getColumnName(Group3User.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(Group3User.getColumnName(Group3User.Columns.UPDATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(Group3User.getColumnName(Group3User.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
