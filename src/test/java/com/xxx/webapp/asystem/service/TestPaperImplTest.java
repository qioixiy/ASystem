package com.xxx.webapp.asystem.service;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xxx.webapp.asystem.pojo.TestPaper;
import com.xxx.webapp.asystem.service.TestPaperImpl;

public class TestPaperImplTest {
	TestPaperImpl tTestPaperImpl;

	@Before
	public void before() {
		tTestPaperImpl = new TestPaperImpl();
		System.out.println("before");
	}

	@Test
	public void TestPaperInsertTest() {

		System.out.println("TestPaperInsertTest");

		TestPaper tTestPaper = new TestPaper();
		tTestPaper.setId(1111111);
		tTestPaper.setName("xxx");
		tTestPaper.setDesc("test");
		tTestPaper.setCourseId(1);

		int ret = tTestPaperImpl.insert(tTestPaper);
		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void TestPaperDeleteByPrimaryKeyTest() {

		System.out.println("TestPaperDeleteByPrimaryKeyTest");

		int ret = tTestPaperImpl.deleteByPrimaryKey(1111111);

		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void TestPaperUpdateByPrimaryKeyTest() {

		System.out.println("TestPaperUpdateByPrimaryKeyTest");

		TestPaper tTestPaper = new TestPaper();
		tTestPaper.setId(1111111);
		tTestPaper.setName("xxxxx");
		tTestPaper.setDesc("test");
		tTestPaper.setCourseId(1);

		int ret = tTestPaperImpl.updateByPrimaryKey(tTestPaper);

		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void TestPaperSelectByPrimaryKeyTest() {
		TestPaper tTestPaper = tTestPaperImpl.selectByPrimaryKey(1111111);

		System.out.println("TestPaperSelectByPrimaryKeyTest");
		if (tTestPaper != null) {
			System.out.println("item");
			System.out.println("  id " + tTestPaper.getId());
			System.out.println("  name " + tTestPaper.getName());
			System.out.println("  Desc " + tTestPaper.getDesc());
			System.out.println("  CourseId " + tTestPaper.getCourseId());
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void TestPaperSelectAll() {
		List<TestPaper> tTestPapers = tTestPaperImpl.selectAll();
		
		for(TestPaper tTestPaper : tTestPapers) {
			System.out.println("item");
			System.out.println("  id " + tTestPaper.getId());
			System.out.println("  name " + tTestPaper.getName());
			System.out.println("  Desc " + tTestPaper.getDesc());
			System.out.println("  CourseId " + tTestPaper.getCourseId());
		}
	}
	
	@After
	public void after() {
		System.out.println("after");
	}
}
