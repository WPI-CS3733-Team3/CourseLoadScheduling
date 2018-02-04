package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.FacultyDao;
import org.dselent.scheduling.server.dao.Group3UsersDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.model.Group3User;
import org.dselent.scheduling.server.service.Group3UserService;
import org.dselent.scheduling.server.sqlutils.ColumnOrder;
import org.dselent.scheduling.server.sqlutils.ComparisonOperator;
import org.dselent.scheduling.server.sqlutils.QueryTerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class Group3UserServiceImpl implements Group3UserService
{
	@Autowired
	private Group3UsersDao group3UsersDao;
	
	@Autowired
	private FacultyDao facultyDao;
	
    public Group3UserServiceImpl()
    {
    	//
    }
    
    /*
     * (non-Javadoc)
     * @see org.dselent.scheduling.server.service.UserService#registerUser(org.dselent.scheduling.server.dto.RegisterUserDto)
     */
    @Transactional
    @Override
	public List<Integer> createGroup3User(String email, String password) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();
		
		// TODO validate business constraints
			// ^^students should do this^^
			// password strength requirements
			// email requirements
			// null values
			// etc...
		List<String> selectColumnNameList = Faculty.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm qt = new QueryTerm();
		qt.setColumnName(Faculty.getColumnName(Faculty.Columns.EMAIL));
		qt.setComparisonOperator(ComparisonOperator.EQUAL);
		qt.setValue(email);
		qt.setLogicalOperator(null);
		queryTermList.add(qt);
		
		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p = new Pair<String, ColumnOrder>(Faculty.getColumnName(Faculty.Columns.ID), ColumnOrder.ASC);
		orderByList.add(p);
		
		List<Faculty> facultyList = facultyDao.select(selectColumnNameList, queryTermList, orderByList);
		
		String salt = KeyGenerators.string().generateKey();
		String saltedPassword = password + salt;
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncorder.encode(saltedPassword);
		
		Group3User group3User = new Group3User();
		group3User.setAccountTypeId(1);
		group3User.setFacultyId(facultyList.get(0).getId());
		group3User.setEncryptedPassword(encryptedPassword);
		group3User.setPasswordSalt(salt);
    	
    	List<String> group3UserInsertColumnNameList = new ArrayList<>();
    	List<String> group3UserKeyHolderColumnNameList = new ArrayList<>();
		
    	group3UserInsertColumnNameList.add(Group3User.getColumnName(Group3User.Columns.ACCOUNT_TYPE_ID));
    	group3UserInsertColumnNameList.add(Group3User.getColumnName(Group3User.Columns.FACULTY_ID));
    	group3UserInsertColumnNameList.add(Group3User.getColumnName(Group3User.Columns.ENCRYPTED_PASSWORD));
    	group3UserInsertColumnNameList.add(Group3User.getColumnName(Group3User.Columns.PASSWORD_SALT));
    	
    	group3UserKeyHolderColumnNameList.add(Group3User.getColumnName(Group3User.Columns.ID));
    	group3UserKeyHolderColumnNameList.add(Group3User.getColumnName(Group3User.Columns.CREATED_AT));
    	group3UserKeyHolderColumnNameList.add(Group3User.getColumnName(Group3User.Columns.UPDATED_AT));
    	group3UserKeyHolderColumnNameList.add(Group3User.getColumnName(Group3User.Columns.DELETED));
		
    	rowsAffectedList.add(group3UsersDao.insert(group3User, group3UserInsertColumnNameList, group3UserKeyHolderColumnNameList));
    	
    	return rowsAffectedList;
	}
	
	//

	@Override
	public Group3User login(String email, String password) throws SQLException
	{
		List<String> facultySelectColumnNameList = Faculty.getColumnNameList();

		List<QueryTerm> facultyQueryTermList = new ArrayList<>();
		QueryTerm qt1 = new QueryTerm();
		qt1.setColumnName(Faculty.getColumnName(Faculty.Columns.EMAIL));
		qt1.setComparisonOperator(ComparisonOperator.EQUAL);
		qt1.setValue(email);
		qt1.setLogicalOperator(null);
		facultyQueryTermList.add(qt1);
		
		List<Pair<String, ColumnOrder>> facultyOrderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p1 = new Pair<String, ColumnOrder>(Faculty.getColumnName(Faculty.Columns.ID), ColumnOrder.ASC);
		facultyOrderByList.add(p1);
		
		Faculty facultyMember = facultyDao.select(facultySelectColumnNameList, facultyQueryTermList, facultyOrderByList).get(0);
		
		List<String> userSelectColumnNameList = Group3User.getColumnNameList();
		
		List<QueryTerm> userQueryTermList = new ArrayList<>();
		QueryTerm qt2 = new QueryTerm();
		qt2.setColumnName(Group3User.getColumnName(Group3User.Columns.FACULTY_ID));
		qt2.setComparisonOperator(ComparisonOperator.EQUAL);
		qt2.setValue(facultyMember.getId());
		qt2.setLogicalOperator(null);
		userQueryTermList.add(qt2);
		
		List<Pair<String, ColumnOrder>> userOrderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p2 = new Pair<String, ColumnOrder>(Group3User.getColumnName(Group3User.Columns.ID), ColumnOrder.ASC);
		facultyOrderByList.add(p2);
		
		Group3User user = group3UsersDao.select(userSelectColumnNameList, userQueryTermList, userOrderByList).get(0);
		
		String saltedPassword = password + user.getPasswordSalt();
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncorder.encode(saltedPassword);
		String expectedPassword = user.getEncryptedPassword();
		
		return encryptedPassword == expectedPassword ? user : null;
	}   

}
