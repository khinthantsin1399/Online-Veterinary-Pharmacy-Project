package scm.ojt.project.bl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import scm.ojt.project.bl.dto.UserDTO;
import scm.ojt.project.bl.service.AuthService;
import scm.ojt.project.bl.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * <h2>AuthServiceImpl Class</h2>
 * <p>
 * Process for Displaying AuthServiceImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {
	/**
	 * <h2>userService</h2>
	 * <p>
	 * userService
	 * </p>
	 */
	@Autowired
	private UserService userService;
	/**
	 * <h2>userDetailsService</h2>
	 * <p>
	 * userDetailsService
	 * </p>
	 */
	@Autowired
	@Qualifier("myUserDetailsService")
	private UserDetailsService userDetailsService;

	/**
	 * <h2>getLoggedInUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	public UserDTO getLoggedInUser() {
		String user_email = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			user_email = ((UserDetails) principal).getUsername();
		} else {
			user_email = principal.toString();
		}

		return userService.findByEmail(user_email);
	}

	/**
	 * <h2>isLoggedIn</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	public boolean isLoggedIn() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken)
		        && authentication.isAuthenticated();
	}

	/**
	 * <h2>loadAuth</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 */
	public void loadAuth(String email) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
		        userDetails.getPassword(), userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	/**
	 * <h2>logout</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param request
	 */
	public void logout(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		request.getSession(true).invalidate();
	}
}