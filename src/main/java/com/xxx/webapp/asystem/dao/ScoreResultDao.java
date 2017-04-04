package com.xxx.webapp.asystem.dao;

import java.util.List;

import com.xxx.utils.mybatis.MyBatisDao;
import com.xxx.webapp.asystem.pojo.ScoreResult;

public class ScoreResultDao extends MyBatisDao {
	public ScoreResultDao() {
		super("com.xxx.webapp.asystem.mybatis.mapper.ScoreResultMapper");
	}

	public int insert(ScoreResult record) {
		return super.insert("insert", record);
	}
	
	public int deleteByPrimaryKey(Integer id) {
		return super.delete("deleteByPrimaryKey", id);
	}

	public int updateByPrimaryKey(ScoreResult record) {
		return super.update("updateByPrimaryKey", record);
	}

	public int updateByPrimaryKeySelective(ScoreResult record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ScoreResult selectByPrimaryKey(Integer id) {
		return super.select("selectByPrimaryKey", id);
	}

	public int insertSelective(ScoreResult record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<ScoreResult> selectAll() {
		ScoreResultDaoCustom tScoreResultDaoCustom = getSqlSession().getMapper(ScoreResultDaoCustom.class);
		return tScoreResultDaoCustom.selectAll();
	}

}