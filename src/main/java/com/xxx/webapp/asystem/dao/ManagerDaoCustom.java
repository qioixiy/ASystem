package com.xxx.webapp.asystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xxx.webapp.asystem.pojo.Manager;

public interface ManagerDaoCustom {

	@Select("select * from manager")
	public List<Manager> selectAll();

	@Select("select * from manager where name = #{0} and password = #{1}")
	public List<Manager> validate(String name, String password);
	
	@Select("select * from manager where name = #{0} and password is null")
	public List<Manager> validateNull(String name);
}