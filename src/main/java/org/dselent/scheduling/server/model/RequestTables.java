package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestTables extends Model{
	// table name
	public static final String TABLE_NAME = "request_tables";

	// column names
	public static enum Columns
	{
		REQUESTS_ID,
		REQUESTS_USER_ID,
		TERMS_NAME,
		START_TIME,
		END_TIME,
		COURSES_TITLE,
		COURSES_NUMBER,
		REQUEST_OTHER_MESSAGE,
		REQUEST_STATUS,
		FACULTY_FIRST_NAME,
		FACULTY_LAST_NAME,
		TERMS_ID,
		START_TIME_ID,
		END_TIME_ID,
		COURSES_ID,
		REQUEST_STATUS_ID
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

		COLUMN_TYPE_MAP.put(Columns.REQUESTS_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.REQUESTS_USER_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.TERMS_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.START_TIME, JDBCType.TIME);
		COLUMN_TYPE_MAP.put(Columns.END_TIME, JDBCType.TIME);
		COLUMN_TYPE_MAP.put(Columns.COURSES_TITLE, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.COURSES_NUMBER, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.REQUEST_OTHER_MESSAGE, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.REQUEST_STATUS, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.FACULTY_FIRST_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.FACULTY_LAST_NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.TERMS_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.START_TIME_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.END_TIME_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.COURSES_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.REQUEST_STATUS_ID, JDBCType.INTEGER);
	};

	// attributes

	private Integer requestsId;
	private Integer requestsUserId;
	private String termsName;
	private Time startTime;
	private Time endTime;
	private String coursesTitle;
	private String coursesNumber;
	private String requestOtherMessage;
	private String requestStatus;
	private String facultyFirstName;
	private String facultyLastName;
	private Integer termsId;
	private Integer startTimeId;
	private Integer endTimeId;
	private Integer coursesId;
	private Integer requestStatusId;

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

	public Integer getTermsId() {
		return termsId;
	}

	public void setTermsId(Integer termsId) {
		this.termsId = termsId;
	}

	public Integer getStartTimeId() {
		return startTimeId;
	}

	public void setStartTimeId(Integer startTimeId) {
		this.startTimeId = startTimeId;
	}

	public Integer getEndTimeId() {
		return endTimeId;
	}

	public void setEndTimeId(Integer endTimeId) {
		this.endTimeId = endTimeId;
	}

	public Integer getCoursesId() {
		return coursesId;
	}

	public void setCoursesId(Integer coursesId) {
		this.coursesId = coursesId;
	}

	public Integer getRequestStatusId() {
		return requestStatusId;
	}

	public void setRequestStatusId(Integer requestStatusId) {
		this.requestStatusId = requestStatusId;
	}

	public Integer getRequestsId() {
		return requestsId;
	}
	
	public void setRequestsId(Integer requestsId) {
		this.requestsId = requestsId;
	}

	public Integer getRequestsUserId() {
		return requestsUserId;
	}

	public void setRequestsUserId(Integer requestsUserId) {
		this.requestsUserId = requestsUserId;
	}

	public String getTermsName() {
		return termsName;
	}

	public void setTermsName(String termsName) {
		this.termsName = termsName;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getCoursesTitle() {
		return coursesTitle;
	}

	public void setCoursesTitle(String coursesTitle) {
		this.coursesTitle = coursesTitle;
	}

	public String getCoursesNumber() {
		return coursesNumber;
	}

	public void setCoursesNumber(String coursesNumber) {
		this.coursesNumber = coursesNumber;
	}

	public String getRequestOtherMessage() {
		return requestOtherMessage;
	}

	public void setRequestOtherMessage(String requestOtherMessage) {
		this.requestOtherMessage = requestOtherMessage;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coursesId == null) ? 0 : coursesId.hashCode());
		result = prime * result + ((coursesNumber == null) ? 0 : coursesNumber.hashCode());
		result = prime * result + ((coursesTitle == null) ? 0 : coursesTitle.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((endTimeId == null) ? 0 : endTimeId.hashCode());
		result = prime * result + ((facultyFirstName == null) ? 0 : facultyFirstName.hashCode());
		result = prime * result + ((facultyLastName == null) ? 0 : facultyLastName.hashCode());
		result = prime * result + ((requestOtherMessage == null) ? 0 : requestOtherMessage.hashCode());
		result = prime * result + ((requestStatus == null) ? 0 : requestStatus.hashCode());
		result = prime * result + ((requestStatusId == null) ? 0 : requestStatusId.hashCode());
		result = prime * result + ((requestsId == null) ? 0 : requestsId.hashCode());
		result = prime * result + ((requestsUserId == null) ? 0 : requestsUserId.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((startTimeId == null) ? 0 : startTimeId.hashCode());
		result = prime * result + ((termsId == null) ? 0 : termsId.hashCode());
		result = prime * result + ((termsName == null) ? 0 : termsName.hashCode());
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
		if (!(obj instanceof RequestTables)) {
			return false;
		}
		RequestTables other = (RequestTables) obj;
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
		if (endTime == null) {
			if (other.endTime != null) {
				return false;
			}
		} else if (!endTime.equals(other.endTime)) {
			return false;
		}
		if (endTimeId == null) {
			if (other.endTimeId != null) {
				return false;
			}
		} else if (!endTimeId.equals(other.endTimeId)) {
			return false;
		}
		if (facultyFirstName == null) {
			if (other.facultyFirstName != null) {
				return false;
			}
		} else if (!facultyFirstName.equals(other.facultyFirstName)) {
			return false;
		}
		if (facultyLastName == null) {
			if (other.facultyLastName != null) {
				return false;
			}
		} else if (!facultyLastName.equals(other.facultyLastName)) {
			return false;
		}
		if (requestOtherMessage == null) {
			if (other.requestOtherMessage != null) {
				return false;
			}
		} else if (!requestOtherMessage.equals(other.requestOtherMessage)) {
			return false;
		}
		if (requestStatus == null) {
			if (other.requestStatus != null) {
				return false;
			}
		} else if (!requestStatus.equals(other.requestStatus)) {
			return false;
		}
		if (requestStatusId == null) {
			if (other.requestStatusId != null) {
				return false;
			}
		} else if (!requestStatusId.equals(other.requestStatusId)) {
			return false;
		}
		if (requestsId == null) {
			if (other.requestsId != null) {
				return false;
			}
		} else if (!requestsId.equals(other.requestsId)) {
			return false;
		}
		if (requestsUserId == null) {
			if (other.requestsUserId != null) {
				return false;
			}
		} else if (!requestsUserId.equals(other.requestsUserId)) {
			return false;
		}
		if (startTime == null) {
			if (other.startTime != null) {
				return false;
			}
		} else if (!startTime.equals(other.startTime)) {
			return false;
		}
		if (startTimeId == null) {
			if (other.startTimeId != null) {
				return false;
			}
		} else if (!startTimeId.equals(other.startTimeId)) {
			return false;
		}
		if (termsId == null) {
			if (other.termsId != null) {
				return false;
			}
		} else if (!termsId.equals(other.termsId)) {
			return false;
		}
		if (termsName == null) {
			if (other.termsName != null) {
				return false;
			}
		} else if (!termsName.equals(other.termsName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "RequestTables [requestsId=" + requestsId + ", requestsUserId=" + requestsUserId + ", termsName="
				+ termsName + ", startTime=" + startTime + ", endTime=" + endTime + ", coursesTitle=" + coursesTitle
				+ ", coursesNumber=" + coursesNumber + ", requestOtherMessage=" + requestOtherMessage
				+ ", requestStatus=" + requestStatus + ", facultyFirstName=" + facultyFirstName + ", facultyLastName="
				+ facultyLastName + ", termsId=" + termsId + ", startTimeId=" + startTimeId + ", endTimeId=" + endTimeId
				+ ", coursesId=" + coursesId + ", requestStatusId=" + requestStatusId + "]";
	}

}
