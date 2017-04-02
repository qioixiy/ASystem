package com.xxx.misc.json.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxx.misc.json.ResponseJsonUtils;

@WebServlet("/servlet/json.do")
public class JsonServlet extends HttpServlet {
    private static final long serialVersionUID = 7500835936131982864L;

    /**
     * 返回json格式数据
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> data = new HashMap<String, Object>();

        data.put("date", new Date());
        data.put("email", "accountwcx@qq.com");
        data.put("age", 30);
        data.put("name", "csdn");
        data.put("array", new int[]{1,2,3,4});

        ResponseJsonUtils.json(response, data);
    }
}
