package com.xxx.webapp.struts2.action;

import com.xxx.webapp.asystem.pojo.Teacher;
import com.xxx.webapp.asystem.service.StudentImpl;
import com.xxx.webapp.asystem.service.TeacherImpl;

public class RegisterService {

	private final TeacherImpl fTeacherImpl = new TeacherImpl();
	
	public boolean RegisterTeacher(String name, String password) {
		boolean ret = true;
		
		Teacher record = new Teacher();
		record.setName(name);
		
		try {
			fTeacherImpl.insert(record);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return ret;
	}
}
