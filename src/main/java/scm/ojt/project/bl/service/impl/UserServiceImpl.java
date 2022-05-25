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
	 * <h2>isUserExist</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public boolean isUserExist(String email) {
		boolean userExist = false;
		User user = this.userDao.getUserByEmail(email);
		if (user != null) {
			userExist = true;
		}
		return userExist;
	}

	/**
	 * <h2>saveUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userForm
	 */
	@Override
	public void saveUser(UserForm userForm) {
		userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
		int createdUserId = 1;
		Date date = new Date();
		User user = new User(userForm);
		userDao.saveUser(user, createdUserId, date);
	}

	/**
	 * <h2>getUserList</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@Override
	public List<UserDTO> getUserList() {
		return this.userDao.getUserList();
	}

	/**
	 * <h2>softDelete</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 */
	@Override
	public void softDelete(Integer userId) {
		int currentUserId = 1;
		Date currentDate = new Date();
		this.userDao.softDeleteUser(userId, currentUserId, currentDate);
	}

	/**
	 * <h2>getUserbyId</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public UserForm getUserbyId(Integer userId) {
		User result = this.userDao.getUserbyId(userId);
		UserForm resultUserForm = result != null ? new UserForm(result) : null;
		return resultUserForm;
	}

	/**
	 * <h2>updateUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userForm
	 */
	@Override
	public void updateUser(UserForm userForm) {
		User updateUser = this.userDao.getUserbyId(userForm.getId());
		if (updateUser != null) {
			updateUser.setUsername(userForm.getUsername());
			updateUser.setEmail(userForm.getEmail());
			updateUser.setPassword(userForm.getPassword());
			updateUser.setPhone(userForm.getPhone());
			updateUser.setType(userForm.getType());
			updateUser.setAddress(userForm.getAddress());
			updateUser.setUpdated_user_id(1);
			updateUser.setUpdated_at(new Date());
			this.userDao.updateUser(updateUser);
		}
	}

	/**
	 * <h2>findByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public UserDTO findByEmail(String email) {
		UserDTO userDto = new UserDTO(userDao.findByEmail(email));
		return userDto;
	}
}