package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Sections extends Model
{
	// table name
	public static final String TABLE_NAME = "sections";
		
	// column names
	public static enum Columns
	{
		ID,
		NAME,
		CRN,
		TERMS_ID,
		SECTION_TYPE_ID,
		DAYS_ID,
		COURSES_ID,
		START_ID,
		END_ID,
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
		COLUMN_TYPE_MAP.put(Columns.NAME, JDBCType.VARCHAR);
		COLUMN_TYPE_MAP.put(Columns.CRN, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.TERMS_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.SECTION_TYPE_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.DAYS_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.COURSES_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.START_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.END_ID, JDBCType.INTEGER);
		COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		COLUMN_TYPE_MAP.put(Columns.UPDATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		COLUMN_TYPE_MAP.put(Columns.DELETED, JDBCType.BOOLEAN);
	};
	
	// attributes
	
	private Integer id;
	private String name;
	private Integer crn;
	private Integer termsID;
	private Integer sectionTypeID;
	private Integer daysID;
	private Integer coursesID;
	private Integer startID;
	private Integer endID;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCrn() {
		return crn;
	}

	public void setCrn(Integer crn) {
		this.crn = crn;
	}

	public Integer getTermsID() {
		return termsID;
	}

	public void setTermsID(Integer termsID) {
		this.termsID = termsID;
	}

	public Integer getSectionTypeID() {
		return sectionTypeID;
	}

	public void setSectionTypeID(Integer sectionTypeID) {
		this.sectionTypeID = sectionTypeID;
	}

	public Integer getDaysID() {
		return daysID;
	}

	public void setDaysID(Integer daysID) {
		this.daysID = daysID;
	}

	public Integer getCoursesID() {
		return coursesID;
	}

	public void setCoursesID(Integer coursesID) {
		this.coursesID = coursesID;
	}

	public Integer getStartID() {
		return startID;
	}

	public void setStartID(Integer startID) {
		this.startID = startID;
	}

	public Integer getEndID() {
		return endID;
	}

	public void setEndID(Integer endID) {
		this.endID = endID;
	}

	public Instant getCreatedAt(){
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt){
		this.createdAt = createdAt;
	}
	
	public void setCreatedAt(Timestamp createdAt){
		if(createdAt != null)
		{
			this.createdAt = createdAt.toInstant();
		}
	}

	public Instant getUpdatedAt(){
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt){
		this.updatedAt = updatedAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt){
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coursesID == null) ? 0 : coursesID.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((crn == null) ? 0 : crn.hashCode());
		result = prime * result + ((daysID == null) ? 0 : daysID.hashCode());
		result = prime * result + ((deleted == null) ? 0 : deleted.hashCode());
		result = prime * result + ((endID == null) ? 0 : endID.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sectionTypeID == null) ? 0 : sectionTypeID.hashCode());
		result = prime * result + ((startID == null) ? 0 : startID.hashCode());
		result = prime * result + ((termsID == null) ? 0 : termsID.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sections other = (Sections) obj;
		if (coursesID == null) {
			if (other.coursesID != null)
				return false;
		} else if (!coursesID.equals(other.coursesID))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (crn == null) {
			if (other.crn != null)
				return false;
		} else if (!crn.equals(other.crn))
			return false;
		if (daysID == null) {
			if (other.daysID != null)
				return false;
		} else if (!daysID.equals(other.daysID))
			return false;
		if (deleted == null) {
			if (other.deleted != null)
				return false;
		} else if (!deleted.equals(other.deleted))
			return false;
		if (endID == null) {
			if (other.endID != null)
				return false;
		} else if (!endID.equals(other.endID))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sectionTypeID == null) {
			if (other.sectionTypeID != null)
				return false;
		} else if (!sectionTypeID.equals(other.sectionTypeID))
			return false;
		if (startID == null) {
			if (other.startID != null)
				return false;
		} else if (!startID.equals(other.startID))
			return false;
		if (termsID == null) {
			if (other.termsID != null)
				return false;
		} else if (!termsID.equals(other.termsID))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sections [id=" + id + ", name=" + name + ", crn=" + crn + ", termsID=" + termsID + ", sectionTypeID="
				+ sectionTypeID + ", daysID=" + daysID + ", coursesID=" + coursesID + ", startID=" + startID
				+ ", endID=" + endID + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deleted=" + deleted
				+ "]";
	}	
}
