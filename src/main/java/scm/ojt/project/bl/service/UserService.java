package scm.ojt.project.bl.service;

import java.util.List;

import scm.ojt.project.bl.dto.UserDTO;
import scm.ojt.project.web.form.UserForm;

public interface UserService {

	public boolean isUserExist(String email);

	public void saveUser(UserForm userForm);

	public List<UserDTO> getUserList();

	public void softDelete(Integer userId);

	public UserForm getUserbyId(Integer userId);

	public void updateUser(UserForm userForm);
}
