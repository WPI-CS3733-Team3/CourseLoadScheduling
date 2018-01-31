package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ScheduleHistory extends Model{
	// table name
		public static final String TABLE_NAME = "schedule_history";
			
		// column names
		public static enum Columns
		{
			ID,
			FACULTY_ID,
			SECTIONS_ID,
			CREATED_AT,
			UPDATED_AT,
			DELETED
		}
		
		// enum list
		private static final List<Columns> COLUMN_LIST = new ArrayList<>();
		
		// type mapping
		private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();
		
		static
		{
			for(Columns key : Columns.values())
			{
				COLUMN_LIST.add(key);
			}
			
			COLUMN_TYPE_MAP.put(Columns.ID, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.FACULTY_ID, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.SECTIONS_ID, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
			COLUMN_TYPE_MAP.put(Columns.UPDATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
			COLUMN_TYPE_MAP.put(Columns.DELETED, JDBCType.BOOLEAN);
		};
		
		// attributes
		
		private Integer id;
		private Integer facultyID;
		private Integer sectionsID;
		private Instant createdAt;
		private Instant updatedAt;
		private Boolean deleted;

		// methods
			
		public static JDBCType getColumnType(Columns column)
		{
			return COLUMN_TYPE_MAP.get(column);
		}
		
		public static String getColumnName(Columns column)
		{
			return column.toString().toLowerCase();
		}
		
		public static List<String> getColumnNameList()
		{
			List<String> columnNameList = new ArrayList<>();
			
			for(Columns column : COLUMN_LIST)
			{
				columnNameList.add(getColumnName(column));
			}
			
			return columnNameList;
		}

		//getters and setters
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getFacultyID() {
			return facultyID;
		}

		public void setFacultyID(Integer facultyID) {
			this.facultyID = facultyID;
		}

		public Integer getSectionsID() {
			return sectionsID;
		}

		public void setSectionsID(Integer sectionsID) {
			this.sectionsID = sectionsID;
		}

		public Instant getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Instant createdAt) {
			this.createdAt = createdAt;
		}
		
		public void setCreatedAt(Timestamp createdAt)
		{
			if(createdAt != null)
			{
				this.createdAt = createdAt.toInstant();
			}
		}

		public Instant getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Instant updatedAt) {
			this.updatedAt = updatedAt;
		}
		
		public void setUpdatedAt(Timestamp updatedAt)
		{
			if(updatedAt != null)
			{
				this.updatedAt = updatedAt.toInstant();
			}
		}

		public Boolean getDeleted() {
			return deleted;
		}

		public void setDeleted(Boolean deleted) {
			this.deleted = deleted;
		}

		//hashCode and equals
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
			result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
			result = prime * result + ((facultyID == null) ? 0 : facultyID.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((sectionsID == null) ? 0 : sectionsID.hashCode());
			result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof ScheduleHistory)) {
				return false;
			}
			ScheduleHistory other = (ScheduleHistory) obj;
			if (createdAt == null) {
				if (other.createdAt != null) {
					return false;
				}
			} else if (!createdAt.equals(other.createdAt)) {
				return false;
			}
			if (deleted == null) {
				if (other.deleted != null) {
					return false;
				}
			} else if (!deleted.equals(other.deleted)) {
				return false;
			}
			if (facultyID == null) {
				if (other.facultyID != null) {
					return false;
				}
			} else if (!facultyID.equals(other.facultyID)) {
				return false;
			}
			if (id == null) {
				if (other.id != null) {
					return false;
				}
			} else if (!id.equals(other.id)) {
				return false;
			}
			if (sectionsID == null) {
				if (other.sectionsID != null) {
					return false;
				}
			} else if (!sectionsID.equals(other.sectionsID)) {
				return false;
			}
			if (updatedAt == null) {
				if (other.updatedAt != null) {
					return false;
				}
			} else if (!updatedAt.equals(other.updatedAt)) {
				return false;
			}
			return true;
		}

		//toString
		@Override
		public String toString() {
			return "ScheduleHistory [id=" + id + ", facultyID=" + facultyID + ", sectionsID=" + sectionsID
					+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deleted=" + deleted + "]";
		}	
	
}
