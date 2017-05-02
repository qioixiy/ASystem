package com.xxx.webapp.asystem.mybatis.mapper;

import com.xxx.webapp.asystem.pojo.Manager;

public interface ManagerMapper {
    int insert(Manager record);

    int insertSelective(Manager record);
}