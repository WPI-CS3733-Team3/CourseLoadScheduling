package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Terms;

public class TermsExtractor extends Extractor<List<Terms>>{

	@Override
	public List<Terms> extractData(ResultSet rs) throws SQLException
	{
		List<Terms> resultList = new ArrayList<>();

		while(rs.next())
		{
			Terms result = new Terms();

			result.setId(rs.getInt(Terms.getColumnName(Terms.Columns.ID)));

			if(rs.wasNull())
			{
				result.setId(null);
			}

			result.setName(rs.getString(Terms.getColumnName(Terms.Columns.NAME)));

			resultList.add(result);
		}

		return resultList;
	}

}