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
	 * <h2>doGetLoggedInUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return UserDTO
	 */
	UserDTO doGetLoggedInUser();

	/**
	 * <h2>doIsLoggedIn</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return boolean
	 */
	boolean doIsLoggedIn();

	/**
	 * <h2>doLoadAuth</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return void
	 */
	void doLoadAuth(String email);

	/**
	 * <h2>doLogout</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @return void
	 */
	void doLogout(HttpServletRequest request);
}