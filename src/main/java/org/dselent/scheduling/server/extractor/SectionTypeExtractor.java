package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.SectionType;

public class SectionTypeExtractor extends Extractor<List<SectionType>>{

	@Override
	public List<SectionType> extractData(ResultSet rs) throws SQLException
	{
		List<SectionType> resultList = new ArrayList<>();

		while(rs.next())
		{
			SectionType result = new SectionType();

			result.setId(rs.getInt(SectionType.getColumnName(SectionType.Columns.ID)));

			if(rs.wasNull())
			{
				result.setId(null);
			}

			result.setType(rs.getString(SectionType.getColumnName(SectionType.Columns.TYPE)));

			resultList.add(result);
		}

		return resultList;
	}

}