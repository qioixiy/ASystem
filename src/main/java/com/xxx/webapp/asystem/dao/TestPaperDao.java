package com.xxx.webapp.asystem.dao;

import java.util.List;

import com.xxx.utils.mybatis.MyBatisDao;
import com.xxx.webapp.asystem.pojo.TestPaper;

public class TestPaperDao extends MyBatisDao {
	public TestPaperDao() {
		super("com.xxx.webapp.asystem.mybatis.mapper.TestPaperMapper");
	}

	public int insert(TestPaper record) {
		return super.insert("insert", record);
	}
	
	public int deleteByPrimaryKey(Integer id) {
		return super.delete("deleteByPrimaryKey", id);
	}

	public int updateByPrimaryKey(TestPaper record) {
		return super.update("updateByPrimaryKey", record);
	}

	public int updateByPrimaryKeySelective(TestPaper record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public TestPaper selectByPrimaryKey(Integer id) {
		return super.select("selectByPrimaryKey", id);
	}

	public int insertSelective(TestPaper record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<TestPaper> selectAll() {
		TestPaperDaoCustom tTestPaperDaoCustom = getSqlSession().getMapper(TestPaperDaoCustom.class);
		return tTestPaperDaoCustom.selectAll();
	}

}