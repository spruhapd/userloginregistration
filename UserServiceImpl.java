package com.green.finch.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.green.finch.dao.UserDao;
import com.green.finch.model.Login;
import com.green.finch.model.User;

public class UserServiceImpl implements UserService {
	
	@Autowired
	public UserDao userDao;
	
	public User validateUser(Login login) {
		return userDao.validateUser(login);
	}

	public int register(User user) {
		return userDao.register(user);		
	}
	
	public User validateLoggedInUser(Login login) {
		return userDao.validateLoggedInUser(login);
	}

}
