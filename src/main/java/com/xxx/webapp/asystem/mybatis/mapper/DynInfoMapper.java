package com.xxx.webapp.asystem.mybatis.mapper;

import com.xxx.webapp.asystem.pojo.DynInfo;

public interface DynInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DynInfo record);

    int insertSelective(DynInfo record);

    DynInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DynInfo record);

    int updateByPrimaryKey(DynInfo record);
}