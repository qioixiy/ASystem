package com.xxx.webapp.asystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xxx.webapp.asystem.pojo.Teacher;;

public interface TeacherDaoCustom {

	@Select("select * from teacher")
	public List<Teacher> selectAll();
	
	@Select("select * from teacher where name = #{0} and password = #{1}")
	public List<Teacher> validate(String name, String password);
}