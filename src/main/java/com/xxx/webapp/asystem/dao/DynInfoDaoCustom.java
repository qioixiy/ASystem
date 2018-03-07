package com.xxx.webapp.asystem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.xxx.webapp.asystem.pojo.DynInfo;
import com.xxx.webapp.asystem.pojo.NfcTag;

public interface DynInfoDaoCustom {

	@Select("select * from dyn_info")
	public List<DynInfo> selectAll();
}