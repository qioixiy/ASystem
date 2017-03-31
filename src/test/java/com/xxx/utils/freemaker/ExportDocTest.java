package com.xxx.utils.freemaker;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ExportDocTest {
	ExportDoc mExportDoc;

	@Before
	public void before() {
		System.out.println("before");
		mExportDoc = new ExportDoc();
	}

	@Test
	public void createDocTest() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String fileName = "/tmp/Hello.html";
		String template = "Hello.ftl";
		dataMap.put("word", "hello");

		try {
			mExportDoc.createDoc(dataMap, fileName, template);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void exportResultTest() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String fileName = "/tmp/Result.doc";
		String template = "Result.doc.ftl";
		dataMap.put("word", "hello");

		try {
			mExportDoc.createDoc(dataMap, fileName, template);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void after() {
		System.out.println("after");
	}
}
