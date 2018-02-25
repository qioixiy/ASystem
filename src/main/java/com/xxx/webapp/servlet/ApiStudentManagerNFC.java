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

@WebServlet("/api/StudentManagerNFC.do")
public class ApiStudentManagerNFC extends HttpServlet {
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
        
        if (func != null) {
	        switch(func) {
	        case "login":
	        	Login(request, response, data);
	        	break;
	        case "manager":
	        	Manager(request, response, data);
	        	break;
	        case "student"://学生
	        	Student(request, response, data);
	        	break;
	        case "teacher"://老师
	        	Teacher(request, response, data);
	        	break;
	        default:
	        	break;
	        }
        }

        ResponseJsonUtils.json(response, data);
    }
    
    public boolean validateUserAndPassword(String name, String password) {
		boolean ret = true;
		
		// 先到管理员进行验证，再看老师，最后才是学生用户，
		String userType = "unkown";
		if (tManagerImpl.validate(name, password)) {
			userType = "manager";
		} else if (tTeacherImpl.validate(name, password)) {
			userType = "teacher";
		} else if (tStudentImpl.validate(name, password)) {
			userType = "student";
		} else {
			ret = false;
		}
		
		return ret;
	}
    
    private void Login(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {
    	String account = request.getParameter("account");
    	String pwd = request.getParameter("pwd");

    	boolean result = validateUserAndPassword(account, pwd);
    	if (result) {
    		
    	}

		data.put("result", result);
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
    	switch(request.getParameter("param1")) {
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
    
}
