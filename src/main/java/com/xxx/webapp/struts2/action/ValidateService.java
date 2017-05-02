package com.xxx.webapp.struts2.action;

import com.xxx.webapp.asystem.service.ManagerImpl;
import com.xxx.webapp.asystem.service.StudentImpl;
import com.xxx.webapp.asystem.service.TeacherImpl;

public class ValidateService {
	private String userType = "unkown";

	private final ManagerImpl fManagerImpl = new ManagerImpl();
	private final TeacherImpl fTeacherImpl = new TeacherImpl();
	private final StudentImpl fStudentImpl = new StudentImpl();
	
	public boolean validateUserAndPassword(String name, String password) {
		boolean ret = true;
		
		if (fManagerImpl.validate(name, password)) {
			userType = "manager";
		} else if (fTeacherImpl.validate(name, password)) {
			userType = "teacher";
		} else if (fStudentImpl.validate(name, password)) {
			userType = "student";
		} else {
			ret = false;
		}
		
		return ret;
	}
	
	public String getUserType() {
		return userType;
	}
}
