package com.xxx.webapp.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

		boolean isFist = true;
		for (ScoreResult tScoreResult : tScoreResults) {
			// 一个学生的考试成绩
			String detail = tScoreResult.getDetail();
			JSONObject obj = (JSONObject) JSON.parse(detail);
			JSONArray data = (JSONArray)obj.get("data");
			
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
				if (isFist) {
					Integer v = levelMap.get(level_index);
					if (v == null) {
						v = 0;
					}
					levelMap.put(level_index, v+1);
				}
			}
			isFist = false;
			
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
		
		ret.score_avg = score_all/(float)cankao_renshu;
		int count = levelMap.get("3") + levelMap.get("2") + levelMap.get("1");
		ret.course_per_base = levelMap.get("1")/(float)count;
		ret.course_per_mid = levelMap.get("2")/(float)count;
		ret.course_per_high = levelMap.get("3")/(float)count;
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
		// 获取类型只做一次
		boolean tongji_tixing = true;
		
		// 题型分布统计:基础，中等，高级题型个数
		Map<String, Integer> tongji_tixing_Map = new HashMap<>();
		// 通过类型对每个学生的分数做分类统计，基础得多少分，中等得多少分，有难度的多少分
		class LevelScore {
			Integer score_total;
			Integer score_real;
		};
		ArrayList<Map<String, LevelScore>> score_level = new ArrayList<Map<String, LevelScore>>();
		
		for (ScoreResult tScoreResult : tScoreResults) {
			// 一个学生的考试成绩
			String detail = tScoreResult.getDetail();
			JSONObject obj = (JSONObject) JSON.parse(detail);
			JSONArray data = (JSONArray)obj.get("data");
			
			// 获取分数数据
			Map<String, LevelScore> level_Map = new HashMap<>();
			for (int i = 0; i < data.size(); i++) {
				JSONObject item = data.getJSONObject(i);
				//"type":"选择题1","level_str":"基础题目","level_index":"1","score_total":"5","score_real":"1","comments":""
				String type = item.getString("type");
				String level_str = item.getString("level_str");
				String level_index = item.getString("level_index");
				String score_total = item.getString("score_total");
				String score_real = item.getString("score_real");
				
				// 统计基础题型的得分情况
				// 1基础　2中等　3有难度
				if (tongji_tixing) {
					Integer v = tongji_tixing_Map.get(level_index);
					if (v == null) {
						v = 0;
					}
					v += 1;
					
					tongji_tixing_Map.put(level_index, v);
				}
				
				// 按题型统计所有分数
				{
					LevelScore score = level_Map.get(level_index);
					if (score == null) {
						score = new LevelScore();
						score.score_total = 0;
						score.score_real = 0;
					}
					score.score_total += Integer.parseInt(score_total);
					score.score_real += Integer.parseInt(score_real);
					
					level_Map.put(level_index, score);
				}
			}
			tongji_tixing = false;
			score_level.add(level_Map);
			
			System.out.println(obj);
		}
		System.out.println("tongji_tixing_Map");
		for ( Entry<String, Integer> entry : tongji_tixing_Map.entrySet() ) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		System.out.println("score_level");
		for (Map<String, LevelScore> item_score_level : score_level) {
			System.out.println("--------------------------");
			for ( Entry<String, LevelScore> entry : item_score_level.entrySet() ) {
				System.out.println("Key = " + entry.getKey() + ", Value = score_total:" + entry.getValue().score_total + ", score_real:" + entry.getValue().score_real);
			}
		}
		
		// 题型所占比重，是否有全答对的?是否有没有得分的?
		ret.Score_situation_base = "基本上那个全部学员能答对，1名答对50%";
		ret.Score_situation_low = "基本上那个全部学员能答对，1名答对25%";
		ret.Score_situation_high = "基本上那个全部学员能答对，1名没对分";
		ret.Score_situation_base = "";
		ret.Score_situation_low = "";
		ret.Score_situation_high = "";
		
		int Score_situation_base_score_0 = 0;// 得分为0
		int Score_situation_base_score_full = 0;// 满分
		int Score_situation_base_score_part_50 = 0;// 部分得分
		int Score_situation_low_score_0 = 0;// 得分为0
		int Score_situation_low_score_full = 0;// 满分
		int Score_situation_low_score_part_50 = 0;// 部分得分
		int Score_situation_high_score_0 = 0;// 得分为0
		int Score_situation_high_score_full = 0;// 满分
		int Score_situation_high_score_part_50 = 0;// 部分得分
		for (Map<String, LevelScore> item_score_level : score_level) {
			for ( Entry<String, LevelScore> entry : item_score_level.entrySet() ) {
				System.out.println("Key = " + entry.getKey() + ", Value = score_total:" + entry.getValue().score_total + ", score_real:" + entry.getValue().score_real);

				int total = entry.getValue().score_total;
				int real = entry.getValue().score_real;
				if (real == 0) {
					switch(entry.getKey()) {
					case "1": Score_situation_base_score_0++; break;
					case "2": Score_situation_low_score_0++; break;
					case "3": Score_situation_high_score_0++; break;
					}
					System.out.println("没有得分");
				}
				if (real == total) {
					switch(entry.getKey()) {
					case "1": Score_situation_base_score_full++; break;
					case "2": Score_situation_low_score_full++; break;
					case "3": Score_situation_high_score_full++; break;
					}
					System.out.println("满分");
				} else {
					if (real/(float)total >= 0.5) {
						switch(entry.getKey()) {
						case "1": Score_situation_base_score_part_50++; break;
						case "2": Score_situation_low_score_part_50++; break;
						case "3": Score_situation_high_score_part_50++; break;
						}
						System.out.println("部分得分");
					}
				}
			}
		}
		if (Score_situation_base_score_0 != 0) {
			ret.Score_situation_base += Score_situation_base_score_0 + "个没得分 ";
		}
		if (Score_situation_base_score_full != 0) {
			ret.Score_situation_base += Score_situation_base_score_full + "个满分 ";
		}
		if (Score_situation_base_score_part_50 != 0) {
			ret.Score_situation_base += Score_situation_base_score_part_50 + "个部分得分";
		}
		
		if (Score_situation_low_score_0 != 0) {
			ret.Score_situation_low += Score_situation_low_score_0 + "个没得分 ";
		}
		if (Score_situation_low_score_full != 0) {
			ret.Score_situation_low += Score_situation_low_score_full + "个满分 ";
		}
		if (Score_situation_low_score_part_50 != 0) {
			ret.Score_situation_low += Score_situation_low_score_part_50 + "个部分得分";
		}
		
		if (Score_situation_high_score_0 != 0) {
			ret.Score_situation_high += Score_situation_high_score_0 + "个没得分 ";
		}
		if (Score_situation_high_score_full != 0) {
			ret.Score_situation_high += Score_situation_high_score_full + "个满分 ";
		}
		if (Score_situation_high_score_part_50 != 0) {
			ret.Score_situation_high += Score_situation_high_score_part_50 + "个部分得分";
		}
		
		// 综合分析
		// 评价试题难度
		ret.comprehensive_analysis ="";
		if (ret.course_per_high < 0.30) {
			ret.comprehensive_analysis += "这套试题的难度不大，";
		} else {
			ret.comprehensive_analysis += "这套试题的难度偏大，";
		}
		if (ret.course_per_base + ret.course_per_mid > 0.7) {
			ret.comprehensive_analysis += "总体分布情况比较合理合理，";
		} else {
			ret.comprehensive_analysis += "总体分布情况比较不很合理，";
		}
		// 评价分数差距
		if (ret.score_highest - ret.score_lowest > 60) {
			ret.comprehensive_analysis += "最高分和最低分差距较大；";
		} else {
			ret.comprehensive_analysis += "最高分和最低分差距不很大；";
		}
		// 分数段分析
		if (ret.score_90_100_per > 20) {
			ret.comprehensive_analysis += "高分段所占比率较大，";
		}
		if (ret.score_l_60_per < 10) {
			ret.comprehensive_analysis += "及格率较高，";
		}
		if (ret.score_lowest < 40) {
			ret.comprehensive_analysis += "最低分较突出,只有" + ret.score_lowest + "分，";
		}
		
		ret.comprehensive_analysis += "面对成绩，面对不足，我们学会了思考。在课堂教学改革的路上，还有许多工作要做，许多困难要解决。我要更加认真学习——提升自我，不断总结——完善自我";
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
