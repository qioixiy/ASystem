package com.xxx.webapp.asystem.mybatis.mapper;

import com.xxx.webapp.asystem.pojo.NfcTag;

public interface NfcTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NfcTag record);

    int insertSelective(NfcTag record);

    NfcTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NfcTag record);

    int updateByPrimaryKey(NfcTag record);
}