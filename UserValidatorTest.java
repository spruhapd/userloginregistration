package com.green.finch.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.green.finch.config.UserTestConfig;
import com.green.finch.model.User;
import com.green.finch.validator.UserValidator;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UserTestConfig.class })
public class UserValidatorTest {

	private UserValidator userValidator = new UserValidator();

	@Test
	public void testValidateUserPasswordNegative() {
		User user = new User();
		user.setFirstName("Green");
		user.setLastName("Finch");
		user.setUserName("greenfinch");
		user.setPassword("test");
		Errors errors = new BeanPropertyBindingResult(user, "validuser");
		userValidator.validate(user, errors);
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("password"));
	}

	@Test
	public void testValidateUserPasswordPositive() {
		User user = new User();
		user.setFirstName("Green");
		user.setLastName("Finch");
		user.setUserName("greenfinch");
		user.setPassword("Spruha@3048");
		Errors errors = new BeanPropertyBindingResult(user, "validuser");
		userValidator.validate(user, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void testValidateUserPasswordPositive1() {
		User user = new User();
		user.setFirstName("Green");
		user.setLastName("Finch");
		user.setUserName("greenfinch");
		user.setPassword("Spruha@3");
		Errors errors = new BeanPropertyBindingResult(user, "validuser");
		userValidator.validate(user, errors);
		assertFalse(errors.hasErrors());
	}

	@Test
	public void testValidateUserPasswordNgt1() {
		User user = new User();
		user.setFirstName("Green");
		user.setLastName("Finch");
		user.setUserName("greenfinch");
		user.setPassword("SPRUHA@3048");
		Errors errors = new BeanPropertyBindingResult(user, "validuser");
		userValidator.validate(user, errors);
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("password"));
	}
	
	@Test
	public void testValidateUserPasswordNgt2() {
		User user = new User();
		user.setFirstName("Green");
		user.setLastName("Finch");
		user.setUserName("greenfinch");
		user.setPassword("test123@3048");
		Errors errors = new BeanPropertyBindingResult(user, "validuser");
		userValidator.validate(user, errors);
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("password"));
	}
	
	@Test
	public void testValidateUserNameNgt1() {
		User user = new User();
		user.setFirstName("Green");
		user.setLastName("Finch");
		user.setUserName("Test@123");
		user.setPassword("Spruha@123");
		Errors errors = new BeanPropertyBindingResult(user, "validuser");
		userValidator.validate(user, errors);
		assertTrue(errors.hasErrors());
		assertNotNull(errors.getFieldError("userName"));
	}
	
	@Test
	public void testValidateUserNamePositive() {
		User user = new User();
		user.setFirstName("Green");
		user.setLastName("Finch");
		user.setUserName("Test12Abc");
		user.setPassword("Spruha@123");
		Errors errors = new BeanPropertyBindingResult(user, "validuser");
		userValidator.validate(user, errors);
		assertFalse(errors.hasErrors());
	}
}
