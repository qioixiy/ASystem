package com.xxx.webapp.asystem.service;

import java.util.List;

import com.xxx.webapp.asystem.dao.ManagerDao;
import com.xxx.webapp.asystem.mybatis.mapper.ManagerMapper;
import com.xxx.webapp.asystem.pojo.Manager;

public class ManagerImpl implements ManagerMapper {
	
	private ManagerDao mManagerDao = new ManagerDao();
	
    public int deleteByPrimaryKey(Integer id) {
    	return mManagerDao.deleteByPrimaryKey(id);
	}

    public int insert(Manager record) {
    	return mManagerDao.insert(record);
	}

    public int insertSelective(Manager record) {
    	return mManagerDao.insertSelective(record);
	}

    public Manager selectByPrimaryKey(Integer id) {
    	return mManagerDao.selectByPrimaryKey(id);
	}

    public int updateByPrimaryKeySelective(Manager record) {
    	return mManagerDao.updateByPrimaryKeySelective(record);
	}

    public int updateByPrimaryKey(Manager record) {
    	return mManagerDao.updateByPrimaryKey(record);
	}

    public List<Manager> selectAll() {
    	return mManagerDao.selectAll();
	}

    public boolean validate(String name, String password) {
    	return mManagerDao.validate(name, password);
	}
}