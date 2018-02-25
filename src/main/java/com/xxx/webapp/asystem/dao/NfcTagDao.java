package com.xxx.webapp.asystem.dao;

import java.util.List;

import com.xxx.utils.mybatis.MyBatisDao;
import com.xxx.webapp.asystem.pojo.NfcTag;

public class NfcTagDao extends MyBatisDao {
	public NfcTagDao() {
		super("com.xxx.webapp.asystem.mybatis.mapper.NfcTagMapper");
	}

	public int insert(NfcTag record) {
		return super.insert("insert", record);
	}
	
	public int deleteByPrimaryKey(Integer id) {
		return super.delete("deleteByPrimaryKey", id);
	}

	public int updateByPrimaryKey(NfcTag record) {
		return super.update("updateByPrimaryKey", record);
	}

	public int updateByPrimaryKeySelective(NfcTag record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public NfcTag selectByPrimaryKey(Integer id) {
		return super.select("selectByPrimaryKey", id);
	}

	public int insertSelective(NfcTag record) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<NfcTag> selectAll() {
		NfcTagDaoCustom tNfcTagDaoCustom = getSqlSession().getMapper(NfcTagDaoCustom.class);
		return tNfcTagDaoCustom.selectAll();
	}

}