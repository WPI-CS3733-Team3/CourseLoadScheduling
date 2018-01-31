package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StartTime extends Model {
	// table name
	public static final String TABLE_NAME = "start_time";

	// column names
	public static enum Columns
	{
		ID,
		TIME
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
		COLUMN_TYPE_MAP.put(Columns.TIME, JDBCType.TIME);
	};

	// attributes

	private Integer id;
	private Time time;

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

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
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
		if (!(obj instanceof StartTime)) {
			return false;
		}
		StartTime other = (StartTime) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (time == null) {
			if (other.time != null) {
				return false;
			}
		} else if (!time.equals(other.time)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StartTime [id=");
		builder.append(id);
		builder.append(", time=");
		builder.append(time);
		builder.append("]");
		return builder.toString();
	}
	
}