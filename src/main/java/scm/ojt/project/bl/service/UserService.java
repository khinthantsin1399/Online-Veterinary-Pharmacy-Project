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
	 * <h2>isUserExist</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return boolean
	 */
	public boolean isUserExist(String email);

	/**
	 * <h2>saveUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @return void
	 */
	public void saveUser(UserForm userForm);

	/**
	 * <h2>getUserList</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return List<UserDTO>
	 */
	public List<UserDTO> getUserList();

	/**
	 * <h2>softDelete</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return void
	 */
	public void softDelete(Integer userId);

	/**
	 * <h2>getUserbyId</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return UserForm
	 */
	public UserForm getUserbyId(Integer userId);

	/**
	 * <h2>updateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @return void
	 */
	public void updateUser(UserForm userForm);

	/**
	 * <h2>findByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user_email
	 * @return
	 * @return UserDTO
	 */
	public UserDTO findByEmail(String user_email);
}