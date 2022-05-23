package scm.ojt.project.persistence.dao;

import java.util.Date;
import java.util.List;

import scm.ojt.project.bl.dto.UserDTO;
import scm.ojt.project.persistence.entity.User;

public interface UserDao {

	public User getUserByEmail(String email);

	public void saveUser(User user, int createdUserId, Date date);

	public List<UserDTO> getUserList();

	public void softDeleteUser(Integer userId, int currentUserId, Date currentDate);

	public User getUserbyId(Integer userId);

	public void updateUser(User updateUser);
}
