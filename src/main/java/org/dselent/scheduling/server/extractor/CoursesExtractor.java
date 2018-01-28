package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Courses;

public class CoursesExtractor extends Extractor<List<Courses>>
{
	@Override
	public List<Courses> extractData(ResultSet rs) throws SQLException
	{
		List<Courses> resultList = new ArrayList<>();

		while(rs.next())
		{
			Courses result = new Courses();
				
			result.setId(rs.getInt(Courses.getColumnName(Courses.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setTitle(rs.getString(Courses.getColumnName(Courses.Columns.TITLE)));
			result.setNumber(rs.getString(Courses.getColumnName(Courses.Columns.NUMBER)));
			result.setFrequencyID(rs.getInt(Courses.getColumnName(Courses.Columns.FREQUENCY_ID)));
			result.setCreatedAt(rs.getTimestamp(Courses.getColumnName(Courses.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(Courses.getColumnName(Courses.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(Courses.getColumnName(Courses.Columns.DELETED)));
		
			resultList.add(result);
		}
			
		return resultList;
	}

}
