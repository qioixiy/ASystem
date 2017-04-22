package com.xxx.webapp.struts2.action;

import com.xxx.webapp.asystem.service.StudentImpl;
import com.xxx.webapp.asystem.service.TeacherImpl;

public class ValidateService {

	private final TeacherImpl fTeacherImpl = new TeacherImpl();
	private final StudentImpl fStudentImpl = new StudentImpl();
	
	public boolean validateUserAndPassword(String name, String password) {
		boolean ret = true;
		
		ret = fTeacherImpl.validate(name, password);
		
		return ret;
	}
}
