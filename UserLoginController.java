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

import com.green.finch.model.Login;
import com.green.finch.model.User;
import com.green.finch.service.UserService;

/**
 * 
 * @author Parimal 
 * This class is used to handle the lgin request.
 * 
 */
@Controller
public class UserLoginController {

	public static final String WELCOME_JSP = "welcomeuser";
	public static final String LOGIN_JSP = "login";

	@Autowired
	public UserService userService;

	@Autowired
	@Qualifier("loginValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/**
	 * Redirect to login.jsp
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(LOGIN_JSP);
		mav.addObject(LOGIN_JSP, new Login());
		return mav;
	}

	/**
	 * Call the database for the entered user id and password and validate against the database.
	 * @param request
	 * @param response
	 * @param login
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@Valid @ModelAttribute("login") Login login, BindingResult bindingResult) {
		ModelAndView mav = null;

		if (bindingResult.hasErrors()) {
			mav = new ModelAndView(LOGIN_JSP);
			mav.addObject(LOGIN_JSP, login);
			return mav;
		}

		User user = userService.validateLoggedInUser(login);
		if (null != user) {
			mav = new ModelAndView(WELCOME_JSP);
			mav.addObject("message",
					"Welcome " + user.getFirstName() + " " + user.getLastName() + ". Login Successful...");
		} else {
			mav = new ModelAndView(LOGIN_JSP);
			mav.addObject("message", "Please ennter valid Username or Password !!!");
		}
		return mav;
	}
}
