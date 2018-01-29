package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.CourseLoadType;

public class CourseLoadTypeExtractor extends Extractor<List<CourseLoadType>>{

	@Override
	public List<CourseLoadType> extractData(ResultSet rs) throws SQLException
	{
		List<CourseLoadType> resultList = new ArrayList<>();

		while(rs.next())
		{
			CourseLoadType result = new CourseLoadType();

			result.setId(rs.getInt(CourseLoadType.getColumnName(CourseLoadType.Columns.ID)));

			if(rs.wasNull())
			{
				result.setId(null);
			}

			result.setName(rs.getString(CourseLoadType.getColumnName(CourseLoadType.Columns.NAME)));

			resultList.add(result);
		}

		return resultList;
	}

}