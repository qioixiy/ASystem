package com.xxx.webapp.asystem.mybatis.mapper;

import com.xxx.webapp.asystem.pojo.ScoreResult;

public interface ScoreResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScoreResult record);

    int insertSelective(ScoreResult record);

    ScoreResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScoreResult record);

    int updateByPrimaryKey(ScoreResult record);
}