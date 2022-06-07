package scm.ojt.project.config;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import scm.ojt.project.persistence.dao.UserDao;
import scm.ojt.project.persistence.entity.User;
import scm.ojt.project.web.form.UserForm;

@Component
public class DeploymentListener {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDao userDao;

	@PostConstruct
	public void addInitialData() {
		if (this.userDao.dbGetUserCount() <= 0) {
			// Admin
			UserForm userAdminForm = new UserForm();
			userAdminForm.setUsername("ADMIN");
			userAdminForm.setEmail("admin@gmail.com");
			userAdminForm.setPassword(passwordEncoder.encode("111111"));
			userAdminForm.setType("1");
			userAdminForm.setPhone("09781111111");
			userAdminForm.setAddress("Yangon, Myanmar");
			userAdminForm.setCreated_at(new Date());
			User userAdmin = new User(userAdminForm);
			this.userDao.dbSaveUser(userAdmin, 1, new Date());

			// Pharmacist
			UserForm userPharmacistForm = new UserForm();
			userPharmacistForm.setUsername("PHARMACIST");
			userPharmacistForm.setEmail("pharmacist@gmail.com");
			userPharmacistForm.setPassword(passwordEncoder.encode("111111"));
			userPharmacistForm.setType("0");
			userPharmacistForm.setPhone("09781111111");
			userPharmacistForm.setAddress("Yangon, Myanmar");
			userPharmacistForm.setCreated_at(new Date());
			User userPharmacist = new User(userPharmacistForm);
			this.userDao.dbSaveUser(userPharmacist, 1, new Date());

			// User
			UserForm userForm = new UserForm();
			userForm.setUsername("USER");
			userForm.setEmail("user@gmail.com");
			userForm.setPassword(passwordEncoder.encode("111111"));
			userForm.setType("2");
			userForm.setPhone("09781111111");
			userForm.setAddress("Yangon, Myanmar");
			userForm.setCreated_at(new Date());
			User user = new User(userForm);
			this.userDao.dbSaveUser(user, 1, new Date());
		}
	}
}