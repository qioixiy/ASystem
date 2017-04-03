package com.xxx.webapp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxx.misc.json.ResponseJsonUtils;
import com.xxx.webapp.asystem.pojo.Course;
import com.xxx.webapp.asystem.service.CourseImpl;

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
    	
    	String function = request.getParameter("function");
        data.put("function", function);
        
        switch(function) {
        case "course":
        	functionFunc(request, response, data);
        	break;
        default:
        	break;
        }
    }
    
    protected void functionFunc(HttpServletRequest request, HttpServletResponse response, Map<String, Object> data) {

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

        ResponseJsonUtils.json(response, data);
    }
}
