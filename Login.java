package com.green.finch.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Login {

	@NotNull
	private String userName;
	
	@NotNull @Min(8)
	private String password;

	
	public Login() {
		super();
	}

	public Login(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
