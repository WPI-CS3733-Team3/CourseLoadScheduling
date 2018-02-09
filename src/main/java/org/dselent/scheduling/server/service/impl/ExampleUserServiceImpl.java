package org.dselent.scheduling.server.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dselent.scheduling.server.dao.ExampleUsersDao;
import org.dselent.scheduling.server.dao.ExampleUsersRolesLinksDao;
import org.dselent.scheduling.server.dto.RegisterUserDto;
import org.dselent.scheduling.server.model.ExampleUser;
import org.dselent.scheduling.server.model.ExampleUsersRolesLink;
import org.dselent.scheduling.server.service.ExampleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ExampleUserServiceImpl implements ExampleUserService
{
	@Autowired
	private ExampleUsersDao usersDao;

	@Autowired
	private ExampleUsersRolesLinksDao usersRolesLinksDao;

	public ExampleUserServiceImpl()
	{
		//
	}

	/*
	 * (non-Javadoc)
	 * @see org.dselent.scheduling.server.service.UserService#registerUser(org.dselent.scheduling.server.dto.RegisterUserDto)
	 */
	@Transactional
	@Override
	public List<Integer> registerUser(RegisterUserDto dto) throws SQLException
	{
		List<Integer> rowsAffectedList = new ArrayList<>();

		// TODO validate business constraints
		// ^^students should do this^^
		// password strength requirements
		// email requirements
		// null values
		// etc...

		String salt = KeyGenerators.string().generateKey();
		String saltedPassword = dto.getPassword() + salt;
		PasswordEncoder passwordEncorder = new BCryptPasswordEncoder();
		String encryptedPassword = passwordEncorder.encode(saltedPassword);

		ExampleUser user = new ExampleUser();
		user.setUserName(dto.getUserName());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setEncryptedPassword(encryptedPassword);
		user.setSalt(salt);
		user.setUserStateId(1);

		List<String> userInsertColumnNameList = new ArrayList<>();
		List<String> userKeyHolderColumnNameList = new ArrayList<>();

		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.USER_NAME));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.FIRST_NAME));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.LAST_NAME));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.EMAIL));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.ENCRYPTED_PASSWORD));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.SALT));
		userInsertColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.USER_STATE_ID));

		userKeyHolderColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.ID));
		userKeyHolderColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.CREATED_AT));
		userKeyHolderColumnNameList.add(ExampleUser.getColumnName(ExampleUser.Columns.UPDATED_AT));

		rowsAffectedList.add(usersDao.insert(user, userInsertColumnNameList, userKeyHolderColumnNameList));

		//

		// for now, assume users can only register with default role id
		// may change in the future

		ExampleUsersRolesLink usersRolesLink = new ExampleUsersRolesLink();
		usersRolesLink.setUserId(user.getId());
		usersRolesLink.setRoleId(1); // hard coded as regular user

		List<String> usersRolesLinksInsertColumnNameList = new ArrayList<>();
		List<String> usersRolesLinksKeyHolderColumnNameList = new ArrayList<>();

		usersRolesLinksInsertColumnNameList.add(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.USER_ID));
		usersRolesLinksInsertColumnNameList.add(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.ROLE_ID));

		usersRolesLinksKeyHolderColumnNameList.add(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.ID));
		usersRolesLinksKeyHolderColumnNameList.add(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.CREATED_AT));
		usersRolesLinksKeyHolderColumnNameList.add(ExampleUsersRolesLink.getColumnName(ExampleUsersRolesLink.Columns.DELETED));

		rowsAffectedList.add(usersRolesLinksDao.insert(usersRolesLink, usersRolesLinksInsertColumnNameList, usersRolesLinksKeyHolderColumnNameList));

		return rowsAffectedList;
	}

	//

	@Override
	public ExampleUser loginUser(String userName, String password)
	{
		// TODO Auto-generated method stub
		return null;
	}   

}
