package scm.ojt.project.bl.service;

import java.util.List;

import scm.ojt.project.bl.dto.UserDTO;
import scm.ojt.project.web.form.UserForm;

/**
 * <h2>UserService Class</h2>
 * <p>
 * Process for Displaying UserService
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface UserService {
	/**
	 * <h2>doIsUserExist</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return boolean
	 */
	public boolean doIsUserExist(String email);

	/**
	 * <h2>doSaveUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @return void
	 */
	public void doSaveUser(UserForm userForm);

	/**
	 * <h2>doGetUserList</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return List<UserDTO>
	 */
	public List<UserDTO> doGetUserList();

	/**
	 * <h2>doSoftDelete</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return void
	 */
	public void doSoftDelete(Integer userId);

	/**
	 * <h2>doGetUserbyId</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return UserForm
	 */
	public UserForm doGetUserbyId(Integer userId);

	/**
	 * <h2>doUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @return void
	 */
	public void doUpdateUser(UserForm userForm);

	/**
	 * <h2>doFindByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user_email
	 * @return
	 * @return UserDTO
	 */
	public UserDTO doFindByEmail(String user_email);
}