package com.green.finch.service;

import com.green.finch.model.Login;
import com.green.finch.model.User;

public interface UserService {
	public User validateUser(Login login);

	int register(User user);
	
	public User validateLoggedInUser(Login login);
}
