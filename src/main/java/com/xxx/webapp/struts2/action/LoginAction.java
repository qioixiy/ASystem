package com.xxx.webapp.struts2.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction {

	private String userName;
	private String password;

	public String execute() {
		if (userName.isEmpty() || password.isEmpty())
			return "error";
		else
			return "success";
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
