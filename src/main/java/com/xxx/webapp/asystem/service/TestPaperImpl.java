package com.xxx.webapp.asystem.service;

import java.util.List;

import com.xxx.webapp.asystem.dao.TestPaperDao;
import com.xxx.webapp.asystem.mybatis.mapper.TestPaperMapper;
import com.xxx.webapp.asystem.pojo.TestPaper;

public class TestPaperImpl implements TestPaperMapper {
	
	private TestPaperDao mTestPaperDao = new TestPaperDao();
	
    public int deleteByPrimaryKey(Integer id) {
    	return mTestPaperDao.deleteByPrimaryKey(id);
	}

    public int insert(TestPaper record) {
    	return mTestPaperDao.insert(record);
	}

    public int insertSelective(TestPaper record) {
    	return mTestPaperDao.insertSelective(record);
	}

    public TestPaper selectByPrimaryKey(Integer id) {
    	return mTestPaperDao.selectByPrimaryKey(id);
	}

    public int updateByPrimaryKeySelective(TestPaper record) {
    	return mTestPaperDao.updateByPrimaryKeySelective(record);
	}

    public int updateByPrimaryKey(TestPaper record) {
    	return mTestPaperDao.updateByPrimaryKey(record);
	}

    public List<TestPaper> selectAll() {
    	return mTestPaperDao.selectAll();
	}
}