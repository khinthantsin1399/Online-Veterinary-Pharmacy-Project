package scm.ojt.project.bl.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.bl.dto.UserDTO;
import scm.ojt.project.bl.service.UserService;
import scm.ojt.project.persistence.dao.UserDao;
import scm.ojt.project.persistence.entity.User;
import scm.ojt.project.web.form.UserForm;

/**
 * <h2>UserServiceImpl Class</h2>
 * <p>
 * Process for Displaying UserServiceImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	/**
	 * <h2>passwordEncoder</h2>
	 * <p>
	 * passwordEncoder
	 * </p>
	 */
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	/**
	 * <h2>userDao</h2>
	 * <p>
	 * userDao
	 * </p>
	 */
	@Autowired
	private UserDao userDao;

	/**
	 * <h2>doIsUserExist</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public boolean doIsUserExist(String email) {
		boolean userExist = false;
		User user = this.userDao.dbGetUserByEmail(email);
		if (user != null) {
			userExist = true;
		}
		return userExist;
	}

	/**
	 * <h2>doSaveUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userForm
	 */
	@Override
	public void doSaveUser(UserForm userForm) {
		userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
		int createdUserId = 1;
		Date date = new Date();
		User user = new User(userForm);
		userDao.dbSaveUser(user, createdUserId, date);
	}

	/**
	 * <h2>doGetUserList</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@Override
	public List<UserDTO> doGetUserList() {
		return this.userDao.dbGetUserList();
	}

	/**
	 * <h2>doSoftDelete</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 */
	@Override
	public void doSoftDelete(Integer userId) {
		int currentUserId = 1;
		Date currentDate = new Date();
		this.userDao.dbSoftDeleteUser(userId, currentUserId, currentDate);
	}

	/**
	 * <h2>doGetUserbyId</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public UserForm doGetUserbyId(Integer userId) {
		User result = this.userDao.dbGetUserbyId(userId);
		UserForm resultUserForm = result != null ? new UserForm(result) : null;
		return resultUserForm;
	}

	/**
	 * <h2>doUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userForm
	 */
	@Override
	public void doUpdateUser(UserForm userForm) {
		User updateUser = this.userDao.dbGetUserbyId(userForm.getId());
		if (updateUser != null) {
			updateUser.setUsername(userForm.getUsername());
			updateUser.setEmail(userForm.getEmail());
			updateUser.setPassword(userForm.getPassword());
			updateUser.setPhone(userForm.getPhone());
			updateUser.setType(userForm.getType());
			updateUser.setAddress(userForm.getAddress());
			updateUser.setUpdated_user_id(1);
			updateUser.setUpdated_at(new Date());
			this.userDao.dbUpdateUser(updateUser);
		}
	}

	/**
	 * <h2>doFindByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public UserDTO doFindByEmail(String email) {
		UserDTO userDto = new UserDTO(userDao.dbFindByEmail(email));
		return userDto;
	}
}