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
		String fileName = "Result.doc";
		String template = "Result.doc.ftl";
		dataMap.put("v_kechongmingcheng", "hello");
		dataMap.put("v_jiaoxuequdui", "hello");
		dataMap.put("v_kaoshirenshu", "hello");
		dataMap.put("v_90_100_fen", "hello");
		dataMap.put("v_80_89_fen", "hello");
		dataMap.put("v_70_79_fen", "hello");
		dataMap.put("v_60_69_fen", "hello");
		dataMap.put("v_quekaorenshu", "hello");
		dataMap.put("v_90_100_baifenbi", "hello");
		dataMap.put("v_80_89_baifenbi", "hello");
		dataMap.put("v_70_79_baifenbi", "hello");
		dataMap.put("v_60_69_baifenbi", "hello");
		dataMap.put("v_60_yixia_fen", "hello");
		dataMap.put("v_zuigaofen", "hello");
		dataMap.put("v_zuidifen", "hello");
		dataMap.put("v_pingjunfen", "hello");
		dataMap.put("v_60_yixia_baifenbi", "hello");
		dataMap.put("v_shiti_jichu_baifenbi", "hello");
		dataMap.put("v_shiti_jichu_defenqingkuang", "hello");
		dataMap.put("v_shiti_zhongdeng_baifenbi", "hello");
		dataMap.put("v_shiti_zhongdeng_defenqingkuang", "hello");
		dataMap.put("v_yidingnandu_baifenbi", "hello");
		dataMap.put("v_yidingnandu_defenqingkuang", "hello");
		dataMap.put("v_shitijieguoyuchengjizhonghefenxi", "hello");
		dataMap.put("v_chengjifenbu_90_100", 100);
		dataMap.put("v_chengjifenbu_80_89", 200);
		dataMap.put("v_chengjifenbu_70_79", 300);
		dataMap.put("v_chengjifenbu_70_79_2", 300);
		dataMap.put("v_chengjifenbu_60_69", 400);
		dataMap.put("v_chengjifenbu_60_yixia", 500);

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
