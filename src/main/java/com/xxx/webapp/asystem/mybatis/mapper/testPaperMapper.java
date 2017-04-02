package com.xxx.webapp.asystem.mybatis.mapper;

import com.xxx.webapp.asystem.pojo.testPaper;

public interface testPaperMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(testPaper record);

    int insertSelective(testPaper record);

    testPaper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(testPaper record);

    int updateByPrimaryKey(testPaper record);
}