package org.dselent.scheduling.server.extractor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.FacultyType;

public class FacultyTypeExtractor extends Extractor<List<FacultyType>> {

	@Override
	public List<FacultyType> extractData(ResultSet rs) throws SQLException
	{
		List<FacultyType> resultList = new ArrayList<>();

		while(rs.next())
		{
			FacultyType result = new FacultyType();
				
			result.setId(rs.getInt(FacultyType.getColumnName(FacultyType.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setType(rs.getString(FacultyType.getColumnName(FacultyType.Columns.TYPE)));
			resultList.add(result);
		}
			
		return resultList;
	}

}
