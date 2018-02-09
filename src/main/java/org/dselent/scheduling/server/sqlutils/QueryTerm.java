package org.dselent.scheduling.server.sqlutils;

/**
 * Class for convenience for where clauses
 * Originally intended for simple single table queries
 * 
 * Example 1:
 * QueryTerm1:
 * 
 * SELECT * FROM users WHERE id = 1
 * LogicalOperator = NULL
 * ComparisonOperator = EQUAL
 * value = 1
 * 
 * 
 * Example 2:
 * SELECT * FROM users WHERE id = 1 AND user_name = 'xxx'
 * 
 * QueryTerm1:
 * LogicalOperator = NULL
 * ComparisonOperator = EQUAL
 * value = 1
 * 
 * QueryTerm2:
 * LogicalOperator = AND
 * ComparisonOperator = EQUAL
 * value = xxx
 */
public class QueryTerm
{
	private String columnName;
	private ComparisonOperator comparisonOperator;
	private Object value;
	private LogicalOperator logicalOperator;
	
	public QueryTerm()
	{
		columnName = null;
		comparisonOperator = null;
		value = null;
		logicalOperator = null;
	}

	public QueryTerm(String columnName, ComparisonOperator comparisonOperator, Object value, LogicalOperator logicalOperator)
	{
		this.columnName = columnName;
		this.comparisonOperator = comparisonOperator;
		this.value = value;
		this.logicalOperator = logicalOperator;
	}

	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}

	public ComparisonOperator getComparisonOperator()
	{
		return comparisonOperator;
	}

	public void setComparisonOperator(ComparisonOperator comparisonOperator)
	{
		this.comparisonOperator = comparisonOperator;
	}

	public Object getValue()
	{
		return value;
	}

	public void setValue(Object value)
	{
		this.value = value;
	}

	public LogicalOperator getLogicalOperator()
	{
		return logicalOperator;
	}

	public void setLogicalOperator(LogicalOperator logicalOperator)
	{
		this.logicalOperator = logicalOperator;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((comparisonOperator == null) ? 0 : comparisonOperator.hashCode());
		result = prime * result + ((logicalOperator == null) ? 0 : logicalOperator.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof QueryTerm))
		{
			return false;
		}
		QueryTerm other = (QueryTerm) obj;
		if (columnName == null)
		{
			if (other.columnName != null)
			{
				return false;
			}
		}
		else if (!columnName.equals(other.columnName))
		{
			return false;
		}
		if (comparisonOperator != other.comparisonOperator)
		{
			return false;
		}
		if (logicalOperator != other.logicalOperator)
		{
			return false;
		}
		if (value == null)
		{
			if (other.value != null)
			{
				return false;
			}
		}
		else if (!value.equals(other.value))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("QueryTerm [columnName=");
		builder.append(columnName);
		builder.append(", comparisonOperator=");
		builder.append(comparisonOperator);
		builder.append(", value=");
		builder.append(value);
		builder.append(", logicalOperator=");
		builder.append(logicalOperator);
		builder.append("]");
		return builder.toString();
	}
}
