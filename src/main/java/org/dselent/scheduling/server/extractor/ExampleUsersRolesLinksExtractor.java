package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.model.ExampleUsersRolesLink;

public class ExampleUsersRolesLinksExtractor extends Extractor<List<ExampleUsersRolesLink>>
{
	@Override
	public List<ExampleUsersRolesLink> extractData(ResultSet rs) throws SQLException
	{
		List<ExampleUsersRolesLink> resultList = new ArrayList<>();

		while(rs.next())
		{
			ExampleUsersRolesLink result = new ExampleUsersRolesLink();
				
			result.setId(rs.getInt(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}

			result.setUserId(rs.getInt(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.USER_ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setRoleId(rs.getInt(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.ROLE_ID)));
			
			if(rs.wasNull())
			{
				result.setRoleId(null);
			}
			
			result.setCreatedAt(rs.getTimestamp(User.getColumnName(User.Columns.CREATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
