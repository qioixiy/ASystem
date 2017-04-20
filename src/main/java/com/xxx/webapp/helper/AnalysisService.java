package com.xxx.webapp.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.webapp.asystem.pojo.ScoreResult;
import com.xxx.webapp.asystem.pojo.TestPaper;
import com.xxx.webapp.asystem.service.ScoreResultImpl;
import com.xxx.webapp.asystem.service.TestPaperImpl;

public class AnalysisService  {
	
	private Map<String, Object> implMap;
	
	public class Result {
		// 基本信息
		public String course_name; // 课程名称
		public String teaching_team;// 教学区队
		public int number_all;// 考试人数
		public int number_abond;// 缺考人数
		public float score_90_100;// 90-100分
		public float score_90_100_per;// 90-100分
		public float score_80_89;// 80-89分
		public float score_80_89_per;// 80-89分
		public float score_70_79;// 70-79分
		public float score_70_79_per;// 70-79分
		public float score_60_69;// 60-69分
		public float score_60_69_per;// 60-69分
		public float score_l_60;// 60分以下
		public float score_l_60_per;// 60分以下
		public float score_lowest;// 最低分
		public float score_highest;// 最高分
		public float score_avg;// 平均分
		
		// 考试情况分析
		// 试题命题类型, 试题百分比
		public float course_per_base;// 基础知识、基本理论、基本技能试题 
		public float course_per_mid;// 中等难度题
		public float course_per_high;// 一定难度和深度难度试题
		// 学生得分情况
		public String Score_situation_base;// 基础知识、基本理论、基本技能试题 
		public String Score_situation_low;// 中等难度题
		public String Score_situation_high;// 一定难度和深度难度试题
		
		// 综合分析
		public String comprehensive_analysis;
	}
	
	int paper_id;// 试卷id
	int number_all;// 考试人数
	
	public AnalysisService(Map<String, Object> implMap, int paper_id, int number_all) {
		this.implMap = implMap;
		this.paper_id = paper_id;
		this.number_all = number_all;
	}
	
	private List<ScoreResult> getAllScore() {
		ScoreResultImpl tScoreResultImpl = (ScoreResultImpl)implMap.get("ScoreResultImpl");
		List<ScoreResult> all = tScoreResultImpl.selectAll();
		List<ScoreResult> ret = new ArrayList<ScoreResult>();
		
		for (ScoreResult tScoreResult : all) {
			if (tScoreResult.getTestPaperId() == paper_id) {
				ret.add(tScoreResult);
			}
		}
		
		return ret;
	}
	
	public Result getResultTest() {
		
		Result ret = new Result();
		
		ret.course_name = "计算机应用基础";
		ret.teaching_team = "2009级、2010级";
		ret.number_all = 534;
		ret.number_abond = 0;
		
		ret.score_90_100 = 95;
		ret.score_90_100_per = 17.80f;
		ret.score_80_89 = 256;
		ret.score_80_89_per = 47.90f;
		ret.score_70_79 = 136;
		ret.score_70_79_per = 25.50f;
		ret.score_60_69 = 39;
		ret.score_60_69_per = 7.30f;
		ret.score_l_60 = 8;
		ret.score_l_60_per = 1.50f;
		ret.score_lowest = 98;
		ret.score_highest = 58;
		ret.score_avg = 77.3f;
		ret.course_per_base = 62.50f; 
		ret.course_per_mid = 22.50f;
		ret.course_per_high = 15;
		
		ret.Score_situation_base = "基本上那个全部学员能答对，1名答对50%";
		ret.Score_situation_low = "基本上那个全部学员能答对，1名答对25%";
		ret.Score_situation_high = "基本上那个全部学员能答对，1名没对分";
		ret.comprehensive_analysis = "总体看该课程的主要知识点。试题结构较合理，强调基础知识、基本理论的掌握，学生考试情况基本反映了学生的学习情况和知识的掌握程度。";
		return ret;
	}

