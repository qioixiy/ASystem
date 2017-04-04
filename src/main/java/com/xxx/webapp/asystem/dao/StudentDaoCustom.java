package com.xxx.webapp.asystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xxx.webapp.asystem.pojo.Student;

public interface StudentDaoCustom {

	@Select("select * from student")
	public List<Student> selectAll();

}