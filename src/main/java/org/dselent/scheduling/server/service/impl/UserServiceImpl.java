package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.AccountTypeDao;
import org.dselent.scheduling.server.dao.FacultyDao;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
import org.dselent.scheduling.server.model.AccountType;
import org.dselent.scheduling.server.model.Faculty;
import org.dselent.scheduling.server.model.User;
import org.dselent.scheduling.server.service.UserService;
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
public class UserServiceImpl implements UserService
{
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private FacultyDao facultyDao;
	
	@Autowired
	private AccountTypeDao accountTypeDao;

	public UserServiceImpl()
	{
		
	}

	@Transactional
	@Override
	public User createUser(String email, String password, String accountType) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();

		// TODO validate business constraints
		// ^^students should do this^^
		// password strength requirements
		// email requirements
		// null values
		// etc...
		
		//Get faculty with the same email
		List<String> facultySelectColumnNameList = Faculty.getColumnNameList();

		List<QueryTerm> facultyQueryTermList = new ArrayList<>();
		QueryTerm fqt = new QueryTerm();
		fqt.setColumnName(Faculty.getColumnName(Faculty.Columns.EMAIL));
		fqt.setComparisonOperator(ComparisonOperator.EQUAL);
		fqt.setValue(email);
		fqt.setLogicalOperator(null);
		facultyQueryTermList.add(fqt);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p = new Pair<String, ColumnOrder>(Faculty.getColumnName(Faculty.Columns.ID), ColumnOrder.ASC);
		orderByList.add(p);

		System.out.println(facultySelectColumnNameList);
		System.out.println(facultyQueryTermList);
		System.out.println(orderByList);
		List<Faculty> facultyList = facultyDao.select(facultySelectColumnNameList, facultyQueryTermList, orderByList);
		System.out.println(facultyList);

		//Get account type row with the same account type
		List<String> accountTypeSelectColumnNameList = AccountType.getColumnNameList();

		List<QueryTerm> accountTypeQueryTermList = new ArrayList<>();
		QueryTerm acqt = new QueryTerm();
		acqt.setColumnName(AccountType.getColumnName(AccountType.Columns.ACCOUNT_TYPE));
		acqt.setComparisonOperator(ComparisonOperator.EQUAL);
		acqt.setValue(accountType);
		acqt.setLogicalOperator(null);
		accountTypeQueryTermList.add(acqt);
		
		List<Pair<String, ColumnOrder>> accountTypeOrderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p2 = new Pair<String, ColumnOrder>(AccountType.getColumnName(AccountType.Columns.ID), ColumnOrder.ASC);
		accountTypeOrderByList.add(p2);
		
		List<AccountType> accountTypeList = accountTypeDao.select(accountTypeSelectColumnNameList, accountTypeQueryTermList, accountTypeOrderByList);
		
		String salt = KeyGenerators.string().generateKey();
		String saltedPassword = password + salt;
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncorder.encode(saltedPassword);

		User user = new User();
		user.setAccountTypeId(accountTypeList.get(0).getId());
		user.setFacultyId(facultyList.get(0).getId());
		user.setEncryptedPassword(encryptedPassword);
		user.setPasswordSalt(salt);

		List<String> userInsertColumnNameList = new ArrayList<>();
		List<String> userKeyHolderColumnNameList = new ArrayList<>();

		userInsertColumnNameList.add(User.getColumnName(User.Columns.ACCOUNT_TYPE_ID));
		userInsertColumnNameList.add(User.getColumnName(User.Columns.FACULTY_ID));
		userInsertColumnNameList.add(User.getColumnName(User.Columns.ENCRYPTED_PASSWORD));
		userInsertColumnNameList.add(User.getColumnName(User.Columns.PASSWORD_SALT));

		userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.ID));
		userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.CREATED_AT));
		userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.UPDATED_AT));
		userKeyHolderColumnNameList.add(User.getColumnName(User.Columns.DELETED));

		rowsAffectedList.add(usersDao.insert(user, userInsertColumnNameList, userKeyHolderColumnNameList));

		return user;
	}


	@Transactional
	@Override
	public List<Integer> editUser(int id, String fname, String lname, String password) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();

		// TODO validate business constraints
		// ^^students should do this^^
		// password strength requirements
		// email requirements
		// null values
		// etc...

		User currentUser = usersDao.findById(id);
		List<String> selectColumnNameList = Faculty.getColumnNameList();

		//query term for users table
		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm qt = new QueryTerm();
		qt.setColumnName(User.getColumnName(User.Columns.ID));
		qt.setComparisonOperator(ComparisonOperator.EQUAL);
		qt.setValue(id);
		qt.setLogicalOperator(null);
		queryTermList.add(qt);
		
		//query term for faculty table
		List<QueryTerm> queryTermList2 = new ArrayList<>();
		QueryTerm qt2 = new QueryTerm();
		qt2.setColumnName(Faculty.getColumnName(Faculty.Columns.ID));
		qt2.setComparisonOperator(ComparisonOperator.EQUAL);
		qt2.setValue(currentUser.getFacultyId());
		qt2.setLogicalOperator(null);//LogicalOperator.AND);
		queryTermList2.add(qt2);

		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p = new Pair<String, ColumnOrder>(Faculty.getColumnName(Faculty.Columns.ID), ColumnOrder.ASC);
		orderByList.add(p);

		System.out.println(selectColumnNameList);
		System.out.println(queryTermList);
		System.out.println(orderByList);
		List<Faculty> facultyList = facultyDao.select(selectColumnNameList, queryTermList, orderByList);
		System.out.println(facultyList);

		String salt = KeyGenerators.string().generateKey();
		String saltedPassword = password + salt;
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncorder.encode(saltedPassword);
/*
		User user = new User();
		user.setAccountTypeId(1);
		user.setEncryptedPassword(encryptedPassword);
		user.setPasswordSalt(salt);*/


		rowsAffectedList.add(usersDao.update(User.getColumnName(User.Columns.ENCRYPTED_PASSWORD), encryptedPassword, queryTermList));
		rowsAffectedList.add(usersDao.update(User.getColumnName(User.Columns.PASSWORD_SALT), salt, queryTermList));
		rowsAffectedList.add(facultyDao.update(Faculty.getColumnName(Faculty.Columns.FIRST_NAME), fname, queryTermList2));
		rowsAffectedList.add(facultyDao.update(Faculty.getColumnName(Faculty.Columns.LAST_NAME), lname, queryTermList2));



		return rowsAffectedList;
	}


	@Override
	public User login(String email, String password) throws SQLException
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
		List<Faculty> facultyList = facultyDao.select(facultySelectColumnNameList, facultyQueryTermList, facultyOrderByList);
		Faculty facultyMember;
		if (facultyList.size()>0) {
			facultyMember = facultyList.get(0);
		} else {
			return null;
		}

		List<String> userSelectColumnNameList = User.getColumnNameList();

		List<QueryTerm> userQueryTermList = new ArrayList<>();
		QueryTerm qt2 = new QueryTerm();
		qt2.setColumnName(User.getColumnName(User.Columns.FACULTY_ID));
		qt2.setComparisonOperator(ComparisonOperator.EQUAL);
		qt2.setValue(facultyMember.getId());
		qt2.setLogicalOperator(null);
		userQueryTermList.add(qt2);

		List<Pair<String, ColumnOrder>> userOrderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p2 = new Pair<String, ColumnOrder>(User.getColumnName(User.Columns.ID), ColumnOrder.ASC);
		facultyOrderByList.add(p2);

		User user = usersDao.select(userSelectColumnNameList, userQueryTermList, userOrderByList).get(0);

		String saltedPassword = password + user.getPasswordSalt();
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String encryptedPassword = passwordEncoder.encode(saltedPassword);
		String expectedPassword = user.getEncryptedPassword();
		
		boolean success = passwordEncoder.matches(saltedPassword, expectedPassword);
		
		
		//System.out.println("Expected: "+ expectedPassword + "\nEncrypted: "+ encryptedPassword + "\n what was given: " + password +
		//"\n and salt:" + user.getPasswordSalt() + "\n Success: " + success);

		return success ? user : null;
	} 

	@Override
	public List<Integer> promote(String email) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();

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

		List<QueryTerm> userQueryTermList = new ArrayList<>();
		QueryTerm qt2 = new QueryTerm();
		qt2.setColumnName(User.getColumnName(User.Columns.FACULTY_ID));
		qt2.setComparisonOperator(ComparisonOperator.EQUAL);
		qt2.setValue(facultyMember.getId());
		qt2.setLogicalOperator(null);
		userQueryTermList.add(qt2);

		rowsAffectedList.add(usersDao.update(User.getColumnName(User.Columns.ACCOUNT_TYPE_ID), 2, userQueryTermList));

		return rowsAffectedList;
	}

	@Transactional
	@Override
	public List<Integer> deleteUser(int id, int idToDelete) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();

		// TODO validate business constraints
		// ^^students should do this^^
		// password strength requirements
		// email requirements
		// null values
		// etc...

		User deleter = usersDao.findById(id);
		//In case user tries to enter 
		if(deleter.getAccountTypeId() != 2 && id !=  idToDelete)
		{
			System.out.println("Regular Users can only delete their own account.");
			return null;
		}

		List<String> selectColumnNameList = Faculty.getColumnNameList();

		List<QueryTerm> queryTermList = new ArrayList<>();
		QueryTerm qt = new QueryTerm();
		qt.setColumnName(User.getColumnName(User.Columns.ID));
		qt.setComparisonOperator(ComparisonOperator.EQUAL);
		qt.setValue(idToDelete);
		qt.setLogicalOperator(null);
		queryTermList.add(qt);


		List<Pair<String, ColumnOrder>> orderByList = new ArrayList<>();
		Pair<String, ColumnOrder> p = new Pair<String, ColumnOrder>(Faculty.getColumnName(Faculty.Columns.ID), ColumnOrder.ASC);
		orderByList.add(p);

		System.out.println(selectColumnNameList);
		System.out.println(queryTermList);
		System.out.println(orderByList);

		rowsAffectedList.add(usersDao.delete(queryTermList));

		return rowsAffectedList;
	} 
    
    @Override
	public List<Faculty> viewRoster() throws SQLException
	{
    	List<String> facultySelectColumnNameList = Faculty.getColumnNameList();
    	List<QueryTerm> qtlist = new ArrayList<QueryTerm>();
    	List<Pair<String,ColumnOrder>> plist = new ArrayList<Pair<String,ColumnOrder>>();
    	List<Faculty> facultyList = facultyDao.select(facultySelectColumnNameList, qtlist, plist);
    	
    	return facultyList;
	}
}
