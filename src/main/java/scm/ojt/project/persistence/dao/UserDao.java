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
	 * <h2>dbGetUserByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return User
	 */
	public User dbGetUserByEmail(String email);

	/**
	 * <h2>dbSaveUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param user
	 * @param createdUserId
	 * @param date
	 * @return void
	 */
	public void dbSaveUser(User user, int createdUserId, Date date);

	/**
	 * <h2>dbGetUserList</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return List<UserDTO>
	 */
	public List<UserDTO> dbGetUserList();

	/**
	 * <h2>dbSoftDeleteUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @param currentUserId
	 * @param currentDate
	 * @return void
	 */
	public void dbSoftDeleteUser(Integer userId, int currentUserId, Date currentDate);

	/**
	 * <h2>dbGetUserbyId</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return User
	 */
	public User dbGetUserbyId(Integer userId);

	/**
	 * <h2>dbUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param updateUser
	 * @return void
	 */
	public void dbUpdateUser(User updateUser);

	/**
	 * <h2>dbFindByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param email
	 * @return
	 * @return User
	 */
	public User dbFindByEmail(String email);

	/**
	 * <h2>dbGetUserCount</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return int
	 */
	public long dbGetUserCount();
}