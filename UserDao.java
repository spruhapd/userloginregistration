package com.green.finch.dao;

import com.green.finch.model.Login;
import com.green.finch.model.User;

public interface UserDao {

	public int register(User user);

	public User validateUser(Login login);
	
	public User validateLoggedInUser(Login login);
}