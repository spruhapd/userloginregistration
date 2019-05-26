package com.green.finch.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.green.finch.dbexecutor.UserQueryExecutor;
import com.green.finch.model.Login;
import com.green.finch.model.User;

public class UserDaoImpl implements UserDao {
	
	@Autowired
	public UserQueryExecutor userQueryExecutor;
	
	public int register(User user) {
		return userQueryExecutor.createUser(user);
	}

	public User validateUser(Login login) {
		return userQueryExecutor.getRegisterUser(login.getUserName());
	}

	public User validateLoggedInUser(Login login) {
		return userQueryExecutor.validateLoggedInUser(login);
	}
}
