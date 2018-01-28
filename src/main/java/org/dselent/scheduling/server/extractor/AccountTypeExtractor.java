package org.dselent.scheduling.server.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.model.AccountType;
import org.dselent.scheduling.server.model.AccountType.Columns;


public class AccountTypeExtractor extends Extractor<List<AccountType>>{
	@Override
	public List<AccountType> extractData(ResultSet rs) throws SQLException
	{
		List<AccountType> resultList = new ArrayList<>();

		while(rs.next())
		{
			AccountType result = new AccountType();
				
			result.setId(rs.getInt(AccountType.getColumnName(AccountType.Columns.ID)));
			
			if(rs.wasNull())
			{
				result.setId(null);
			}
			
			result.setAccountType(rs.getString(AccountType.getColumnName(AccountType.Columns.ACCOUNT_TYPE)));

			resultList.add(result);
		}
			
		return resultList;
	}

}
