package com.xxx.webapp.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.misc.json.ResponseJsonUtils;
import com.xxx.utils.freemaker.ExportDoc;
import com.xxx.utils.url.UrlUtils;
import com.xxx.webapp.asystem.pojo.Course;
import com.xxx.webapp.asystem.pojo.ScoreResult;
import com.xxx.webapp.asystem.pojo.Student;
import com.xxx.webapp.asystem.pojo.Teacher;
import com.xxx.webapp.asystem.pojo.TestPaper;
import com.xxx.webapp.asystem.service.CourseImpl;
import com.xxx.webapp.asystem.service.ManagerImpl;
import com.xxx.webapp.asystem.service.ScoreResultImpl;
import com.xxx.webapp.asystem.service.StudentImpl;
import com.xxx.webapp.asystem.service.TeacherImpl;
import com.xxx.webapp.asystem.service.TestPaperImpl;
import com.xxx.webapp.helper.AnalysisService;
import com.xxx.webapp.asystem.pojo.Manager;

@WebServlet("/api/json.do")
public class ApiJson extends HttpServlet {
    private static final long serialVersionUID = 7500835936131982864L;
    
    private static final CourseImpl tCourseImpl = new CourseImpl();
    private static final StudentImpl tStudentImpl = new StudentImpl();
    private static final TeacherImpl tTeacherImpl = new TeacherImpl();
    private static final ManagerImpl tManagerImpl = new ManagerImpl();
    private static final TestPaperImpl tTestPaperImpl = new TestPaperImpl();
    private static final ScoreResultImpl tScoreResultImpl = new ScoreResultImpl();
    
    public static final Map<String, Object> implMap = new HashMap<String, Object>() {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		{
            put("CourseImpl", tCourseImpl);
            put("StudentImpl", tStudentImpl);
            put("TeacherImpl", tTeacherImpl);
            put("TestPaperImpl", tTestPaperImpl);
            put("ScoreResultImpl", tScoreResultImpl);
        }
    };

