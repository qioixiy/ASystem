package com.xxx.webapp.asystem.service;

import java.util.List;

import com.xxx.webapp.asystem.dao.TeacherDao;
import com.xxx.webapp.asystem.mybatis.mapper.TeacherMapper;
import com.xxx.webapp.asystem.pojo.Teacher;

public class TeacherImpl implements TeacherMapper {
	
	private TeacherDao mTeacherDao = new TeacherDao();
	
    public int deleteByPrimaryKey(Integer id) {
    	return mTeacherDao.deleteByPrimaryKey(id);
	}

    public int insert(Teacher record) {
    	return mTeacherDao.insert(record);
	}

    public int insertSelective(Teacher record) {
    	return mTeacherDao.insertSelective(record);
	}

    public Teacher selectByPrimaryKey(Integer id) {
    	return mTeacherDao.selectByPrimaryKey(id);
	}

    public int updateByPrimaryKeySelective(Teacher record) {
    	return mTeacherDao.updateByPrimaryKeySelective(record);
	}

    public int updateByPrimaryKey(Teacher record) {
    	return mTeacherDao.updateByPrimaryKey(record);
	}

    public List<Teacher> selectAll() {
    	return mTeacherDao.selectAll();
	}
}