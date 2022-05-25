package scm.ojt.project.persistence.dao;

import java.util.Date;
import java.util.List;

import scm.ojt.project.bl.dto.UserDTO;
import scm.ojt.project.persistence.entity.User;

/**
 * <h2>UserDao Class</h2>
 * <p>
 * Process for Displaying UserDao
 * </p>
 * 
 * @author KyawHtet
 *
 */
public interface UserDao {
	/**
	 * <h2>getUserByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return User
	 */
	public User getUserByEmail(String email);

	/**
	 * <h2>saveUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user
	 * @param createdUserId
	 * @param date
	 * @return void
	 */
	public void saveUser(User user, int createdUserId, Date date);

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
	 * <h2>softDeleteUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @param currentUserId
	 * @param currentDate
	 * @return void
	 */
	public void softDeleteUser(Integer userId, int currentUserId, Date currentDate);

	/**
	 * <h2>getUserbyId</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return User
	 */
	public User getUserbyId(Integer userId);

	/**
	 * <h2>updateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param updateUser
	 * @return void
	 */
	public void updateUser(User updateUser);

	/**
	 * <h2>findByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return User
	 */
	public User findByEmail(String email);
}