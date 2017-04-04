package com.xxx.webapp.asystem.service;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xxx.webapp.asystem.pojo.ScoreResult;
import com.xxx.webapp.asystem.service.ScoreResultImpl;

public class ScoreResultImplTest {
	ScoreResultImpl tScoreResultImpl;

	@Before
	public void before() {
		tScoreResultImpl = new ScoreResultImpl();
		System.out.println("before");
	}

	@Test
	public void ScoreResultInsertTest() {

		System.out.println("ScoreResultInsertTest");

		ScoreResult tScoreResult = new ScoreResult();
		tScoreResult.setId(1111111);
		tScoreResult.setStudentId(1);
		tScoreResult.setTeacherId(1);
		tScoreResult.setTestPaperId(1);
		tScoreResult.setDesc("{}");

		int ret = tScoreResultImpl.insert(tScoreResult);
		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void ScoreResultDeleteByPrimaryKeyTest() {

		System.out.println("ScoreResultDeleteByPrimaryKeyTest");

		int ret = tScoreResultImpl.deleteByPrimaryKey(1111111);

		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void ScoreResultUpdateByPrimaryKeyTest() {

		System.out.println("ScoreResultUpdateByPrimaryKeyTest");

		ScoreResult tScoreResult = new ScoreResult();
		tScoreResult.setId(1111111);
		tScoreResult.setStudentId(1);
		tScoreResult.setTeacherId(1);
		tScoreResult.setTestPaperId(1);
		tScoreResult.setDesc("");

		int ret = tScoreResultImpl.updateByPrimaryKey(tScoreResult);

		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void ScoreResultSelectByPrimaryKeyTest() {
		ScoreResult tScoreResult = tScoreResultImpl.selectByPrimaryKey(1111111);

		System.out.println("ScoreResultSelectByPrimaryKeyTest");
		if (tScoreResult != null) {
			System.out.println("item");
			System.out.println("  id " + tScoreResult.getId());
			System.out.println("  StudentId " + tScoreResult.getStudentId());
			System.out.println("  TeacherId " + tScoreResult.getTeacherId());
			System.out.println("  TestPaperId " + tScoreResult.getTestPaperId());
			System.out.println("  Desc " + tScoreResult.getDesc());
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void ScoreResultSelectAll() {
		List<ScoreResult> tScoreResults = tScoreResultImpl.selectAll();
		
		for(ScoreResult tScoreResult : tScoreResults) {
			System.out.println("item");
			System.out.println("  id " + tScoreResult.getId());
			System.out.println("  StudentId " + tScoreResult.getStudentId());
			System.out.println("  TeacherId " + tScoreResult.getTeacherId());
			System.out.println("  TestPaperId " + tScoreResult.getTestPaperId());
			System.out.println("  Desc " + tScoreResult.getDesc());
		}
	}
	
	@After
	public void after() {
		System.out.println("after");
	}
}
