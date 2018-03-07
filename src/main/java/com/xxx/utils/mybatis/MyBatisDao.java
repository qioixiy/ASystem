package com.xxx.utils.mybatis;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.util.CollectionUtils;

public class MyBatisDao {

	private String namespaceName;
	private SqlSession session = MyBatisUtil.getSession();
	
	public MyBatisDao(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	protected int insert(String key, Object object) {
		if (object != null) {
			SqlSession session = getSqlSession();
			int ret = session.insert(createStatementName(key), object);
			session.commit();
			return ret;
		}
		return 0;
	}
	
	protected int delete(String key, Serializable id) {
		if (id != null) {
			SqlSession session = getSqlSession();
			int ret = session.delete(createStatementName(key), id);
			session.commit();
			return ret;
		}
		return 0;
	}
	
	protected int update(String key, Object object) {
		if (object != null) {
			SqlSession session = getSqlSession();
			int ret = session.update(createStatementName(key), object);
			session.commit();
			return ret;
		}
		return 0;
	}	
	protected <T> List<T> selectList(String key, Object params) {
		if (params != null) {
			return getSqlSession().selectList(createStatementName(key), params);
		} else {
			return null;
		}
	}

	protected <T> T select(String key, Object params) {
		List<T> list = this.selectList(key, params);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		
		if (list.size() == 1) {
			return list.get(0);
		} 
		
		if (list.size() > 1) {
			throw new TooManyResultsException("Expected one result (or null) to be returned by selectOne(), but found: "+ list.size());
		}
		
		return null;
	}

	protected SqlSession getSqlSession() {
		return session;
	}
	
	private String createStatementName(String id) {
		return namespaceName + "." + id;
	}
}