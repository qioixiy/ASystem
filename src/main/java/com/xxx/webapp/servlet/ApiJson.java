package com.xxx.webapp.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import com.xxx.utils.url.UrlUtils;
import com.xxx.webapp.asystem.pojo.Course;
import com.xxx.webapp.asystem.pojo.ScoreResult;
import com.xxx.webapp.asystem.pojo.Student;
import com.xxx.webapp.asystem.pojo.TestPaper;
import com.xxx.webapp.asystem.service.CourseImpl;
import com.xxx.webapp.asystem.service.ScoreResultImpl;
import com.xxx.webapp.asystem.service.StudentImpl;
import com.xxx.webapp.asystem.service.TestPaperImpl;

@WebServlet("/api/json.do")
public class ApiJson extends HttpServlet {
    private static final long serialVersionUID = 7500835936131982864L;

    /**
     * 返回json格式数据
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        dispatcher(request, response);
    }
    
    protected void dispatcher(HttpServletRequest request, HttpServletResponse response) {

    	Map<String, Object> data = new HashMap<String, Object>();
    	
    	String func = request.getParameter("func");
        data.put("func", func);
        
        switch(func) {
        case "course":
        	Course(request, response, data);
        	break;
        case "user":
        	User(request, response, data);
        	break;
        case "paper":
        	Paper(request, response, data);
        	break;
        case "score":
        	Score(request, response, data);
        	break;
        default:
        	break;
        }

        ResponseJsonUtils.json(response, data);
    }

    protected void Course(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	switch(request.getParameter("param1")) {
    	case "viewall":
    		CourseViewAll(request, response, data);
    		break;
    	case "create":
    		CourseCreate(request, response, data);
    		break;
    	case "delete":
    		CourseDelete(request, response, data);
    		break;
    	case "modify":
    		CourseModify(request, response, data);
    		break;
    	}
    }
    protected void CourseViewAll(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

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
    	
        ArrayList<Object> arrayList=new ArrayList<Object>();
        CourseImpl tCourseImpl = new CourseImpl();
		for(Course tCourse : tCourseImpl.selectAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", tCourse.getId());
			item.put("name", tCourse.getName());
			item.put("title", tCourse.getTitle());
			item.put("detail", tCourse.getDetail());
			item.put("creater", tCourse.getCreater());
			item.put("createTimestamp", tCourse.getCreateTimestamp());
			arrayList.add(item);
		}
		data.put("items", arrayList);
    }

    protected void CourseCreate(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

        Map<String, String> params = UrlUtils.toMap(param2);

		String result = "error";
		Course tCourse = new Course();
		try {
			String name = params.get("name");
			String title = params.get("title");
			String detail = params.get("detail");
			if (name != null && title != null && detail != null) {
				tCourse.setName(name);
				tCourse.setTitle(title);
				tCourse.setDetail(detail);
				tCourse.setCreater(Integer.parseInt(params.get("creater")));
				tCourse.setCreateTimestamp(new Date());
	
				CourseImpl tCourseImpl = new CourseImpl();
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
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {

			JSONArray objs = (JSONArray) JSON.parse(param2);

			CourseImpl tCourseImpl = new CourseImpl();
			for (Object i : objs.toArray()) {
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
			
			CourseImpl tCourseImpl = new CourseImpl();
			Course tCourse = tCourseImpl.selectByPrimaryKey(id);
			if (tCourse != null) {
				tCourse.setName(obj.getString("name"));
				tCourse.setTitle(obj.getString("title"));
				tCourse.setDetail(obj.getString("detail"));

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
    
    protected void User(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	switch(request.getParameter("param1")) {
    	case "viewall":
    		UserViewAll(request, response, data);
    		break;
    	case "create":
    		UserCreate(request, response, data);
    		break;
    	case "delete":
    		UserDelete(request, response, data);
    		break;
    	case "modify":
    		UserModify(request, response, data);
    		break;
    	}
    }
    protected void UserViewAll(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

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
        StudentImpl tStudentImpl = new StudentImpl();
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

    protected void UserCreate(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
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
	
				StudentImpl tStudentImpl = new StudentImpl();
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

    protected void UserDelete(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String param2 = request.getParameter("param2");
		try {
			param2 = URLDecoder.decode(param2, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String result = "error";
		try {

			JSONArray objs = (JSONArray) JSON.parse(param2);

			StudentImpl tStudentImpl = new StudentImpl();
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
    protected void UserModify(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
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
			
			StudentImpl tStudentImpl = new StudentImpl();
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
    	}
    }
    protected void PaperViewAll(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

    	ArrayList<Object> arrayTHead = new ArrayList<Object>();
    	ArrayList<Object> detailTHead = new ArrayList<Object>();
    	arrayTHead.add("id");
    	arrayTHead.add("name");
    	arrayTHead.add("desc");
    	arrayTHead.add("course_id");
		data.put("thead", arrayTHead);
		detailTHead.add("序号");
		detailTHead.add("试卷名");
		detailTHead.add("描述");
		detailTHead.add("课程ID");
		data.put("detailTHead", detailTHead);
	    
        ArrayList<Object> arrayList=new ArrayList<Object>();
        TestPaperImpl tTestPaperImpl = new TestPaperImpl();
		for(TestPaper tTestPaper : tTestPaperImpl.selectAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", tTestPaper.getId());
			item.put("name", tTestPaper.getName());
			item.put("desc", tTestPaper.getDesc());
			item.put("course_id", tTestPaper.getCourseId());
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
			String name = params.get("name");
			String desc = params.get("desc");
			String course_id = params.get("course_id");
			if (name != null && desc != null && course_id != null) {
				tTestPaper.setName(name);
				tTestPaper.setDesc(desc);
				tTestPaper.setCourseId(Integer.parseInt(course_id));
	
				TestPaperImpl tTestPaperImpl = new TestPaperImpl();
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

			StudentImpl tStudentImpl = new StudentImpl();
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
			
			TestPaperImpl tTestPaperImpl = new TestPaperImpl();
			TestPaper tTestPaper = tTestPaperImpl.selectByPrimaryKey(id);
			if (tTestPaper != null) {
				tTestPaper.setName(obj.getString("name"));
				tTestPaper.setDesc(obj.getString("desc"));
				tTestPaper.setCourseId(Integer.parseInt(obj.getString("course_id")));

				int ret = tTestPaperImpl.updateByPrimaryKey(tTestPaper);
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
		detailTHead.add("试卷ID");
		detailTHead.add("成绩描述");
		data.put("detailTHead", detailTHead);
	    
        ArrayList<Object> arrayList=new ArrayList<Object>();
        ScoreResultImpl tScoreResultImpl = new ScoreResultImpl();
		for(ScoreResult tScoreResult : tScoreResultImpl.selectAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", tScoreResult.getId());
			item.put("student_id", tScoreResult.getStudentId());
			item.put("teacher_id", tScoreResult.getTeacherId());
			item.put("paper_id", tScoreResult.getTestPaperId());
			item.put("desc", tScoreResult.getDesc());
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
			String desc = params.get("desc");
			if (student_id != null && teacher_id != null && paper_id != null && desc != null) {
				tScoreResult.setStudentId(Integer.parseInt(student_id));
				tScoreResult.setTeacherId(Integer.parseInt(teacher_id));
				tScoreResult.setTestPaperId(Integer.parseInt(paper_id));
				tScoreResult.setDesc(desc);
	
				ScoreResultImpl tScoreResultImpl = new ScoreResultImpl();
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

			ScoreResultImpl tScoreResultImpl = new ScoreResultImpl();
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
			
			ScoreResultImpl tScoreResultImpl = new ScoreResultImpl();
			ScoreResult tScoreResult = tScoreResultImpl.selectByPrimaryKey(id);
			if (tScoreResult != null) {
				tScoreResult.setStudentId(Integer.parseInt(obj.getString("student_id")));
				tScoreResult.setTeacherId(Integer.parseInt(obj.getString("teacher_id")));
				tScoreResult.setTestPaperId(Integer.parseInt(obj.getString("paper_id")));
				tScoreResult.setDesc(obj.getString("desc"));

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
