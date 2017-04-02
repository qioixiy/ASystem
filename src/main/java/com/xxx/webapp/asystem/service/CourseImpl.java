package com.xxx.webapp.asystem.service;

import com.xxx.webapp.asystem.dao.CourseDao;
import com.xxx.webapp.asystem.mybatis.mapper.CourseMapper;
import com.xxx.webapp.asystem.pojo.Course;

public class CourseImpl implements CourseMapper {
	
	private CourseDao mCourseDao = new CourseDao();
	
    public int deleteByPrimaryKey(Integer id) {
    	return mCourseDao.deleteByPrimaryKey(id);
	}

    public int insert(Course record) {
    	return mCourseDao.insert(record);
	}

    public int insertSelective(Course record) {
    	return mCourseDao.insertSelective(record);
	}

    public Course selectByPrimaryKey(Integer id) {
    	return mCourseDao.selectByPrimaryKey(id);
	}

    public int updateByPrimaryKeySelective(Course record) {
    	return mCourseDao.updateByPrimaryKeySelective(record);
	}

    public int updateByPrimaryKey(Course record) {
    	return mCourseDao.updateByPrimaryKey(record);
	}
}