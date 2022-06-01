package scm.ojt.project.persistence.dao.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import scm.ojt.project.bl.dto.UserDTO;
import scm.ojt.project.persistence.dao.UserDao;
import scm.ojt.project.persistence.entity.User;

/**
 * <h2>UserDaoImpl Class</h2>
 * <p>
 * Process for Displaying UserDaoImpl
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	/**
	 * <h2>sessionFactory</h2>
	 * <p>
	 * sessionFactory
	 * </p>
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * <h2>dbGetUserByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public User dbGetUserByEmail(String email) {
		String userHqlQuery = "SELECT u FROM User u WHERE u.email = :email";
		Query queryUserByEmail = this.sessionFactory.getCurrentSession().createQuery(userHqlQuery);
		queryUserByEmail.setParameter("email", email);
		User user = (User) queryUserByEmail.uniqueResult();
		return user;
	}

	/**
	 * <h2>dbSaveUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param user
	 * @param createdUserId
	 * @param date
	 */
	@Override
	public void dbSaveUser(User user, int createdUserId, Date date) {
		user.setCreated_user_id(createdUserId);
		user.setCreated_at(date);
		this.sessionFactory.getCurrentSession().save(user);
	}

	/**
	 * <h2>dbGetUserList</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> dbGetUserList() {
		return sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u WHERE u.deleted_user_id = null")
		        .list();
	}

	/**
	 * <h2>dbSoftDeleteUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 * @param currentUserId
	 * @param currentDate
	 */
	@Override
	public void dbSoftDeleteUser(Integer userId, int currentUserId, Date currentDate) {
		User user = (User) sessionFactory.getCurrentSession().load(User.class, userId);
		if (user != null) {
			user.setDeleted_user_id(userId);
			user.setDeleted_at(currentDate);
			this.sessionFactory.getCurrentSession().update(user);
		}
	}

	/**
	 * <h2>dbGetUserbyId</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public User dbGetUserbyId(Integer userId) {
		String userHqlQuery = "SELECT u FROM User u where u.id = :id";
		Query queryUserById = this.sessionFactory.getCurrentSession().createQuery(userHqlQuery);
		queryUserById.setParameter("id", userId);
		User resultUser = (User) queryUserById.uniqueResult();
		return resultUser;
	}

	/**
	 * <h2>dbUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param updateUser
	 */
	@Override
	public void dbUpdateUser(User updateUser) {
		this.sessionFactory.getCurrentSession().update(updateUser);
	}

	/**
	 * <h2>dbFindByEmail</h2>
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param email
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public User dbFindByEmail(String email) {
		String userHqlQuery = "SELECT u FROM User u where u.email = :email";
		Query queryUserById = this.sessionFactory.getCurrentSession().createQuery(userHqlQuery);
		queryUserById.setParameter("email", email);
		User resultUser = (User) queryUserById.uniqueResult();
		return resultUser;
	}
}