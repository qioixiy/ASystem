package com.xxx.webapp.struts2.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class LoginActionAsystem {

	private final ValidateService tValidateService = new ValidateService();
	private String userName;
	private String password;

	public String execute() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		System.out.println(session.get("userName"));
		if (!userName.isEmpty() && !password.isEmpty()
				&& tValidateService.validateUserAndPassword(userName, password)) {
			session.put("userName", userName);
			session.put("userType", tValidateService.getUserType());
			
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
