package scm.ojt.project.bl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.bl.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * <h2>MyUserDetailsService Class</h2>
 * <p>
 * Process for Displaying MyUserDetailsService
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
	/**
	 * <h2>userService</h2>
	 * <p>
	 * userService
	 * </p>
	 */
	@Autowired
	private UserService userService;

	/**
	 * <h2>loadUserByUsername</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDTO userDto = userService.findByEmail(email);
		if (userDto == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new User(userDto.getEmail(), userDto.getPassword(), authorities(userDto.getType()));
	}

	/**
	 * <h2>authorities</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param type
	 * @return
	 * @return List<GrantedAuthority>
	 */
	private List<GrantedAuthority> authorities(String type) {
		List<GrantedAuthority> setAuths = new ArrayList<GrantedAuthority>();
		if (Integer.parseInt(type) == 0) {
			setAuths.add(new SimpleGrantedAuthority("ROLE_PHARMACIST"));
		} else if (Integer.parseInt(type) == 1) {
			setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return setAuths;
	}
}