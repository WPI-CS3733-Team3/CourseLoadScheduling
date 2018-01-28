package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.Faculty;

public class FacultyExtractor extends Extractor<List<Faculty>>
{
	@Override
	public List<Faculty> extractData(ResultSet rs) throws SQLException {
		List<Faculty> resultList = new ArrayList<>();

		while(rs.next()) {
			Faculty result = new Faculty();
				
			result.setId(rs.getInt(Faculty.getColumnName(Faculty.Columns.ID)));
			if(rs.wasNull()) {
				result.setId(null);
			}
			result.setFirstName(rs.getString(Faculty.getColumnName(Faculty.Columns.FIRST_NAME)));
			result.setLastName(rs.getString(Faculty.getColumnName(Faculty.Columns.LAST_NAME)));
			result.setEmail(rs.getString(Faculty.getColumnName(Faculty.Columns.EMAIL)));
			result.setFacultyTypeId(rs.getInt(Faculty.getColumnName(Faculty.Columns.FACULTY_TYPE_ID)));
			result.setCreatedAt(rs.getTimestamp(Faculty.getColumnName(Faculty.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(Faculty.getColumnName(Faculty.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(Faculty.getColumnName(Faculty.Columns.DELETED)));
			resultList.add(result);
		}
			
		return resultList;
	}

}
