package org.dselent.scheduling.server.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.config.AppConfig;
import org.dselent.scheduling.server.dao.FacultyDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Faculty;
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
public class FacultyDaoTest {
	@Autowired
	private FacultyDao facultyDao;
	
	/*
	 * Not really an using this as a JUnit test
	 * More of an example on how to use the classes
	 */
    @Test
    public void testFacultyDao() throws SQLException {
    	// INSERT
    	
    	Faculty faculty1 = new Faculty();
    	faculty1.setFirstName("user");
    	faculty1.setLastName("two");
    	faculty1.setEmail("usertwo@wpi.edu");
    	faculty1.setFacultyTypeId(1);
    	
    	List<String> insertColumnNameList = new ArrayList<>();
    	List<String> keyHolderColumnNameList = new ArrayList<>();
    	
    	insertColumnNameList.add(Faculty.getColumnName(Faculty.Columns.FIRST_NAME));
    	insertColumnNameList.add(Faculty.getColumnName(Faculty.Columns.LAST_NAME));
    	insertColumnNameList.add(Faculty.getColumnName(Faculty.Columns.EMAIL));
    	insertColumnNameList.add(Faculty.getColumnName(Faculty.Columns.FACULTY_TYPE_ID));
    	
    	keyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.ID));
    	keyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.CREATED_AT));
    	keyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.UPDATED_AT));
    	keyHolderColumnNameList.add(Faculty.getColumnName(Faculty.Columns.DELETED));
   	
    	facultyDao.insert(faculty1, insertColumnNameList, keyHolderColumnNameList);
    	
    	
    	// UPDATE
    	
    	String updateColumnName = Faculty.getColumnName(Faculty.Columns.FIRST_NAME);
    	String oldFirstName = "firstName";
    	String newFirstName = "firstName3";
    	List<QueryTerm> updateQueryTermList = new ArrayList<>();
    	
    	QueryTerm updateFirstNameTerm = new QueryTerm();
    	updateFirstNameTerm.setColumnName(updateColumnName);
    	updateFirstNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	updateFirstNameTerm.setValue(oldFirstName);
    	updateQueryTermList.add(updateFirstNameTerm);
    	
    	facultyDao.update(updateColumnName, newFirstName, updateQueryTermList);
    	
    	
    	// SELECT
    	// by user name
    	
    	String selectColumnName = Faculty.getColumnName(Faculty.Columns.FIRST_NAME);
    	String selectFirstName = newFirstName;
    	
    	List<QueryTerm> selectQueryTermList = new ArrayList<>();
    	
    	QueryTerm selectFirstNameTerm = new QueryTerm();
    	selectFirstNameTerm.setColumnName(selectColumnName);
    	selectFirstNameTerm.setComparisonOperator(ComparisonOperator.EQUAL);
    	selectFirstNameTerm.setValue(selectFirstName);
    	selectQueryTermList.add(selectFirstNameTerm);
    	
    	List<String> selectColumnNameList = Faculty.getColumnNameList();
    	
    	List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
    	Pair<String, ColumnOrder> orderPair1 = new Pair<String, ColumnOrder>(selectColumnName, ColumnOrder.ASC);
    	orderByList.add(orderPair1);
    	
		@SuppressWarnings("unused")
		List<Faculty> selectedUserList = facultyDao.select(selectColumnNameList, selectQueryTermList, orderByList);
    	
    	System.out.println();
    }
}
