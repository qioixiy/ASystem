package com.xxx.webapp.asystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xxx.webapp.asystem.pojo.ScoreResult;;

public interface ScoreResultDaoCustom {

	@Select("select * from score_result")
	public List<ScoreResult> selectAll();

}