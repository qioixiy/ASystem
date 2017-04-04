package com.xxx.webapp.asystem.service;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xxx.webapp.asystem.pojo.Teacher;
import com.xxx.webapp.asystem.service.TeacherImpl;

public class TeacherImplTest {
	TeacherImpl tTeacherImpl;

	@Before
	public void before() {
		tTeacherImpl = new TeacherImpl();
		System.out.println("before");
	}

	@Test
	public void TeacherInsertTest() {

		System.out.println("TeacherInsertTest");

		Teacher tTeacher = new Teacher();
		tTeacher.setId(1111111);
		tTeacher.setName("xxx");
		tTeacher.setNumber("test");
		tTeacher.setEmail("xxx");
		tTeacher.setTelphone("");

		int ret = tTeacherImpl.insert(tTeacher);
		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void TeacherDeleteByPrimaryKeyTest() {

		System.out.println("TeacherDeleteByPrimaryKeyTest");

		int ret = tTeacherImpl.deleteByPrimaryKey(1111111);

		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void TeacherUpdateByPrimaryKeyTest() {

		System.out.println("TeacherUpdateByPrimaryKeyTest");

		Teacher tTeacher = new Teacher();
		tTeacher.setId(1111111);
		tTeacher.setName("xxx");
		tTeacher.setNumber("test");
		tTeacher.setEmail("xxx");
		tTeacher.setTelphone("");


		int ret = tTeacherImpl.updateByPrimaryKey(tTeacher);

		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void TeacherSelectByPrimaryKeyTest() {
		Teacher tTeacher = tTeacherImpl.selectByPrimaryKey(1111111);

		System.out.println("TeacherSelectByPrimaryKeyTest");
		if (tTeacher != null) {
			System.out.println("item");
			System.out.println("  id " + tTeacher.getId());
			System.out.println("  name " + tTeacher.getName());
			System.out.println("  title " + tTeacher.getNumber());
			System.out.println("  detail " + tTeacher.getTelphone());
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void TeacherSelectAll() {
		List<Teacher> tTeachers = tTeacherImpl.selectAll();
		
		for(Teacher tTeacher : tTeachers) {
			System.out.println("item");
			System.out.println("item");
			System.out.println("  id " + tTeacher.getId());
			System.out.println("  name " + tTeacher.getName());
			System.out.println("  title " + tTeacher.getNumber());
			System.out.println("  detail " + tTeacher.getTelphone());
		}
	}
	
	@After
	public void after() {
		System.out.println("after");
	}
}
