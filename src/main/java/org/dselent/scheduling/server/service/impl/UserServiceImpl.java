package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.FacultyDao;
import org.dselent.scheduling.server.dao.UsersDao;
import org.dselent.scheduling.server.miscellaneous.Pair;
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
	private UsersDao group3UsersDao;
	
	@Autowired
	private FacultyDao facultyDao;
	
    public UserServiceImpl()
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
		
		System.out.println(selectColumnNameList);
		System.out.println(queryTermList);
		System.out.println(orderByList);
		List<Faculty> facultyList = facultyDao.select(selectColumnNameList, queryTermList, orderByList);
		System.out.println(facultyList);
		
		String salt = KeyGenerators.string().generateKey();
		String saltedPassword = password + salt;
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncorder.encode(saltedPassword);
		
		User group3User = new User();
		group3User.setAccountTypeId(1);
		group3User.setFacultyId(facultyList.get(0).getId());
		group3User.setEncryptedPassword(encryptedPassword);
		group3User.setPasswordSalt(salt);
    	
    	List<String> group3UserInsertColumnNameList = new ArrayList<>();
    	List<String> group3UserKeyHolderColumnNameList = new ArrayList<>();
		
    	group3UserInsertColumnNameList.add(User.getColumnName(User.Columns.ACCOUNT_TYPE_ID));
    	group3UserInsertColumnNameList.add(User.getColumnName(User.Columns.FACULTY_ID));
    	group3UserInsertColumnNameList.add(User.getColumnName(User.Columns.ENCRYPTED_PASSWORD));
    	group3UserInsertColumnNameList.add(User.getColumnName(User.Columns.PASSWORD_SALT));
    	
    	group3UserKeyHolderColumnNameList.add(User.getColumnName(User.Columns.ID));
    	group3UserKeyHolderColumnNameList.add(User.getColumnName(User.Columns.CREATED_AT));
    	group3UserKeyHolderColumnNameList.add(User.getColumnName(User.Columns.UPDATED_AT));
    	group3UserKeyHolderColumnNameList.add(User.getColumnName(User.Columns.DELETED));
		
    	rowsAffectedList.add(group3UsersDao.insert(group3User, group3UserInsertColumnNameList, group3UserKeyHolderColumnNameList));
    	
    	return rowsAffectedList;
	}
	
	//

	@Override
	public User loginUser(String userName, String password)
	{
		// TODO Auto-generated method stub
		return null;
	}   

}
