package com.xxx.webapp.asystem.dao;

import java.util.List;

import com.xxx.utils.mybatis.MyBatisDao;
import com.xxx.webapp.asystem.pojo.DynInfo;

public class DynInfoDao extends MyBatisDao {
	public DynInfoDao() {
		super("com.xxx.webapp.asystem.mybatis.mapper.DynInfoMapper");
	}

	public int insert(DynInfo record) {
		return super.insert("insert", record);
	}
	
	public int deleteByPrimaryKey(Integer id) {
		return super.delete("deleteByPrimaryKey", id);
	}

	public int updateByPrimaryKey(DynInfo record) {
		return super.update("updateByPrimaryKey", record);
	}

	public int updateByPrimaryKeySelective(DynInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public DynInfo selectByPrimaryKey(Integer id) {
		return super.select("selectByPrimaryKey", id);
	}

	public int insertSelective(DynInfo record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<DynInfo> selectAll() {
		DynInfoDaoCustom tDynInfoDaoCustom = getSqlSession().getMapper(DynInfoDaoCustom.class);
		return tDynInfoDaoCustom.selectAll();
	}

}