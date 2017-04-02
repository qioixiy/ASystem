package com.xxx.webapp.asystem.dao;

import com.xxx.utils.mybatis.MyBatisDao;
import com.xxx.webapp.asystem.pojo.Course;

public class CourseDao extends MyBatisDao {

	public CourseDao() {
		super("com.xxx.webapp.asystem.mybatis.mapper.CourseMapper");
	}

	public int insert(Course record) {
		return super.insert("insert", record);
	}
	
	public int deleteByPrimaryKey(Integer id) {
		return super.delete("deleteByPrimaryKey", id);
	}

	public int updateByPrimaryKey(Course record) {
		return super.update("updateByPrimaryKey", record);
	}

	public int updateByPrimaryKeySelective(Course record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Course selectByPrimaryKey(Integer id) {
		return super.select("selectByPrimaryKey", id);
	}

	public int insertSelective(Course record) {
		// TODO Auto-generated method stub
		return 0;
	}

}