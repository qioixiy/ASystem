package com.xxx.webapp.asystem.dao;

import java.util.List;

import com.xxx.utils.mybatis.MyBatisDao;
import com.xxx.webapp.asystem.pojo.Teacher;

public class TeacherDao extends MyBatisDao {
	public TeacherDao() {
		super("com.xxx.webapp.asystem.mybatis.mapper.TeacherMapper");
	}

	public int insert(Teacher record) {
		return super.insert("insert", record);
	}
	
	public int deleteByPrimaryKey(Integer id) {
		return super.delete("deleteByPrimaryKey", id);
	}

	public int updateByPrimaryKey(Teacher record) {
		return super.update("updateByPrimaryKey", record);
	}

	public int updateByPrimaryKeySelective(Teacher record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Teacher selectByPrimaryKey(Integer id) {
		return super.select("selectByPrimaryKey", id);
	}

	public int insertSelective(Teacher record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<Teacher> selectAll() {
		TeacherDaoCustom tTeacherDaoCustom = getSqlSession().getMapper(TeacherDaoCustom.class);
		return tTeacherDaoCustom.selectAll();
	}

}