package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.ExampleUsersDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.ExampleUser;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class UsersDaoTest
{
	@Autowired
	private ExampleUsersDao usersDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    @Test
    public void testUsersDao() throws SQLException
    {
    	// INSERT
    	
    	ExampleUser user1 = new ExampleUser();
    	user1.setUserName("user2");
    	user1.setFirstName("user");
    	user1.setLastName("two");
    	user1.setEmail("usertwo@wpi.edu");
    	user1.setEncryptedPassword("22222222"); // simplified for now
    	user1.setSalt("22222222"); // also simplified for now
    	user1.setUserStateId(1); // assumes 1 = activated
    	
    	List<String> insertColumnNameList = new ArrayList<>();
    	List<String> keyHolderColumnNameList = new ArrayList<>();
    	
    	insertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.USER_NAME));
    	insertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.FIRST_NAME));
    	insertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.LAST_NAME));
    	insertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.EMAIL));
    	insertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.ENCRYPTED_PASSWORD));
    	insertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.SALT));
    	insertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.USER_STATE_ID));
    	
    	keyHolderColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.ID));
    	keyHolderColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.CREATED_AT));
    	keyHolderColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.UPDATED_AT));
   	
    	usersDao.insert(user1, insertColumnNameList, keyHolderColumnNameList);
    	
    	
    	// UPDATE
    	
    	String updateColumnName = ExampleUser.getColumnName(ExampleUser.Columns.USER_NAME);
    	String oldUserName = "user2";
    	String newUserName = "newUserName2";
    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
    	QueryTerm updateUseNameTerm = new QueryTerm();
    	updateUseNameTerm.setColumnName(updateColumnName);
    	updateUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateUseNameTerm.setValue(oldUserName);
    	updateQueryTermList.add(updateUseNameTerm);
    	
    	usersDao.update(updateColumnName, newUserName, updateQueryTermList);
    	
    	
    	// SELECT
    	// by user name
    	
    	String selectColumnName = ExampleUser.getColumnName(ExampleUser.Columns.USER_NAME);
    	String selectUserName = newUserName;
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectUseNameTerm = new QueryTerm();
    	selectUseNameTerm.setColumnName(selectColumnName);
    	selectUseNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectUseNameTerm.setValue(selectUserName);
    	selectQueryTermList.add(selectUseNameTerm);
    	
    	List<String> selectColumnNameList = ExampleUser.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
		@SuppressWarnings("unused")
		List<ExampleUser> selectedUserList = usersDao.select(selectColumnNameList, selectQueryTermList, orderByList);
    	
    	System.out.println();
    }
}
