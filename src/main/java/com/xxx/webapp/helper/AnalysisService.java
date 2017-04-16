package com.xxx.webapp.helper;

public class AnalysisService  {
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
	
	// 试题
	String course;
	int number_all;// 考试人数
	
	public AnalysisService(String course, int number_all) {
		this.course = course;
		this.number_all = number_all;
	}
	
	public Result getResult() {
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
}
