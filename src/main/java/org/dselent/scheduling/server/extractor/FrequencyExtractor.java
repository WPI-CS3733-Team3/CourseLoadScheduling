package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Frequency;

public class FrequencyExtractor extends Extractor<List<Frequency>>{

	@Override
	public List<Frequency> extractData(ResultSet rs) throws SQLException
	{
		List<Frequency> resultList = new ArrayList<>();

		while(rs.next())
		{
			Frequency result = new Frequency();

			result.setId(rs.getInt(Frequency.getColumnName(Frequency.Columns.ID)));

			if(rs.wasNull())
			{
				result.setId(null);
			}

			result.setFrequency(rs.getString(Frequency.getColumnName(Frequency.Columns.FREQUENCY)));

			resultList.add(result);
		}

		return resultList;
	}

}