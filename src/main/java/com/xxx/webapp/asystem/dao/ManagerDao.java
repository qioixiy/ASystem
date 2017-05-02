package com.xxx.webapp.asystem.dao;

import java.util.List;

import com.xxx.utils.mybatis.MyBatisDao;
import com.xxx.webapp.asystem.pojo.Manager;

public class ManagerDao extends MyBatisDao {
	public ManagerDao() {
		super("com.xxx.webapp.asystem.mybatis.mapper.ManagerMapper");
	}

	public int insert(Manager record) {
		return super.insert("insert", record);
	}
	
	public int deleteByPrimaryKey(Integer id) {
		return super.delete("deleteByPrimaryKey", id);
	}

	public int updateByPrimaryKey(Manager record) {
		return super.update("updateByPrimaryKey", record);
	}

	public int updateByPrimaryKeySelective(Manager record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Manager selectByPrimaryKey(Integer id) {
		return super.select("selectByPrimaryKey", id);
	}

	public int insertSelective(Manager record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<Manager> selectAll() {
		ManagerDaoCustom tManagerDaoCustom = getSqlSession().getMapper(ManagerDaoCustom.class);
		return tManagerDaoCustom.selectAll();
	}

}