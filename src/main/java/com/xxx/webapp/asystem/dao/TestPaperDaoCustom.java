package com.xxx.webapp.asystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xxx.webapp.asystem.pojo.TestPaper;;

public interface TestPaperDaoCustom {

	@Select("select * from test_paper")
	public List<TestPaper> selectAll();

}