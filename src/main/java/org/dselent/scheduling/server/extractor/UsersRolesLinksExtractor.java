package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Group3User;
import org.dselent.scheduling.server.model.UsersRolesLink;

public class UsersRolesLinksExtractor extends Extractor<List<UsersRolesLink>>
{
	@Override
	public List<UsersRolesLink> extractData(ResultSet rs) throws SQLException
	{
		List<UsersRolesLink> resultList = new ArrayList<>();

		while(rs.next())
		{
			UsersRolesLink result = new UsersRolesLink();
				
			result.setId(rs.getInt(UsersRolesLink.getColumnName(UsersRolesLink.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}

			result.setUserId(rs.getInt(UsersRolesLink.getColumnName(UsersRolesLink.Columns.USER_ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setRoleId(rs.getInt(UsersRolesLink.getColumnName(UsersRolesLink.Columns.ROLE_ID)));
			
			if(rs.wasNull())
			{
				result.setRoleId(null);
			}
			
			result.setCreatedAt(rs.getTimestamp(Group3User.getColumnName(Group3User.Columns.CREATED_AT)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
