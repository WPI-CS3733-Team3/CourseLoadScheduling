package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseInfo {
		// table name
		public static final String TABLE_NAME = "courses_info";
			
		// column names
		public static enum Columns
		{
			COURSES_TITLE,
			COURSES_NUMBER,
			FREQUENCY,
			FREQUENCY_ID,
			COURSES_ID
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
			COLUMN_TYPE_MAP.put(Columns.COURSES_ID, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.COURSES_NUMBER, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.COURSES_TITLE, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.FREQUENCY_ID, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.FREQUENCY, JDBCType.VARCHAR);

		};
		
		// attributes
		private Integer coursesId;
		private String coursesNumber;
		private String coursesTitle;
		private Integer frequencyId;
		private String frequency;

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

		public Integer getCoursesId() {
			return coursesId;
		}

		public void setCoursesId(Integer coursesId) {
			this.coursesId = coursesId;
		}

		public Integer getFrequencyId() {
			return frequencyId;
		}

		public void setFrequencyId(Integer frequencyId) {
			this.frequencyId = frequencyId;
		}

		public String getCoursesNumber() {
			return coursesNumber;
		}

		public void setCoursesNumber(String coursesNumber) {
			this.coursesNumber = coursesNumber;
		}

		public String getCoursesTitle() {
			return coursesTitle;
		}

		public void setCoursesTitle(String coursesTitle) {
			this.coursesTitle = coursesTitle;
		}

		public String getFrequency() {
			return frequency;
		}

		public void setFrequency(String frequency) {
			this.frequency = frequency;
		}

		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((coursesId == null) ? 0 : coursesId.hashCode());
			result = prime * result + ((coursesNumber == null) ? 0 : coursesNumber.hashCode());
			result = prime * result + ((coursesTitle == null) ? 0 : coursesTitle.hashCode());
			result = prime * result + ((frequency == null) ? 0 : frequency.hashCode());
			result = prime * result + ((frequencyId == null) ? 0 : frequencyId.hashCode());
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
			if (!(obj instanceof CourseInfo)) {
				return false;
			}
			CourseInfo other = (CourseInfo) obj;
			if (coursesId == null) {
				if (other.coursesId != null) {
					return false;
				}
			} else if (!coursesId.equals(other.coursesId)) {
				return false;
			}
			if (coursesNumber == null) {
				if (other.coursesNumber != null) {
					return false;
				}
			} else if (!coursesNumber.equals(other.coursesNumber)) {
				return false;
			}
			if (coursesTitle == null) {
				if (other.coursesTitle != null) {
					return false;
				}
			} else if (!coursesTitle.equals(other.coursesTitle)) {
				return false;
			}
			if (frequency == null) {
				if (other.frequency != null) {
					return false;
				}
			} else if (!frequency.equals(other.frequency)) {
				return false;
			}
			if (frequencyId == null) {
				if (other.frequencyId != null) {
					return false;
				}
			} else if (!frequencyId.equals(other.frequencyId)) {
				return false;
			}
			return true;
		}

		
		@Override
		public String toString() {
			return "CourseInfo [coursesId=" + coursesId + ", coursesNumber=" + coursesNumber + ", coursesTitle="
					+ coursesTitle + ", frequencyId=" + frequencyId + ", frequency=" + frequency + "]";
		}
	
}
