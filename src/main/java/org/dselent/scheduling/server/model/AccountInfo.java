package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountInfo {
	// table name
	public static final String TABLE_NAME = "account_info";
		
	// column names
	public static enum Columns
	{
		FACULTY_FIRST_NAME,
		FACULTY_LAST_NAME,
		FACULTY_EMAIL,
		FACULTY_TYPE,
		FACULTY_ID,
		ACCOUNT_TYPE
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
		COLUMN_TYPE_MAP.put(Columns.FACULTY_FIRST_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.FACULTY_LAST_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.FACULTY_EMAIL, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.FACULTY_TYPE, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.ACCOUNT_TYPE, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.FACULTY_ID, JDBCType.INTEGER);

	};
	
	// attributes
	private String facultyFirstName;
	private String facultyLastName;
	private String facultyEmail;
	private Integer facultyId;
	private String facultyType;
	private String accountType;
	
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
	public Integer getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}
	
	public String getFacultyFirstName() {
		return facultyFirstName;
	}

	public void setFacultyFirstName(String facultyFirstName) {
		this.facultyFirstName = facultyFirstName;
	}

	public String getFacultyLastName() {
		return facultyLastName;
	}

	public void setFacultyLastName(String facultyLastName) {
		this.facultyLastName = facultyLastName;
	}

	public String getFacultyEmail() {
		return facultyEmail;
	}

	public void setFacultyEmail(String facultyEmail) {
		this.facultyEmail = facultyEmail;
	}

	public String getFacultyType() {
		return facultyType;
	}

	public void setFacultyType(String facultyType) {
		this.facultyType = facultyType;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((facultyEmail == null) ? 0 : facultyEmail.hashCode());
		result = prime * result + ((facultyFirstName == null) ? 0 : facultyFirstName.hashCode());
		result = prime * result + ((facultyId == null) ? 0 : facultyId.hashCode());
		result = prime * result + ((facultyLastName == null) ? 0 : facultyLastName.hashCode());
		result = prime * result + ((facultyType == null) ? 0 : facultyType.hashCode());
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
		if (!(obj instanceof AccountInfo)) {
			return false;
		}
		AccountInfo other = (AccountInfo) obj;
		if (accountType == null) {
			if (other.accountType != null) {
				return false;
			}
		} else if (!accountType.equals(other.accountType)) {
			return false;
		}
		if (facultyEmail == null) {
			if (other.facultyEmail != null) {
				return false;
			}
		} else if (!facultyEmail.equals(other.facultyEmail)) {
			return false;
		}
		if (facultyFirstName == null) {
			if (other.facultyFirstName != null) {
				return false;
			}
		} else if (!facultyFirstName.equals(other.facultyFirstName)) {
			return false;
		}
		if (facultyId == null) {
			if (other.facultyId != null) {
				return false;
			}
		} else if (!facultyId.equals(other.facultyId)) {
			return false;
		}
		if (facultyLastName == null) {
			if (other.facultyLastName != null) {
				return false;
			}
		} else if (!facultyLastName.equals(other.facultyLastName)) {
			return false;
		}
		if (facultyType == null) {
			if (other.facultyType != null) {
				return false;
			}
		} else if (!facultyType.equals(other.facultyType)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AccountInfo [facultyFirstName=" + facultyFirstName + ", facultyLastName=" + facultyLastName
				+ ", facultyEmail=" + facultyEmail + ", facultyId=" + facultyId + ", facultyType=" + facultyType
				+ ", accountType=" + accountType + "]";
	}


}
