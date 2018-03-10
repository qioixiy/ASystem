package com.xxx.webapp.asystem.service;

import java.util.List;

import com.xxx.webapp.asystem.dao.DynInfoDao;
import com.xxx.webapp.asystem.mybatis.mapper.DynInfoMapper;
import com.xxx.webapp.asystem.pojo.DynInfo;

public class DynInfoImpl implements DynInfoMapper {
	
	private DynInfoDao mDynInfoDao = new DynInfoDao();
	
    public int deleteByPrimaryKey(Integer id) {
    	return mDynInfoDao.deleteByPrimaryKey(id);
	}

    public int insert(DynInfo record) {
    	return mDynInfoDao.insert(record);
	}

    public int insertSelective(DynInfo record) {
    	return mDynInfoDao.insertSelective(record);
	}

    public DynInfo selectByPrimaryKey(Integer id) {
    	return mDynInfoDao.selectByPrimaryKey(id);
	}

    public int updateByPrimaryKeySelective(DynInfo record) {
    	return mDynInfoDao.updateByPrimaryKeySelective(record);
	}

    public int updateByPrimaryKey(DynInfo record) {
    	return mDynInfoDao.updateByPrimaryKey(record);
	}

    public List<DynInfo> selectAll() {
    	return mDynInfoDao.selectAll();
	}
}