package com.xxx.webapp.asystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xxx.webapp.asystem.pojo.NfcTag;

public interface NfcTagDaoCustom {

	@Select("select * from nfc_tag")
	public List<NfcTag> selectAll();
}