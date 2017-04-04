package com.xxx.webapp.asystem.service;

import java.util.List;

import com.xxx.webapp.asystem.dao.StudentDao;
import com.xxx.webapp.asystem.mybatis.mapper.StudentMapper;
import com.xxx.webapp.asystem.pojo.Student;

public class StudentImpl implements StudentMapper {
	
	private StudentDao mStudentDao = new StudentDao();
	
    public int deleteByPrimaryKey(Integer id) {
    	return mStudentDao.deleteByPrimaryKey(id);
	}

    public int insert(Student record) {
    	return mStudentDao.insert(record);
	}

    public int insertSelective(Student record) {
    	return mStudentDao.insertSelective(record);
	}

    public Student selectByPrimaryKey(Integer id) {
    	return mStudentDao.selectByPrimaryKey(id);
	}

    public int updateByPrimaryKeySelective(Student record) {
    	return mStudentDao.updateByPrimaryKeySelective(record);
	}

    public int updateByPrimaryKey(Student record) {
    	return mStudentDao.updateByPrimaryKey(record);
	}

    public List<Student> selectAll() {
    	return mStudentDao.selectAll();
	}
}