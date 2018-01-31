package com.xxx.webapp.servlet.NFCApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxx.misc.json.ResponseJsonUtils;
import com.xxx.webapp.asystem.pojo.Student;
import com.xxx.webapp.asystem.service.ManagerImpl;
import com.xxx.webapp.asystem.service.StudentImpl;
import com.xxx.webapp.asystem.service.TeacherImpl;
import com.xxx.webapp.struts2.action.ValidateService;

@WebServlet("/api/nfc.do")
public class NFCApi extends HttpServlet {
    private static final long serialVersionUID = 7500835936131982865L;
    
    private static final StudentImpl tStudentImpl = new StudentImpl();
	private static final ManagerImpl tManagerImpl = new ManagerImpl();
	private static final TeacherImpl tTeacherImpl = new TeacherImpl();
    
    public static final Map<String, Object> implMap = new HashMap<String, Object>() {
    	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		{
            put("StudentImpl", tStudentImpl);
            put("ManagerImpl", tManagerImpl);
            put("TeacherImpl", tTeacherImpl);
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
    	
    	switch(func) {
        case "login":
        	Login(request, response, data);
        	break;
        case "manager":
        	Manager(request, response, data);
        	break;
        default:
        	break;
    	}
    	
        ResponseJsonUtils.json(response, data);
    }

	private void Login(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

    	String name = request.getParameter("name");
    	String password = request.getParameter("password");
    	
    	StringBuilder userType = new StringBuilder("");
    	if (validateUserAndPassword(name, password, userType)) {
        	data.put("result", "success");
        	data.put("type", userType);
    	} else {
        	data.put("result", "error");
    	}
	}
	
	private boolean validateUserAndPassword(String name, String password, StringBuilder userType) {
		boolean ret = true;
		
		// 先到管理员进行验证，再看老师，最后才是学生用户，
		if (tManagerImpl.validate(name, password)) {
			userType.append("manager");
		} else if (tTeacherImpl.validate(name, password)) {
			userType.append("teacher");
		} else if (tStudentImpl.validate(name, password)) {
			userType.append("student");
		} else {
			ret = false;
		}
		
		return ret;
	}
	
	private void Manager(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

    	String op = request.getParameter("op");
    	data.put("op", op);
    	
    	switch(op) {
    	case "getStudentList":
    		List<Student> items = tStudentImpl.selectAll();
        	data.put("items", items);
    		break;
    	}
	}
}
