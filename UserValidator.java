package com.green.finch.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.green.finch.model.User;

public class UserValidator implements Validator {

	private static final String PASSWORD_REGEXP = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEXP);
	private static final String PASSWORD = "Password";
	
	private static final String USER_NAME_REGEXP = "^[a-zA-Z0-9]+$";
	private static final Pattern USER_NAME_PATTERN = Pattern.compile(USER_NAME_REGEXP);
	private static final String USERNAME = "UserName";

	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		User user = (User) target;

		if (null != user && null != user.getUserName() && user.getUserName().length() > 0) {
			if (!validateField(user.getUserName(), USERNAME)) {
				errors.rejectValue("userName", "userName.criteria", null,
						"User Name should accept only alphaneueric characters.");
			}
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "userName.required");
		}

		if (null != user && null != user.getPassword() && user.getPassword().length() > 0) {
			if (!validateField(user.getPassword(), PASSWORD)) {
				errors.rejectValue("password", "password.criteria", null,
						"Password should be minimum 8 char long and contains at least one Upper case letter, One lower case letter and one Special character.");
			}
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required");
	}

	private boolean validateField(String input, String field) {
		boolean valid = false;
		if (field.equals(PASSWORD) && PASSWORD_PATTERN.matcher(input).matches()) {
			valid = true;
		} else if (field.equals(USERNAME) && USER_NAME_PATTERN.matcher(input).matches()) {
			valid = true;
		}
		return valid;
	}
}
