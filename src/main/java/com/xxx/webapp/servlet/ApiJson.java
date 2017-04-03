package com.xxx.webapp.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xxx.misc.json.ResponseJsonUtils;
import com.xxx.utils.url.UrlUtils;
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
    	
    	String func = request.getParameter("func");
        data.put("func", func);
        
        switch(func) {
        case "course":
        	Course(request, response, data);
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        Map<String, String> params = UrlUtils.toMap(param2);

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
}
