package org.dselent.scheduling.server.sqlutils;

import java.util.List;

import org.dselent.scheduling.server.miscellaneous.Pair;
import org.springframework.util.StringUtils;

public class QueryStringBuilder
{
	private static final String INSERT_PIECE1 = "INSERT INTO ";
	private static final String INSERT_PIECE2 = "(";
	private static final String INSERT_PIECE3 = ") VALUES(";
	private static final String INSERT_PIECE4 = ");";
	
	private static final String SELECT_PIECE1 = "SELECT ";
	private static final String SELECT_PIECE2 = " FROM ";
	private static final String SELECT_PIECE3 = " WHERE ";
	private static final String SELECT_PIECE4 = " ORDER BY ";
	
	private static final String UPDATE_PIECE1 = "UPDATE ";
	private static final String UPDATE_PIECE2 = " SET ";
	private static final String UPDATE_PIECE3 = ", ";
	private static final String UPDATE_PIECE4 = " = ?";
	private static final String UPDATE_PIECE5 = " WHERE ";
	
	private static final String DELETE_PIECE1 = "DELETE FROM ";
	private static final String DELETE_PIECE2 = " WHERE ";
	
	/*
	 * Assumes valid enum/table naming conventions
	 */
	public static String generateInsertString(String tableName, List<String> columnNames)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(INSERT_PIECE1);
		sb.append(tableName);
		sb.append(INSERT_PIECE2);
		
		for(String columnName : columnNames)
		{
			sb.append(columnName);
			sb.append(", ");
		}
		
		if(!columnNames.isEmpty())
		{
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
		}
		
		sb.append(INSERT_PIECE3);
		
		for(String columnName : columnNames)
		{					
			String variableName = convertColumnName(columnName, true);
			sb.append(variableName);
			sb.append(", ");
		}
		
		if(!columnNames.isEmpty())
		{
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
		}
		
		sb.append(INSERT_PIECE4);
		
		return sb.toString();
	}
	
	public static String generateSelectString(String tableName, List<String> columnNames, List<QueryTerm> queryTermList, List<Pair<String, ColumnOrder>> orderByList)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(SELECT_PIECE1);
		
		for(String columnName : columnNames)
		{
			sb.append(columnName);
			sb.append(", ");
		}
		
		if(!columnNames.isEmpty())
		{
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
		}
		
		sb.append(SELECT_PIECE2);
		sb.append(tableName);
		
		if(!queryTermList.isEmpty())
		{
			sb.append(SELECT_PIECE3);
			
			for(QueryTerm queryTerm : queryTermList)
			{
				if(queryTerm.getLogicalOperator() != null)
				{
					sb.append(" ");
					sb.append(queryTerm.getLogicalOperator().getStringFormat());
					sb.append(" ");
				}
				
				sb.append(queryTerm.getColumnName());
				sb.append(" ");
				sb.append(queryTerm.getComparisonOperator().getStringFormat());
				sb.append(" ");
				sb.append("?");
			}
		}
		
		if(!orderByList.isEmpty())
		{
			sb.append(SELECT_PIECE4);
			
			for(Pair<String, ColumnOrder> orderPair : orderByList)
			{
				sb.append(orderPair.getValue1());
				sb.append(" ");
				sb.append(orderPair.getValue2().toString());
				sb.append(", ");
			}
			
			sb.deleteCharAt(sb.length()-1);
			sb.deleteCharAt(sb.length()-1);
		}
		
		sb.append(";");
		
		return sb.toString();
	}
	
	public static String generateUpdateString(String tableName, String columnName, List<QueryTerm> queryTermList)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(UPDATE_PIECE1);
		sb.append(tableName);
		sb.append(UPDATE_PIECE2);
		sb.append(columnName);
		sb.append(UPDATE_PIECE4);
	
		
		if(!queryTermList.isEmpty())
		{
			sb.append(UPDATE_PIECE5);
			
			for(QueryTerm queryTerm : queryTermList)
			{
				if(queryTerm.getLogicalOperator() != null)
				{
					sb.append(" ");
					sb.append(queryTerm.getLogicalOperator().getStringFormat());
					sb.append(" ");
				}
				
				sb.append(queryTerm.getColumnName());
				sb.append(" ");
				sb.append(queryTerm.getComparisonOperator().getStringFormat());
				sb.append(" ");
				sb.append("?");
			}
		}
				
		sb.append(";");
		
		return sb.toString();
	}
	
	public static String generateUpdateString(String tableName, List<String> columnNameList, List<QueryTerm> queryTermList)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(UPDATE_PIECE1);
		sb.append(tableName);
		
		for(int i=0; i<columnNameList.size(); i++)
		{
			String columnName = columnNameList.get(i);
			
			if(i == 0)
			{
				sb.append(UPDATE_PIECE2);
			}
			else
			{
				sb.append(UPDATE_PIECE3);
			}
			
			sb.append(columnName);
			sb.append(UPDATE_PIECE4);
		}
			
		
		if(!queryTermList.isEmpty())
		{
			sb.append(UPDATE_PIECE5);
			
			for(QueryTerm queryTerm : queryTermList)
			{
				if(queryTerm.getLogicalOperator() != null)
				{
					sb.append(" ");
					sb.append(queryTerm.getLogicalOperator().getStringFormat());
					sb.append(" ");
				}
				
				sb.append(queryTerm.getColumnName());
				sb.append(" ");
				sb.append(queryTerm.getComparisonOperator().getStringFormat());
				sb.append(" ");
				sb.append("?");
			}
		}
				
		sb.append(";");
		
		return sb.toString();
	}
	
	public static String generateDeleteString(String tableName, List<QueryTerm> queryTermList)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(DELETE_PIECE1);
		sb.append(tableName);
		
		if(!queryTermList.isEmpty())
		{
			sb.append(DELETE_PIECE2);
			
			for(QueryTerm queryTerm : queryTermList)
			{
				if(queryTerm.getLogicalOperator() != null)
				{
					sb.append(" ");
					sb.append(queryTerm.getLogicalOperator().getStringFormat());
					sb.append(" ");
				}
				
				sb.append(queryTerm.getColumnName());
				sb.append(" ");
				sb.append(queryTerm.getComparisonOperator().getStringFormat());
				sb.append(" ");
				sb.append("?");
			}
		}
				
		sb.append(";");
		
		return sb.toString();
	}
	
	/**
	 * Converts a column name with underscores to a variable name without underscores
	 * First letter of each word is capitalized
	 * Table name format -> JDBC parameter name format
	 * 
	 * @param columnName The name of the column
	 * @param addSemicolon If a semicolon should be added to the front of the parameter
	 * For the template query string, the semicolon should be added
	 * For the parameter mapping, it should not
	 * @return The corresponding variable name
	 */
	public static String convertColumnName(String columnName, boolean addSemicolon)
	{
		String[] variableNameParts = columnName.split("_");
		String variableName = variableNameParts[0];
		
		for(int i=1; i<variableNameParts.length; i++)
		{
			variableName = variableName.concat(StringUtils.capitalize(variableNameParts[i]));
		}
		
		if(addSemicolon)
		{
			variableName = ":".concat(variableName);
		}
		
		return variableName;
	}
}
