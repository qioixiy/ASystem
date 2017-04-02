package com.xxx.webapp.asystem.mybatis.mapper;

import com.xxx.webapp.asystem.pojo.scoreResult;

public interface scoreResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(scoreResult record);

    int insertSelective(scoreResult record);

    scoreResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(scoreResult record);

    int updateByPrimaryKey(scoreResult record);
}