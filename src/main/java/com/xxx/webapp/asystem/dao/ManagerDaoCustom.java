package com.xxx.webapp.asystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xxx.webapp.asystem.pojo.Manager;

public interface ManagerDaoCustom {

	@Select("select * from manager")
	public List<Manager> selectAll();

}