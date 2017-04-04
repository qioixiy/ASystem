package com.xxx.webapp.asystem.dao;

import java.util.List;

import com.xxx.utils.mybatis.MyBatisDao;
import com.xxx.webapp.asystem.pojo.Student;

public class StudentDao extends MyBatisDao {
	public StudentDao() {
		super("com.xxx.webapp.asystem.mybatis.mapper.StudentMapper");
	}

	public int insert(Student record) {
		return super.insert("insert", record);
	}
	
	public int deleteByPrimaryKey(Integer id) {
		return super.delete("deleteByPrimaryKey", id);
	}

	public int updateByPrimaryKey(Student record) {
		return super.update("updateByPrimaryKey", record);
	}

	public int updateByPrimaryKeySelective(Student record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Student selectByPrimaryKey(Integer id) {
		return super.select("selectByPrimaryKey", id);
	}

	public int insertSelective(Student record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<Student> selectAll() {
		StudentDaoCustom tStudentDaoCustom = getSqlSession().getMapper(StudentDaoCustom.class);
		return tStudentDaoCustom.selectAll();
	}

}