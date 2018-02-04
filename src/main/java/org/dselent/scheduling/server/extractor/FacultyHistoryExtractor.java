package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.FacultyHistory;

public class FacultyHistoryExtractor extends Extractor<List<FacultyHistory>> {
	@Override
	public List<FacultyHistory> extractData(ResultSet rs) throws SQLException {
		List<FacultyHistory> resultList = new ArrayList<>();

		while(rs.next()) {
			FacultyHistory result = new FacultyHistory();
				
			result.setId(rs.getInt(FacultyHistory.getColumnName(FacultyHistory.Columns.ID)));
			if(rs.wasNull()) {
				result.setId(null);
			}
			result.setFirstName(rs.getString(FacultyHistory.getColumnName(FacultyHistory.Columns.FIRST_NAME)));
			result.setLastName(rs.getString(FacultyHistory.getColumnName(FacultyHistory.Columns.LAST_NAME)));
			result.setEmail(rs.getString(FacultyHistory.getColumnName(FacultyHistory.Columns.EMAIL)));
			result.setFacultyTypeId(rs.getInt(FacultyHistory.getColumnName(FacultyHistory.Columns.FACULTY_TYPE_ID)));
			if(rs.wasNull())
			{
				result.setFacultyTypeId(null);
			}
			
			result.setCreatedAt(rs.getTimestamp(FacultyHistory.getColumnName(FacultyHistory.Columns.CREATED_AT)));
			result.setUpdatedAt(rs.getTimestamp(FacultyHistory.getColumnName(FacultyHistory.Columns.UPDATED_AT)));
			result.setDeleted(rs.getBoolean(FacultyHistory.getColumnName(FacultyHistory.Columns.DELETED)));
			if(rs.wasNull())
			{
				result.setDeleted(null);
			}
			
			resultList.add(result);
		}
			
		return resultList;
	}
	
}
