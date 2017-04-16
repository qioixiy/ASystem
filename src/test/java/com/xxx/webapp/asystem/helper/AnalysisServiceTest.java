package com.xxx.webapp.asystem.helper;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.xxx.utils.freemaker.ExportDoc;
import com.xxx.webapp.helper.AnalysisService;

public class AnalysisServiceTest {
    ExportDoc tExportDoc = new ExportDoc();

    @Before
    public void before() {
    }

    @Test
    public void AnalysisServiceTester() {
        AnalysisService tAnalysis = new AnalysisService("", 10);
        AnalysisService.Result tResult =  tAnalysis.getResultTest();

        Map<String, Object> dataMap = new HashMap<String, Object>();
        String fileName = "Result.doc";
        String template = "Result.doc.ftl";
        
        dataMap.put("v_kechongmingcheng", tResult.course_name);
        dataMap.put("v_jiaoxuequdui", tResult.teaching_team);
        dataMap.put("v_kaoshirenshu", tResult.number_all);
        dataMap.put("v_90_100_fen", tResult.score_90_100);
        dataMap.put("v_80_89_fen", tResult.score_80_89);
        dataMap.put("v_70_79_fen", tResult.score_70_79);
        dataMap.put("v_60_69_fen", tResult.score_60_69);
        dataMap.put("v_quekaorenshu", tResult.number_abond);
        dataMap.put("v_90_100_baifenbi", tResult.score_90_100_per);
        dataMap.put("v_80_89_baifenbi", tResult.score_80_89_per);
        dataMap.put("v_70_79_baifenbi", tResult.score_70_79_per);
        dataMap.put("v_60_69_baifenbi", tResult.score_60_69_per);
        dataMap.put("v_60_yixia_fen", tResult.score_l_60);
        dataMap.put("v_zuigaofen", tResult.score_highest);
        dataMap.put("v_zuidifen", tResult.score_lowest);
        dataMap.put("v_pingjunfen", tResult.score_avg);
        dataMap.put("v_60_yixia_baifenbi", tResult.score_l_60_per);
        dataMap.put("v_shiti_jichu_baifenbi", tResult.course_per_base);
        dataMap.put("v_shiti_jichu_defenqingkuang", tResult.Score_situation_base);
        dataMap.put("v_shiti_zhongdeng_baifenbi", tResult.course_per_mid);
        dataMap.put("v_shiti_zhongdeng_defenqingkuang", tResult.Score_situation_low);
        dataMap.put("v_yidingnandu_baifenbi", tResult.course_per_high);
        dataMap.put("v_yidingnandu_defenqingkuang", tResult.Score_situation_high);
        dataMap.put("v_shitijieguoyuchengjizhonghefenxi", tResult.comprehensive_analysis);
        dataMap.put("v_chengjifenbu_90_100", tResult.score_90_100);
        dataMap.put("v_chengjifenbu_80_89", tResult.score_80_89);
        dataMap.put("v_chengjifenbu_70_79", tResult.score_70_79);
        dataMap.put("v_chengjifenbu_70_79_2", tResult.score_70_79);
        dataMap.put("v_chengjifenbu_60_69", tResult.score_60_69);
        dataMap.put("v_chengjifenbu_60_yixia", tResult.score_l_60);

        try {
            tExportDoc.createDoc(dataMap, fileName, template);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(tResult);
    }

    @After
    public void after() {
        System.out.println("after");
    }

}