	private void analysisPass1(List<ScoreResult> tScoreResults, Result ret) {
		
		int person_num_score_90_100 = 0;
		int person_num_score_80_89 = 0;
		int person_num_score_70_79 = 0;
		int person_num_score_60_69 = 0;
		int person_num_score_l_60 = 0;
		int person_num_score_lowest = 100;
		int person_num_score_highest = 0;
		
		// 查到的成绩数量代表参考的人数
		int cankao_renshu = tScoreResults.size();
		// 记录所有的成绩总和，用于计算平均成绩
		int score_all = 0;

		// 保存难易程度的map
		Map<String, Integer> levelMap = new HashMap<>();
		
		for (ScoreResult tScoreResult : tScoreResults) {
			// 一个学生的考试成绩
			String detail = tScoreResult.getDetail();
			JSONObject obj = (JSONObject) JSON.parse(detail);
			JSONArray data = (JSONArray)obj.get("data");
			
			boolean isFist = true;
			int score = 0;
			// 获取分数
			for (int i = 0; i < data.size(); i++) {
				JSONObject item = data.getJSONObject(i);
				//"type":"选择题1","level_str":"基础题目","level_index":"1","score_total":"5","score_real":"1","comments":""
				String type = item.getString("type");
				String level_str = item.getString("level_str");
				String level_index = item.getString("level_index");
				String score_total = item.getString("score_total");
				String score_real = item.getString("score_real");
				String comments = item.getString("comments");
				score += Integer.parseInt(score_real);
				
				// 因为，每一个数据里面都包含了题目的难易程度，所以第一次就分析出来吧
				Integer v = levelMap.get(level_index);
				if (v == null) {
					v = 0;
				}
				levelMap.put(level_index, v+1);
			}
			
			if(score > 89.9) {
				person_num_score_90_100++;
			} else if (score > 89.9) {
				person_num_score_80_89++;
			} else if (score > 69.9) {
				person_num_score_70_79++;
			} else if (score > 59.9) {
				person_num_score_60_69++;
			} else {
				person_num_score_l_60++;
			}
			
			if (person_num_score_lowest > score) {
				person_num_score_lowest = score;
			}
			if (person_num_score_highest < score) {
				person_num_score_highest = score;
			}
			score_all += score;
			
			System.out.println(obj);
		}
		
		ret.course_per_base = 62.50f; 
		ret.course_per_mid = 22.50f;
		ret.course_per_high = 15;
		
		ret.score_avg = score_all/(float)cankao_renshu;
		ret.course_per_base = levelMap.get("1")/cankao_renshu;
		ret.course_per_mid = levelMap.get("2")/cankao_renshu;
		ret.course_per_high = levelMap.get("3")/cankao_renshu;
		ret.score_90_100 = person_num_score_90_100;
		ret.score_90_100_per = person_num_score_90_100/(float)cankao_renshu;
		ret.score_80_89 = person_num_score_80_89;
		ret.score_80_89_per = person_num_score_80_89/(float)cankao_renshu;
		ret.score_70_79 = person_num_score_70_79;
		ret.score_70_79_per = person_num_score_70_79/(float)cankao_renshu;
		ret.score_60_69 = person_num_score_60_69;
		ret.score_60_69_per = person_num_score_60_69/(float)cankao_renshu;
		ret.score_l_60 = person_num_score_l_60;
		ret.score_l_60_per = person_num_score_l_60/(float)cankao_renshu;

		ret.score_lowest = person_num_score_lowest;
		ret.score_highest = person_num_score_highest;
	}
	
	// 通过pass1拿到的数值信息，决策分析得到最终的文字描述
	private void analysisPass2(List<ScoreResult> tScoreResults, Result ret) {
		boolean tongji_tixing = true;
		Map<String, Integer> tongji_tixing_Map = new HashMap<>();
		for (ScoreResult tScoreResult : tScoreResults) {
			// 一个学生的考试成绩
			String detail = tScoreResult.getDetail();
			JSONObject obj = (JSONObject) JSON.parse(detail);
			JSONArray data = (JSONArray)obj.get("data");
			
			// 获取分数
			for (int i = 0; i < data.size(); i++) {
				JSONObject item = data.getJSONObject(i);
				//"type":"选择题1","level_str":"基础题目","level_index":"1","score_total":"5","score_real":"1","comments":""
				String type = item.getString("type");
				String level_str = item.getString("level_str");
				String level_index = item.getString("level_index");
				String score_total = item.getString("score_total");
				String score_real = item.getString("score_real");
				String comments = item.getString("comments");
				
				// 统计基础题型的得分情况
				// 123 基础　中等　有难度
				if (tongji_tixing) {
					Integer v = tongji_tixing_Map.get(level_index);
					if (v == null) {
						v = 0;
					}
					tongji_tixing_Map.put(level_index, v+1);
				}
			}
			tongji_tixing = false;

			System.out.println(obj);
		}
		ret.Score_situation_base = "基本上那个全部学员能答对，1名答对50%";
		ret.Score_situation_low = "基本上那个全部学员能答对，1名答对25%";
		ret.Score_situation_high = "基本上那个全部学员能答对，1名没对分";
		
		// 1. 分析题型百分比分布情况，大于50%认为占比过大，正常比率
		ret.comprehensive_analysis = "总体看该课程的主要知识点。试题结构较合理，强调基础知识、基本理论的掌握，学生考试情况基本反映了学生的学习情况和知识的掌握程度。";
	}

	public Result getResult() {
		TestPaperImpl tTestPaperImpl = (TestPaperImpl)implMap.get("TestPaperImpl");
		TestPaper tTestPaper = tTestPaperImpl.selectByPrimaryKey(paper_id);
		List<ScoreResult> tScoreResults = getAllScore();
		
		Result ret = new Result();
		
		ret.course_name = tTestPaper.getName();
		ret.teaching_team = "2009级、2010级";
		ret.number_all = number_all;
		ret.number_abond = number_all - tScoreResults.size();
		if (tScoreResults.size() > number_all) {
			ret.number_all = tScoreResults.size();
			ret.number_abond = 0;
		}

		analysisPass1(tScoreResults, ret);
		analysisPass2(tScoreResults, ret);
				
		return ret;
	}
}
