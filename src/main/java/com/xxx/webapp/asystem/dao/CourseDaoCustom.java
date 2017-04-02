package com.xxx.webapp.asystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xxx.webapp.asystem.pojo.Course;

public interface CourseDaoCustom {

	@Select("select * from course")
	public List<Course> selectAll();

}