package com.xxx.webapp.asystem.service;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xxx.webapp.asystem.pojo.Course;
import com.xxx.webapp.asystem.service.CourseImpl;

public class CourseImplTest {
	CourseImpl tCourseImpl;

	@Before
	public void before() {
		tCourseImpl = new CourseImpl();
		System.out.println("before");
	}

	@Test
	public void CourseInsertTest() {

		System.out.println("CourseInsertTest");

		Course tCourse = new Course();
		tCourse.setId(1111111);
		tCourse.setName("xxx");
		tCourse.setTitle("test");
		tCourse.setDetail("xxx");
		tCourse.setCreater(1);
		tCourse.setCreateTimestamp(new Date());

		int ret = tCourseImpl.insert(tCourse);
		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void CourseDeleteByPrimaryKeyTest() {

		System.out.println("CourseDeleteByPrimaryKeyTest");

		int ret = tCourseImpl.deleteByPrimaryKey(1111111);

		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void CourseUpdateByPrimaryKeyTest() {

		System.out.println("CourseUpdateByPrimaryKeyTest");

		Course tCourse = new Course();
		tCourse.setId(1111111);
		tCourse.setName("xxxxx");
		tCourse.setTitle("test");
		tCourse.setDetail("xxxxx");
		tCourse.setCreater(1);
		tCourse.setCreateTimestamp(new Date());

		int ret = tCourseImpl.updateByPrimaryKey(tCourse);

		if (ret > 0) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void CourseSelectByPrimaryKeyTest() {
		CourseImpl tCourseImpl = new CourseImpl();
		Course tCourse = tCourseImpl.selectByPrimaryKey(1111111);

		System.out.println("CourseSelectByPrimaryKeyTest");
		if (tCourse != null) {
			System.out.println("item");
			System.out.println("  id " + tCourse.getId());
			System.out.println("  name " + tCourse.getName());
			System.out.println("  title " + tCourse.getTitle());
			System.out.println("  detail " + tCourse.getDetail());
			System.out.println("  creater " + tCourse.getCreater());
			System.out.println("  createTimestamp " + tCourse.getCreateTimestamp());
		} else {
			System.out.println("error");
		}
	}

	@After
	public void after() {
		System.out.println("after");
	}
}
