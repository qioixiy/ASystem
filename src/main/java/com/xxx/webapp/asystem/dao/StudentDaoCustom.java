package com.xxx.webapp.asystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xxx.webapp.asystem.pojo.Student;

public interface StudentDaoCustom {

	@Select("select * from student")
	public List<Student> selectAll();

	@Select("select * from student where name = #{0} and password = #{1}")
	public List<Student> validate(String name, String password);
		
	@Select("select * from student where name = #{0} and password is null")
	public List<Student> validateNull(String name);

}