package scm.ojt.project.bl.service;

import javax.servlet.http.HttpServletRequest;

import scm.ojt.project.bl.dto.UserDTO;

/**
 * <h2>AuthService Class</h2>
 * <p>
 * Process for Displaying AuthService
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface AuthService {
	/**
	 * <h2>getLoggedInUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return UserDTO
	 */
	UserDTO getLoggedInUser();

	/**
	 * <h2>isLoggedIn</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return boolean
	 */
	boolean isLoggedIn();

	/**
	 * <h2>loadAuth</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return void
	 */
	void loadAuth(String email);

	/**
	 * <h2>logout</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @return void
	 */
	void logout(HttpServletRequest request);
}