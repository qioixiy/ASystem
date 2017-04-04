package com.xxx.webapp.asystem.service;

import java.util.List;

import com.xxx.webapp.asystem.dao.ScoreResultDao;
import com.xxx.webapp.asystem.mybatis.mapper.ScoreResultMapper;
import com.xxx.webapp.asystem.pojo.ScoreResult;

public class ScoreResultImpl implements ScoreResultMapper {
	
	private ScoreResultDao mScoreResultDao = new ScoreResultDao();
	
    public int deleteByPrimaryKey(Integer id) {
    	return mScoreResultDao.deleteByPrimaryKey(id);
	}

    public int insert(ScoreResult record) {
    	return mScoreResultDao.insert(record);
	}

    public int insertSelective(ScoreResult record) {
    	return mScoreResultDao.insertSelective(record);
	}

    public ScoreResult selectByPrimaryKey(Integer id) {
    	return mScoreResultDao.selectByPrimaryKey(id);
	}

    public int updateByPrimaryKeySelective(ScoreResult record) {
    	return mScoreResultDao.updateByPrimaryKeySelective(record);
	}

    public int updateByPrimaryKey(ScoreResult record) {
    	return mScoreResultDao.updateByPrimaryKey(record);
	}

    public List<ScoreResult> selectAll() {
    	return mScoreResultDao.selectAll();
	}
}