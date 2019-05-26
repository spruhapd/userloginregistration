/**
 * 
 */
package com.green.finch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.green.finch.model.User;
import com.green.finch.service.UserService;

/**
 * This class is used to handle the user registration request to create a new request.
 * @author Parimal
 *
 */

@Controller
public class UserRegistrationController {

	public static final String WELCOME_JSP = "welcomeuser";

	@Autowired
	@Qualifier("userService")
	public UserService userService;

	@Autowired
	@Qualifier("userValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/**
	 * Redirect to user rgistration page.
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new User());
		return mav;
	}

	/**
	 * Stored the entered user details. also check for the unique username before insert
	 * @param request
	 * @param response
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView registeredUser(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		String welcome = null;
		if (bindingResult.hasErrors()) {
			ModelAndView mav = new ModelAndView("register");
			mav.addObject("user", user);
			return mav;
		}

		int ret = userService.register(user);
		if (ret == 0) {
			welcome = "User Name " + user.getUserName() + " already exist !!!";
		} else {
			welcome = "Welcome " + user.getFirstName() + " " + user.getLastName();
		}
		return new ModelAndView(WELCOME_JSP, "message", welcome);
	}
}
