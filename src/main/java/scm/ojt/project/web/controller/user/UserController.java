package scm.ojt.project.web.controller.user;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import scm.ojt.project.bl.dto.UserDTO;
import scm.ojt.project.bl.service.UserService;
import scm.ojt.project.persistence.entity.User;
import scm.ojt.project.web.form.UserForm;

@Controller
public class UserController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public ModelAndView initView(ModelAndView model) {
		ModelAndView createUserListView = new ModelAndView("userList");
		List<UserDTO> userList = this.userService.getUserList();
		createUserListView.addObject("userList", userList);
		return createUserListView;
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public ModelAndView createUser(ModelAndView model) {
		User user = new User();
		ModelAndView mv = new ModelAndView("createUser");
		mv.addObject("userForm", user);
		return mv;
	}

	@RequestMapping(value = "/createUserConfirm", method = RequestMethod.POST)
	public ModelAndView callCreateUserConfirm(@ModelAttribute("userForm") @Valid UserForm userForm,
	        BindingResult result) throws ParseException {
		ModelAndView userConfirmView = new ModelAndView("createUser");
		if (result.hasErrors()) {
		} else if (userService.isUserExist(userForm.getEmail())) {
			userConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0010", null, null));
		} else {
			userConfirmView.addObject("userConfirmForm", userForm);
			userConfirmView.setViewName("createUserConfirm");
		}
		return userConfirmView;
	}

	/**
	 * <h2>insertUser</h2>
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
	@RequestMapping(value = "/saveUser", params = "addUser", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("userConfirmForm") @Valid UserForm userForm) throws ParseException {
		this.userService.saveUser(userForm);
		ModelAndView createUserView = new ModelAndView("userList");
		List<UserDTO> userList = this.userService.getUserList();
		createUserView.addObject("errorMsg", messageSource.getMessage("M_SC_0014", null, null));
		createUserView.addObject("userList", userList);
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
	@RequestMapping(value = "/saveUser", params = "cancel", method = RequestMethod.POST)
	public ModelAndView calcelUser(@ModelAttribute("userConfirmForm") @Valid UserForm userForm, BindingResult result) {
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
	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam("id") Integer userId) {
		ModelAndView updateView = new ModelAndView("updateUser");
		UserForm oldUserForm = this.userService.getUserbyId(userId);
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
	@RequestMapping(value = "/updateUserConfirm", params = "cancel", method = RequestMethod.POST)
	public ModelAndView calcelUpdateUser() {
		return new ModelAndView("redirect:/userList");
	}

	@RequestMapping(value = "/updateUserConfirm", params = "updateConfirm", method = RequestMethod.POST)
	public ModelAndView updateUserConfrim(@ModelAttribute("oldUserForm") @Valid UserForm updateuserForm,
	        BindingResult result) throws ParseException {
		ModelAndView updateUserConfirmView = new ModelAndView("updateUser");
		if (result.hasErrors()) {
			updateUserConfirmView.addObject("errorMsg", messageSource.getMessage("M_SC_0008", null, null));
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
	@RequestMapping(value = "/updateUser", params = "cancel", method = RequestMethod.POST)
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
	 * @param oldUserForm
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/updateUser", params = "updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute("updateUserConfirmForm") UserForm userForm) {
		this.userService.updateUser(userForm);
		ModelAndView updateUserView = new ModelAndView("redirect:/userList");
		updateUserView.addObject("errorMsg", messageSource.getMessage("M_SC_0018", null, null));
		return updateUserView;
	}

	/**
	 * <h2>delete</h2>
	 * <p>
	 * 
	 * </p>
	 *
	 * @param userId
	 * @return
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam("id") Integer userId, HttpServletRequest request) {
		ModelAndView updateView = new ModelAndView("userList");
		this.userService.softDelete(userId);
		List<UserDTO> userList = this.userService.getUserList();
		updateView.addObject("userList", userList);
		updateView.addObject("errorMsg", messageSource.getMessage("M_SC_0013", null, null));
		return updateView;
	}
}