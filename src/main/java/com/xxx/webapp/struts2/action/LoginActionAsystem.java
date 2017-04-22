package com.xxx.webapp.struts2.action;

public class LoginActionAsystem {

	private final ValidateService tValidateService = new ValidateService();
	private String userName;
	private String password;

	public String execute() {
		if (!userName.isEmpty() && !password.isEmpty()
				&& tValidateService.validateUserAndPassword(userName, password)) {
			return "success";
		} else {
			return "error";
		}
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