    /**
     * 返回json格式数据
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 请求处理入口
        request.setCharacterEncoding("UTF-8");
        dispatcher(request, response);
    }
    
    protected void dispatcher(HttpServletRequest request, HttpServletResponse response) {
    	// 分发实现
    	Map<String, Object> data = new HashMap<String, Object>();
    	
    	String func = request.getParameter("func");
        data.put("func", func);
        
        if (func == null) {
        	func = "student";
        	System.out.println("set func default as student");
        }
        
        switch(func) {
        case "course"://课程相关
        	Course(request, response, data);
        	break;
        case "student"://学生
        	Student(request, response, data);
        	break;
        case "teacher"://老师
        	Teacher(request, response, data);
        	break;
        case "paper"://试卷数据
        	Paper(request, response, data);
        	break;
        case "score"://成绩数据
        	Score(request, response, data);
        	break;
        case "analysis"://分析请求
        	Analysis(request, response, data);
        	break;
        case "manager":
        	Manager(request, response, data);
        default:
        	break;
        }

        ResponseJsonUtils.json(response, data);
    }

    private void Manager(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param1 = request.getParameter("param1");
    	String param2 = request.getParameter("param2");
		JSONObject obj = (JSONObject) JSON.parse(param2);

		String username = obj.get("username").toString();
		String oldpass = obj.get("oldpass").toString();
		String newpass = obj.get("newpass").toString();
		
		boolean result = false;
		boolean find = false;
		for (Manager manager : tManagerImpl.selectAll()) {
			if (manager.getName().equals(username)) {
				find = true;
				String password = manager.getPassword();
				if (password == null && oldpass.equals("password")) {
					result = true;
				} else if(password.equals(oldpass)) {
					result = true;
				}
				
				if (result) {
					manager.setPassword(newpass);
					tManagerImpl.updateByPrimaryKey(manager);
				}
			}
		}
		
		if (!find) {
			for (Teacher teacher : tTeacherImpl.selectAll()) {
				if (teacher.getName().equals(username)) {
					find = true;
					String password = teacher.getPassword();
					if (password == null && oldpass.equals("password")) {
						result = true;
					} else if(password.equals(oldpass)) {
						result = true;
					}
					
					if (result) {
						teacher.setPassword(newpass);
						tTeacherImpl.updateByPrimaryKey(teacher);
					}
				}
			}
		}
		if (!find) {
			for (Student student : tStudentImpl.selectAll()) {
				if (student.getName().equals(username)) {
					find = true;
					String password = student.getPassword();
					if (password == null && oldpass.equals("password")) {
						result = true;
					} else if(password.equals(oldpass)) {
						result = true;
					}
					
					if (result) {
						student.setPassword(newpass);
						tStudentImpl.updateByPrimaryKey(student);
					}
				}
			}
		}
		
		data.put("result", result);
		if (!find) {
			data.put("err_string", "用户没有查找到");
		} else {
			if (!result) {
				data.put("err_string", "密码验证没有通过");
			}
		}
	}

	protected void Analysis(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param1 = request.getParameter("param1");
    	switch(param1) {
    	case "method1":
    		AnalysisMethod1(request, response, data);
    		break;
    	case "method2":
    		AnalysisMethod2(request, response, data);
    		break;
    	}
    	data.put("param1", param1);
    }
    
    protected void AnalysisMethod1(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		JSONObject obj = (JSONObject) JSON.parse(param2);
    	// 参考应有人数
		int number_all = obj.getIntValue("number_all");
		int paper_id = obj.getIntValue("paper_id");
		String paper_name = obj.getString("paper_name");
		String input_class = obj.getString("input_class");
		
    	AnalysisService tAnalysis = new AnalysisService(implMap, paper_id, number_all, input_class);
    	AnalysisService.Result tResult =  tAnalysis.getResult();
    	data.put("result", tResult);
    }
    
    protected void AnalysisMethod2(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	
    	// 成绩分析 api入口
    	String param2 = request.getParameter("param2");
		JSONObject obj = (JSONObject) JSON.parse(param2);
    	// 参考应有人数
		int number_all = obj.getIntValue("number_all");
		int paper_id = obj.getIntValue("paper_id");
		String paper_name = obj.getString("paper_name");
		String input_class = obj.getString("input_class");
		
    	AnalysisService tAnalysis = new AnalysisService(implMap, paper_id, number_all, input_class);
        AnalysisService.Result tResult =  tAnalysis.getResult();
    	
    	Map<String, Object> dataMap = new HashMap<String, Object>();
    	String tempPath = this.getServletContext().getRealPath("/WEB-INF/result/");
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdir();
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        String date_str = sdf.format(new java.util.Date());
        
        String fileName = paper_name + "_" + date_str + "_分析结果" + "" + ".doc";
        String filePath = tempPath + "/" + fileName;
        String template = "Result.doc.ftl";
        
        dataMap.put("v_kechongmingcheng", tResult.course_name);
        dataMap.put("v_jiaoxuequdui", tResult.teaching_team);
        dataMap.put("v_kaoshirenshu", tResult.number_all);
        dataMap.put("v_90_100_fen", tResult.score_90_100);
        dataMap.put("v_80_89_fen", tResult.score_80_89);
        dataMap.put("v_70_79_fen", tResult.score_70_79);
        dataMap.put("v_60_69_fen", tResult.score_60_69);
        dataMap.put("v_quekaorenshu", tResult.number_abond);
        dataMap.put("v_90_100_baifenbi", tResult.score_90_100_per*100 + "%");
        dataMap.put("v_80_89_baifenbi", tResult.score_80_89_per*100 + "%");
        dataMap.put("v_70_79_baifenbi", tResult.score_70_79_per*100 + "%");
        dataMap.put("v_60_69_baifenbi", tResult.score_60_69_per*100 + "%");
        dataMap.put("v_60_yixia_fen", tResult.score_l_60);
        dataMap.put("v_zuigaofen", tResult.score_highest);
        dataMap.put("v_zuidifen", tResult.score_lowest);
        dataMap.put("v_pingjunfen", tResult.score_avg);
        dataMap.put("v_60_yixia_baifenbi", tResult.score_l_60_per*100 + "%");
        dataMap.put("v_shiti_jichu_baifenbi", tResult.course_per_base*100 + "%");
        dataMap.put("v_shiti_jichu_defenqingkuang", tResult.Score_situation_base);
        dataMap.put("v_shiti_zhongdeng_baifenbi", tResult.course_per_mid*100 + "%");
        dataMap.put("v_shiti_zhongdeng_defenqingkuang", tResult.Score_situation_low);
        dataMap.put("v_yidingnandu_baifenbi", tResult.course_per_high*100 + "%");
        dataMap.put("v_yidingnandu_defenqingkuang", tResult.Score_situation_high);
        dataMap.put("v_shitijieguoyuchengjizhonghefenxi", tResult.comprehensive_analysis);
        dataMap.put("v_chengjifenbu_90_100", tResult.score_90_100);
        dataMap.put("v_chengjifenbu_80_89", tResult.score_80_89);
        dataMap.put("v_chengjifenbu_70_79", tResult.score_70_79);
        dataMap.put("v_chengjifenbu_70_79_2", tResult.score_70_79);
        dataMap.put("v_chengjifenbu_60_69", tResult.score_60_69);
        dataMap.put("v_chengjifenbu_60_yixia", tResult.score_l_60);

        try {
            ExportDoc tExportDoc = new ExportDoc();
            tExportDoc.createDoc(dataMap, filePath, template);
            
            data.put("filename", fileName);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    protected void Course(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	switch(request.getParameter("param1")) {
    	case "viewall": // 查看
    		CourseViewAll(request, response, data);
    		break;
    	case "create": // 创建
    		CourseCreate(request, response, data);
    		break;
    	case "delete": // 删除
    		CourseDelete(request, response, data);
    		break;
    	case "modify": // 修改
    		CourseModify(request, response, data);
    		break;
    	case "get_list": // 获取数据
    		CourseGetList(request, response, data);
    	}
    }
    protected void CourseGetList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

        ArrayList<Object> arrayList=new ArrayList<Object>();
		for(Course tCourse : tCourseImpl.selectAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", tCourse.getId());
			item.put("name", tCourse.getName());
			item.put("title", tCourse.getTitle());
			item.put("detail", tCourse.getDetail());

			item.put("creater", "unkown");
			Teacher tTeacher = tTeacherImpl.selectByPrimaryKey(tCourse.getCreater());
			if (tTeacher != null) {
				item.put("creater", tTeacher.getName());
			};
			item.put("createTimestamp", tCourse.getCreateTimestamp());
			arrayList.add(item);
		}
		data.put("items", arrayList);
    }
    protected void CourseViewAll(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

    	// 组装表头
    	ArrayList<Object> arrayTHead = new ArrayList<Object>();
    	ArrayList<Object> detailTHead = new ArrayList<Object>();
    	arrayTHead.add("id");
    	arrayTHead.add("name");
    	arrayTHead.add("title");
    	arrayTHead.add("detail");
    	arrayTHead.add("creater");
    	arrayTHead.add("createTimestamp");
		data.put("thead", arrayTHead);
		detailTHead.add("序号");
		detailTHead.add("名字");
		detailTHead.add("主题");
		detailTHead.add("详细说明");
		detailTHead.add("创建人");
		detailTHead.add("创建时间");
		data.put("detailTHead", detailTHead);
    	
		// 查询数据库得到数据，
        ArrayList<Object> arrayList=new ArrayList<Object>();
		for(Course tCourse : tCourseImpl.selectAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", tCourse.getId());
			item.put("name", tCourse.getName());
			item.put("title", tCourse.getTitle());
			item.put("detail", tCourse.getDetail());

			item.put("creater", "unkown");
			Teacher tTeacher = tTeacherImpl.selectByPrimaryKey(tCourse.getCreater());
			if (tTeacher != null) {
				item.put("creater", tTeacher.getName());
			};
			item.put("createTimestamp", tCourse.getCreateTimestamp());
			arrayList.add(item);
		}
		data.put("items", arrayList);
    }

    protected void CourseCreate(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	// 创建课程
    	
    	// 获取请求参数
    	String param2 = request.getParameter("param2");
		try {
			// 转换字符编码为UTF-8
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

        Map<String, String> params = UrlUtils.toMap(param2);

		String result = "error";
		Course tCourse = new Course();// 创建课程对象
		try {
			String name = params.get("name");
			String title = params.get("title");
			String detail = params.get("detail");
			if (name != null && title != null && detail != null) {
				// 填入对应的数据
				tCourse.setName(name);
				tCourse.setTitle(title);
				tCourse.setDetail(detail);
				tCourse.setCreater(Integer.parseInt(params.get("creater")));
				tCourse.setCreateTimestamp(new Date());
	
				// 插入数据库
				int ret = tCourseImpl.insert(tCourse);
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }

    protected void CourseDelete(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	// 删除课程实现
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {

			JSONArray objs = (JSONArray) JSON.parse(param2);

			for (Object i : objs.toArray()) {
				// 通过ID删除课程数据
				int ret = tCourseImpl.deleteByPrimaryKey(Integer.parseInt((String)i));
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
					break;
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }
    protected void CourseModify(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	// 修改课程数据
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {
			// 获取到请求上传的数据
			JSONObject obj = (JSONObject) JSON.parse(param2);
			int id = Integer.parseInt(obj.getString("id"));
			
			Course tCourse = tCourseImpl.selectByPrimaryKey(id);
			if (tCourse != null) {
				// 填入需要修改的数据
				tCourse.setName(obj.getString("name"));
				tCourse.setTitle(obj.getString("title"));
				tCourse.setDetail(obj.getString("detail"));

				// 更新数据到数据库
				int ret = tCourseImpl.updateByPrimaryKey(tCourse);
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }
    
    protected void Teacher(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	switch(request.getParameter("param1")) {
    	case "viewall":
    		TeacherViewAll(request, response, data);
    		break;
    	case "create":
    		TeacherCreate(request, response, data);
    		break;
    	case "delete":
    		TeacherDelete(request, response, data);
    		break;
    	case "modify":
    		TeacherModify(request, response, data);
    		break;
    	}
    }
    protected void TeacherViewAll(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

    	ArrayList<Object> arrayTHead = new ArrayList<Object>();
    	ArrayList<Object> detailTHead = new ArrayList<Object>();
    	arrayTHead.add("id");
    	arrayTHead.add("name");
    	arrayTHead.add("number");
    	arrayTHead.add("email");
    	arrayTHead.add("telphone");
		data.put("thead", arrayTHead);
		detailTHead.add("序号");
		detailTHead.add("名字");
		detailTHead.add("编号");
		detailTHead.add("邮箱");
		detailTHead.add("联系方式");
		data.put("detailTHead", detailTHead);
	    
        ArrayList<Object> arrayList=new ArrayList<Object>();
		for(Teacher tTeacher : tTeacherImpl.selectAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", tTeacher.getId());
			item.put("name", tTeacher.getName());
			item.put("number", tTeacher.getNumber());
			item.put("email", tTeacher.getEmail());
			item.put("telphone", tTeacher.getTelphone());
			arrayList.add(item);
		}
		data.put("items", arrayList);
    }

    protected void TeacherCreate(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

        Map<String, String> params = UrlUtils.toMap(param2);

		String result = "error";
		Teacher tTeacher = new Teacher();
		try {
			String name = params.get("name");
			String number = params.get("number");
			String email = params.get("email");
			String telphone = params.get("telphone");
			if (name != null && number != null && email != null && telphone != null) {
				tTeacher.setName(name);
				tTeacher.setNumber(number);
				tTeacher.setEmail(email);
				tTeacher.setTelphone(telphone);
	
				int ret = tTeacherImpl.insert(tTeacher);
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }

    protected void TeacherDelete(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {

			JSONArray objs = (JSONArray) JSON.parse(param2);

			for (Object i : objs.toArray()) {
				int ret = tTeacherImpl.deleteByPrimaryKey(Integer.parseInt((String)i));
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
					break;
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }
    protected void TeacherModify(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {

			JSONObject obj = (JSONObject) JSON.parse(param2);
			int id = Integer.parseInt(obj.getString("id"));
			
			Teacher tTeacher = tTeacherImpl.selectByPrimaryKey(id);
			if (tTeacher != null) {
				tTeacher.setName(obj.getString("name"));
				tTeacher.setNumber(obj.getString("number"));
				tTeacher.setEmail(obj.getString("email"));
				tTeacher.setTelphone(obj.getString("telphone"));

				int ret = tTeacherImpl.updateByPrimaryKey(tTeacher);
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }

    protected void Student(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param1 = request.getParameter("param1");
    	
        if (param1 == null) {
        	param1 = "viewall";
        	System.out.println("set param1 default as viewall");
        }
    	
    	switch(param1) {
    	case "viewall":
    		StudentViewAll(request, response, data);
    		break;
    	case "create":
    		StudentCreate(request, response, data);
    		break;
    	case "delete":
    		StudentDelete(request, response, data);
    		break;
    	case "modify":
    		StudentModify(request, response, data);
    		break;
    	}
    }
    protected void StudentViewAll(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

    	ArrayList<Object> arrayTHead = new ArrayList<Object>();
    	ArrayList<Object> detailTHead = new ArrayList<Object>();
    	arrayTHead.add("id");
    	arrayTHead.add("name");
    	arrayTHead.add("number");
    	arrayTHead.add("email");
    	arrayTHead.add("telphone");
		data.put("thead", arrayTHead);
		detailTHead.add("序号");
		detailTHead.add("名字");
		detailTHead.add("学号");
		detailTHead.add("邮箱");
		detailTHead.add("联系方式");
		data.put("detailTHead", detailTHead);
	    
        ArrayList<Object> arrayList=new ArrayList<Object>();
		for(Student tStudent : tStudentImpl.selectAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", tStudent.getId());
			item.put("name", tStudent.getName());
			item.put("number", tStudent.getNumber());
			item.put("email", tStudent.getEmail());
			item.put("telphone", tStudent.getTelphone());
			arrayList.add(item);
		}
		data.put("items", arrayList);
    }

    protected void StudentCreate(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

        Map<String, String> params = UrlUtils.toMap(param2);

		String result = "error";
		Student tStudent = new Student();
		try {
			String name = params.get("name");
			String number = params.get("number");
			String email = params.get("email");
			String telphone = params.get("telphone");
			if (name != null && number != null && email != null && telphone != null) {
				tStudent.setName(name);
				tStudent.setNumber(number);
				tStudent.setEmail(email);
				tStudent.setTelphone(telphone);
	
				int ret = tStudentImpl.insert(tStudent);
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }

    protected void StudentDelete(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {

			JSONArray objs = (JSONArray) JSON.parse(param2);

			for (Object i : objs.toArray()) {
				int ret = tStudentImpl.deleteByPrimaryKey(Integer.parseInt((String)i));
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
					break;
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }
    protected void StudentModify(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {

			JSONObject obj = (JSONObject) JSON.parse(param2);
			int id = Integer.parseInt(obj.getString("id"));
			
			Student tStudent = tStudentImpl.selectByPrimaryKey(id);
			if (tStudent != null) {
				tStudent.setName(obj.getString("name"));
				tStudent.setNumber(obj.getString("number"));
				tStudent.setEmail(obj.getString("email"));
				tStudent.setTelphone(obj.getString("telphone"));

				int ret = tStudentImpl.updateByPrimaryKey(tStudent);
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }
    
    protected void Paper(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	switch(request.getParameter("param1")) {
    	case "viewall":
    		PaperViewAll(request, response, data);
    		break;
    	case "create":
    		PaperCreate(request, response, data);
    		break;
    	case "delete":
    		PaperDelete(request, response, data);
    		break;
    	case "modify":
    		PaperModify(request, response, data);
    		break;
    	case "query_by_id":
    		PaperQueryById(request, response, data);
    	}
    }
    protected void PaperQueryById(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

    	String param2 = request.getParameter("param2");

    	JSONObject obj = (JSONObject) JSON.parse(param2);
        
    	TestPaper tTestPaper = tTestPaperImpl.selectByPrimaryKey(obj.getIntValue("id"));
		data.put("items", tTestPaper);
    }
    
    protected void PaperViewAll(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

    	ArrayList<Object> arrayTHead = new ArrayList<Object>();
    	ArrayList<Object> detailTHead = new ArrayList<Object>();
    	arrayTHead.add("id");
    	arrayTHead.add("name");
    	arrayTHead.add("course_id");
    	arrayTHead.add("course_str");
    	arrayTHead.add("desc");
		data.put("thead", arrayTHead);
		detailTHead.add("序号");
		detailTHead.add("试卷名");
		detailTHead.add("课程编号");
		detailTHead.add("课程名称");
		detailTHead.add("描述");
		data.put("detailTHead", detailTHead);
	    
        ArrayList<Object> arrayList=new ArrayList<Object>();
		for(TestPaper tTestPaper : tTestPaperImpl.selectAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", tTestPaper.getId());
			item.put("name", tTestPaper.getName());
			item.put("course_id", tTestPaper.getCourseId());
			item.put("course_str", "unkown");
			item.put("desc", tTestPaper.getDetail());
			Course tCourse = tCourseImpl.selectByPrimaryKey(tTestPaper.getCourseId());
			if (tCourse != null) {
				item.put("course_str", tCourse.getName());
			}
			arrayList.add(item);
		}
		data.put("items", arrayList);
    }

    protected void PaperCreate(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

        Map<String, String> params = UrlUtils.toMap(param2);

		String result = "error";
		TestPaper tTestPaper = new TestPaper();
		try {
			String name = params.get("paper-name");
			String detail = params.get("paper-detail");
			String course_id = params.get("paper-course-id");
			if (name != null && detail != null && course_id != null) {
				tTestPaper.setName(name);
				tTestPaper.setDetail(detail);
				tTestPaper.setCourseId(Integer.parseInt(course_id));
	
				int ret = tTestPaperImpl.insert(tTestPaper);
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
				}
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}

    	data.put("result", result);
    }

    protected void PaperDelete(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {

			JSONArray objs = (JSONArray) JSON.parse(param2);

			for (Object i : objs.toArray()) {
				int ret = tTestPaperImpl.deleteByPrimaryKey(Integer.parseInt((String)i));
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
					break;
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }
    protected void PaperModify(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {

			JSONObject obj = (JSONObject) JSON.parse(param2);
			int id = Integer.parseInt(obj.getString("id"));

			TestPaper tTestPaper = tTestPaperImpl.selectByPrimaryKey(id);
			if (tTestPaper != null) {
				
				tTestPaper.setName(obj.getString("name"));
				tTestPaper.setDetail(obj.getString("desc"));
				//tTestPaper.setCourseId(Integer.parseInt(obj.getString("course_id")));

				int ret = tTestPaperImpl.updateByPrimaryKey(tTestPaper);
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
				}
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}

    	data.put("result", result);
    }

    protected void Score(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	switch(request.getParameter("param1")) {
    	case "viewall":
    		ScoreViewAll(request, response, data);
    		break;
    	case "create":
    		ScoreCreate(request, response, data);
    		break;
    	case "delete":
    		ScoreDelete(request, response, data);
    		break;
    	case "modify":
    		ScoreModify(request, response, data);
    		break;
    	}
    }
    protected void ScoreViewAll(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

    	ArrayList<Object> arrayTHead = new ArrayList<Object>();
    	ArrayList<Object> detailTHead = new ArrayList<Object>();
    	arrayTHead.add("id");
    	arrayTHead.add("student_id");
    	arrayTHead.add("teacher_id");
    	arrayTHead.add("paper_id");
    	arrayTHead.add("desc");
		data.put("thead", arrayTHead);
		detailTHead.add("序号");
		detailTHead.add("学生");
		detailTHead.add("老师");
		detailTHead.add("试卷名");
		detailTHead.add("描述");
		data.put("detailTHead", detailTHead);
	    
        ArrayList<Object> arrayList=new ArrayList<Object>();
		for(ScoreResult tScoreResult : tScoreResultImpl.selectAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", tScoreResult.getId());
			item.put("student_id", tScoreResult.getStudentId());
			item.put("teacher_id", tScoreResult.getTeacherId());
			item.put("paper_id", tScoreResult.getTestPaperId());
			item.put("desc", tScoreResult.getDetail());
			
			Student tStudent = tStudentImpl.selectByPrimaryKey(tScoreResult.getStudentId());
			if(tStudent != null) {
				item.put("student_id", tStudent.getName());
			}
			Teacher tTeacher = tTeacherImpl.selectByPrimaryKey(tScoreResult.getTeacherId());
			if(tTeacher != null) {
				item.put("teacher_id", tTeacher.getName());
			}
			TestPaper tTestPaper = tTestPaperImpl.selectByPrimaryKey(tScoreResult.getTestPaperId());
			if(tTestPaper != null) {
				item.put("paper_id", tTestPaper.getName());
			}
			
			arrayList.add(item);
		}
		data.put("items", arrayList);
    }

    protected void ScoreCreate(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

        Map<String, String> params = UrlUtils.toMap(param2);

		String result = "error";
		ScoreResult tScoreResult = new ScoreResult();
		try {
			String student_id = params.get("student_id");
			String teacher_id = params.get("teacher_id");
			String paper_id = params.get("paper_id");
			String detail = params.get("detail");
			if (student_id != null && teacher_id != null && paper_id != null && detail != null) {
				tScoreResult.setStudentId(Integer.parseInt(student_id));
				tScoreResult.setTeacherId(Integer.parseInt(teacher_id));
				tScoreResult.setTestPaperId(Integer.parseInt(paper_id));
				tScoreResult.setDetail(detail);
	
				int ret = tScoreResultImpl.insert(tScoreResult);
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
				}
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}

    	data.put("result", result);
    }

    protected void ScoreDelete(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {

			JSONArray objs = (JSONArray) JSON.parse(param2);

			for (Object i : objs.toArray()) {
				int ret = tScoreResultImpl.deleteByPrimaryKey(Integer.parseInt((String)i));
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
					break;
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }
    protected void ScoreModify(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {

			JSONObject obj = (JSONObject) JSON.parse(param2);
			int id = Integer.parseInt(obj.getString("id"));
			
			ScoreResult tScoreResult = tScoreResultImpl.selectByPrimaryKey(id);
			if (tScoreResult != null) {
				//tScoreResult.setStudentId(Integer.parseInt(obj.getString("student_id")));
				//tScoreResult.setTeacherId(Integer.parseInt(obj.getString("teacher_id")));
				//tScoreResult.setTestPaperId(Integer.parseInt(obj.getString("paper_id")));
				tScoreResult.setDetail(obj.getString("desc"));

				int ret = tScoreResultImpl.updateByPrimaryKey(tScoreResult);
				if (ret > 0) {
					result = "ok";
				} else {
					result = "error";
				}
			}
		} catch(Exception e) {
			;
		}

    	data.put("result", result);
    }
}
