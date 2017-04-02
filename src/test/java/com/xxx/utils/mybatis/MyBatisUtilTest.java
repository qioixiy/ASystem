package com.xxx.utils.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xxx.webapp.asystem.pojo.Course;

public class MyBatisUtilTest {
	public static final String NAMESPACE = "com.xxx.webapp.asystem.mybatis.mapper.CourseMapper";
	
	@Before
	public void before() {
		System.out.println("before");
	}

	@Test
	public void CourseSelectByPrimaryKeyTest() {
		SqlSession session = MyBatisUtil.getSession();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", 1);
		List<Course> tCourse = session.selectList(NAMESPACE + ".selectByPrimaryKey", params);
		
		System.out.println("test");
		for(Course item : tCourse) {
			System.out.println("item");
			System.out.println("  id " + item.getId());
			System.out.println("  name " + item.getName());
			System.out.println("  title " + item.getTitle());
			System.out.println("  detail " + item.getDetail());
			System.out.println("  creater " + item.getCreater());
			System.out.println("  createTimestamp " + item.getCreateTimestamp());
		}

		session.commit();
		session.close();
	}

	@After
	public void after() {
		System.out.println("after");
	}
}
