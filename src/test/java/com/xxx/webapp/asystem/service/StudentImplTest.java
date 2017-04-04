package com.xxx.webapp.asystem.service;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xxx.webapp.asystem.pojo.Student;
import com.xxx.webapp.asystem.service.StudentImpl;

public class StudentImplTest {
	StudentImpl tStudentImpl;

	@Before
	public void before() {
		tStudentImpl = new StudentImpl();
		System.out.println("before");
	}

	@Test
	public void StudentInsertTest() {

		System.out.println("StudentInsertTest");

		Student tStudent = new Student();
		tStudent.setId(1111111);
		tStudent.setName("xxx");
		tStudent.setNumber("test");
		tStudent.setEmail("xxx");
		tStudent.setTelphone("11");

		int ret = tStudentImpl.insert(tStudent);
		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void StudentDeleteByPrimaryKeyTest() {

		System.out.println("StudentDeleteByPrimaryKeyTest");

		int ret = tStudentImpl.deleteByPrimaryKey(1111111);

		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void StudentUpdateByPrimaryKeyTest() {

		System.out.println("StudentUpdateByPrimaryKeyTest");

		Student tStudent = new Student();
		tStudent.setId(1111111);
		tStudent.setName("xxxxx");
		tStudent.setNumber("test");
		tStudent.setEmail("xxxxx");
		tStudent.setTelphone("x");

		int ret = tStudentImpl.updateByPrimaryKey(tStudent);

		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void StudentSelectByPrimaryKeyTest() {
		Student tStudent = tStudentImpl.selectByPrimaryKey(1111111);

		System.out.println("StudentSelectByPrimaryKeyTest");
		if (tStudent != null) {
			System.out.println("item");
			System.out.println("  id " + tStudent.getId());
			System.out.println("  name " + tStudent.getName());
			System.out.println("  title " + tStudent.getNumber());
			System.out.println("  detail " + tStudent.getEmail());
			System.out.println("  creater " + tStudent.getTelphone());
		} else {
			System.out.println("error");
		}
	}
	

	@Test
	public void StudentSelectAll() {
		List<Student> tStudents = tStudentImpl.selectAll();
		
		for(Student tStudent : tStudents) {
			System.out.println("item");
			System.out.println("  id " + tStudent.getId());
			System.out.println("  name " + tStudent.getName());
			System.out.println("  title " + tStudent.getNumber());
			System.out.println("  detail " + tStudent.getEmail());
			System.out.println("  creater " + tStudent.getTelphone());
		}
	}
	
	@After
	public void after() {
		System.out.println("after");
	}
}
