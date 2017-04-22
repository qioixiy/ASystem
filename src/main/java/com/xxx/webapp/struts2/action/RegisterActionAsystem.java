package com.xxx.webapp.struts2.action;

public class RegisterActionAsystem {

	private final RegisterService tRegisterService = new RegisterService();
	private String userName;
	private String password;
	private String passwordR;

	public String execute() {
		if (!userName.isEmpty() && !password.isEmpty() && !passwordR.isEmpty()
				&&(password.equals(passwordR))
				&& tRegisterService.RegisterTeacher(userName, password)) {
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
	public String getPasswordR() {
		return passwordR;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPasswordR(String password) {
		this.passwordR = password;
	}
}
