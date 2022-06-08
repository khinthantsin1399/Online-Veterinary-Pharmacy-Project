package scm.ojt.project.web.controller.user;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import scm.ojt.project.bl.dto.UserDTO;
import scm.ojt.project.bl.service.AuthService;
import scm.ojt.project.bl.service.UserService;
import scm.ojt.project.persistence.entity.User;
import scm.ojt.project.web.form.UserForm;

/**
 * <h2>UserController Class</h2>
 * <p>
 * Process for Displaying UserController
 * </p>
 * 
 * @author KyawHtet
 *
 */
@Controller
public class UserController {
	/**
	 * <h2>messageSource</h2>
	 * <p>
	 * messageSource
	 * </p>
	 */
	@Autowired
	private MessageSource messageSource;
	/**
	 * <h2>userService</h2>
	 * <p>
	 * userService
	 * </p>
	 */
	@Autowired
	private UserService userService;
	/**
	 * <h2>authService</h2>
	 * <p>
	 * authService
	 * </p>
	 */
	@Autowired
	private AuthService authService;

	/**
	 * <h2>viewLogin</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param model
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public ModelAndView viewLogin(ModelAndView model) {
		if (authService.doIsLoggedIn()) {
			if (Integer.parseInt(authService.doGetLoggedInUser().getType()) == 2) {
				return new ModelAndView("redirect:/userMedicineList");
			}
			return new ModelAndView("redirect:/medicineList");
		}
		ModelAndView mv = new ModelAndView("userLogin");
		return mv;
	}

	/**
	 * <h2>login</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param error
	 * @param model
	 * @param request
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error, ModelMap model,
	        HttpServletRequest request) {
		if (authService.doIsLoggedIn()) {
			if (authService.doGetLoggedInUser().getType() == "2") {
				return "medicineList";
			}
			return "redirect:/userList";
		}
		if (error != null) {
			model.addAttribute("errorMsg", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		return "userLogin";
	}

	/**
	 * <h2>viewUserList</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param model
	 * @param session
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public ModelAndView viewUserList(ModelAndView model, HttpSession session) {
		System.out.println("isLoggedIn" + authService.doGetLoggedInUser().getType());
		boolean isLoggedIn = authService.doIsLoggedIn();
		ModelAndView createUserListView = new ModelAndView("userList");
		List<UserDTO> userList = this.userService.doGetUserList();
		createUserListView.addObject("userList", userList);
		if (isLoggedIn) {
			session.setAttribute("LOGIN_USER", authService.doGetLoggedInUser());
			session.setAttribute("loginUserId", authService.doGetLoggedInUser().getId());
			System.out.print(isLoggedIn + authService.doGetLoggedInUser().getType());
			String type = authService.doGetLoggedInUser().getType();
			if (Integer.parseInt(type) == 2) {
				return new ModelAndView("redirect:/userMedicineList");
			}
		}
		return createUserListView;
	}

	/**
	 * <h2>registerUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param model
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public ModelAndView registerUser(ModelAndView model) {
		User user = new User();
		boolean isLoggedIn = authService.doIsLoggedIn();
		ModelAndView mv = new ModelAndView("createUser");
		mv.addObject("userForm", user);
		if (isLoggedIn) {
			mv.addObject("LOGIN_USER", authService.doGetLoggedInUser());
		}
		return mv;
	}

	/**
	 * <h2>createUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param model
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/create", method = RequestMethod.GET)
	public ModelAndView createUser(ModelAndView model) {
		User user = new User();
		ModelAndView mv = new ModelAndView("createUser");
		mv.addObject("userForm", user);
		return mv;
	}

	/**
	 * <h2>callCreateUserConfirm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @param result
	 * @return
	 * @throws ParseException
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/create/confirm", method = RequestMethod.POST)
	public ModelAndView callCreateUserConfirm(@ModelAttribute("userForm") @Valid UserForm userForm,
	        BindingResult result) throws ParseException {
		ModelAndView userConfirmView = new ModelAndView("createUser");
		if (result.hasErrors()) {
		} else if (userService.doIsUserExist(userForm.getEmail())) {
			userConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_USR_0001", null, null));
		} else {
			userConfirmView.addObject("userConfirmForm", userForm);
			userConfirmView.setViewName("createUserConfirm");
		}
		return userConfirmView;
	}

	/**
	 * <h2>cancelCreateUserForm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param model
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/create/confirm", params = "cancel", method = RequestMethod.POST)
	public ModelAndView cancelCreateUserForm(ModelAndView model) {
		return new ModelAndView("redirect:/user/list");
	}

	/**
	 * <h2>saveUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @return
	 * @throws ParseException
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/save", params = "addUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("userConfirmForm") @Valid UserForm userForm, HttpSession session,
	        RedirectAttributes redirectAttributes) throws ParseException {
		this.userService.doSaveUser(userForm);
		boolean isLoggedIn = authService.doIsLoggedIn();
		if (!isLoggedIn) {
			authService.doLoadAuth(userForm.getEmail());
		}
		session.setAttribute("LOGIN_USER", authService.doGetLoggedInUser());
		session.setAttribute("loginUserId", authService.doGetLoggedInUser().getId());

		ModelAndView createUserView = new ModelAndView("redirect:/user/list");
		redirectAttributes.addFlashAttribute("errorMsg", messageSource.getMessage("M_SC_USR_0002", null, null));
//        List<UserDTO> userList = this.userService.doGetUserList();
//        createUserView.addObject("errorMsg", messageSource.getMessage("M_SC_USR_0002", null, null));
//        createUserView.addObject("userList", userList);
		return createUserView;
	}

	/**
	 * <h2>calcelUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @param result
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/save", params = "cancel", method = RequestMethod.POST)
	public ModelAndView calcelUser(@ModelAttribute("userConfirmForm") @Valid UserForm userForm) {
		ModelAndView createUserView = new ModelAndView("createUser");
		createUserView.addObject("userForm", userForm);
		return createUserView;
	}

	/**
	 * <h2>update</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam("id") Integer userId) {
		ModelAndView updateView = new ModelAndView("updateUser");
		UserForm oldUserForm = this.userService.doGetUserbyId(userId);
		updateView.addObject("oldUserForm", oldUserForm);
		return updateView;
	}

	/**
	 * <h2>calcelUpdateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/update/confirm", params = "cancel", method = RequestMethod.POST)
	public ModelAndView calcelUpdateUser() {
		return new ModelAndView("redirect:/user/list");
	}

	/**
	 * <h2>updateUserConfrim</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param updateuserForm
	 * @param result
	 * @return
	 * @throws ParseException
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/update/confirm", params = "updateConfirm", method = RequestMethod.POST)
	public ModelAndView updateUserConfrim(@ModelAttribute("oldUserForm") @Valid UserForm updateuserForm,
	        BindingResult result) throws ParseException {
		ModelAndView updateUserConfirmView = new ModelAndView("updateUser");
		if (result.hasErrors()) {
			updateUserConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_USR_0003", null, null));
		} else {
			updateUserConfirmView = new ModelAndView("updateUserConfirm");
			updateUserConfirmView.addObject("updateUserConfirmForm", updateuserForm);
		}
		return updateUserConfirmView;
	}

	/**
	 * <h2>cancelUpdateConfirmForm</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param oldUserForm
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/update", params = "cancel", method = RequestMethod.POST)
	public ModelAndView cancelUpdateConfirmForm(@ModelAttribute("updateUserConfirmForm") UserForm oldUserForm) {
		ModelAndView backUpdateUserForm = new ModelAndView("updateUser");
		backUpdateUserForm.addObject("oldUserForm", oldUserForm);
		return backUpdateUserForm;
	}

	/**
	 * <h2>updateUser</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userForm
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/update", params = "updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("updateUserConfirmForm") UserForm userForm,
	        RedirectAttributes redirectAttributes) {
		this.userService.doUpdateUser(userForm);
		ModelAndView updateUserView = new ModelAndView("redirect:/user/list");
//		updateUserView.addObject("errorMsg", messageSource.getMessage("M_SC_USR_0004", null, null));
		redirectAttributes.addFlashAttribute("errorMsg", messageSource.getMessage("M_SC_USR_0004", null, null));
		return updateUserView;
	}

	/**
	 * <h2>profileDetail</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/profile/detail", method = RequestMethod.GET)
	public ModelAndView profileDetail(@RequestParam("id") Integer userId, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
		if (user.getId() == userId) {
			ModelAndView mv = new ModelAndView("profileDetail");
			UserForm profileDetailForm = this.userService.doGetUserbyId(userId);
			mv.addObject("profileDetailForm", profileDetailForm);
			return mv;
		}
		return new ModelAndView("redirect:/denied");
	}

	/**
	 * <h2>profileEdit</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @param session
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
	public ModelAndView profileEdit(@RequestParam("id") Integer userId, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
		if (user.getId() == userId) {
			ModelAndView mv = new ModelAndView("profileEdit");
			UserForm profileEditForm = this.userService.doGetUserbyId(userId);
			mv.addObject("profileEditForm", profileEditForm);
			return mv;
		}
		return new ModelAndView("redirect:/denied");
	}

	/**
	 * <h2>profileUpdate</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param profileForm
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/profile/update", params = "editProfile", method = RequestMethod.POST)
	public ModelAndView profileUpdate(@ModelAttribute("profileEditForm") UserForm profileForm, HttpSession session,
	        RedirectAttributes redirectAttributes) {
		this.userService.doUpdateUser(profileForm);
		session.setAttribute("LOGIN_USER", authService.doGetLoggedInUser());
		redirectAttributes.addFlashAttribute("errorMsg", messageSource.getMessage("M_SC_USR_0004", null, null));
		UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
		if (Integer.parseInt(user.getType()) == 2) {
			return new ModelAndView("redirect:/userMedicineList");
		}
		ModelAndView updateUserView = new ModelAndView("redirect:/medicineList");
		return updateUserView;
	}

	/**
	 * <h2>cancelProfileUpdate</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param profileForm
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/profile/update", params = "cancelProfile", method = RequestMethod.POST)
	public ModelAndView cancelProfileUpdate(@ModelAttribute("profileEditForm") UserForm profileForm) {
		ModelAndView cancelProfile = new ModelAndView("profileDetail");
		cancelProfile.addObject("profileDetailForm", profileForm);
		return cancelProfile;
	}

	/**
	 * <h2>delete</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @param request
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/user/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") Integer userId, HttpServletRequest request) {
		ModelAndView updateView = new ModelAndView("userList");
		this.userService.doSoftDelete(userId);
		List<UserDTO> userList = this.userService.doGetUserList();
		updateView.addObject("userList", userList);
		updateView.addObject("errorMsg", messageSource.getMessage("M_SC_USR_0005", null, null));
		return updateView;
	}

	/**
	 * <h2>logout</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @param session
	 * @return
	 * @return String
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpSession session) {
		session.removeAttribute("LOGIN_USER");
		authService.doLogout(request);
		return "redirect:/userLogin";
	}

	/**
	 * <h2>denied</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public ModelAndView denied() {
		ModelAndView createStudentView = new ModelAndView("accessDenied");
		return createStudentView;
	}

	/**
	 * <h2>getErrorMessage</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param request
	 * @param key
	 * @return
	 * @return String
	 */
	private String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}
		return error;
	}
}