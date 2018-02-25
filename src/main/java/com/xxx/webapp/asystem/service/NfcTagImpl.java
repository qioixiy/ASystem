package com.xxx.webapp.asystem.service;

import java.util.List;

import com.xxx.webapp.asystem.dao.NfcTagDao;
import com.xxx.webapp.asystem.dao.StudentDao;
import com.xxx.webapp.asystem.mybatis.mapper.NfcTagMapper;
import com.xxx.webapp.asystem.mybatis.mapper.StudentMapper;
import com.xxx.webapp.asystem.pojo.NfcTag;
import com.xxx.webapp.asystem.pojo.Student;

public class NfcTagImpl implements NfcTagMapper {
	
	private NfcTagDao mNfcTagDao = new NfcTagDao();
	
    public int deleteByPrimaryKey(Integer id) {
    	return mNfcTagDao.deleteByPrimaryKey(id);
	}

    public int insert(NfcTag record) {
    	return mNfcTagDao.insert(record);
	}

    public int insertSelective(NfcTag record) {
    	return mNfcTagDao.insertSelective(record);
	}

    public NfcTag selectByPrimaryKey(Integer id) {
    	return mNfcTagDao.selectByPrimaryKey(id);
	}

    public int updateByPrimaryKeySelective(NfcTag record) {
    	return mNfcTagDao.updateByPrimaryKeySelective(record);
	}

    public int updateByPrimaryKey(NfcTag record) {
    	return mNfcTagDao.updateByPrimaryKey(record);
	}

    public List<NfcTag> selectAll() {
    	return mNfcTagDao.selectAll();
	}
}