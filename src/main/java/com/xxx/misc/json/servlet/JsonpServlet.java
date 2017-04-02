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


/**
 * Servlet返回JSONP格式数据
 */
@WebServlet("/servlet/jsonp.do")
public class JsonpServlet extends HttpServlet {
    private static final long serialVersionUID = -8343408864035108293L;

    /**
     * 请求会发送callback参数作为回调函数，如果没有发送callback参数则使用默认回调函数
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //客户端发送的回调函数
        String callback = request.getParameter("callback");

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("date", new Date());
        data.put("email", "accountwcx@qq.com");
        data.put("age", 30);
        data.put("name", "csdn");
        data.put("array", new int[]{1,2,3,4});

        if(callback == null || callback.length() == 0){
            //如果客户端没有发送回调函数，则使用默认的回调函数
            ResponseJsonUtils.jsonp(response, data);
        }else{
            //使用客户端的回调函数
            ResponseJsonUtils.jsonp(response, callback, data);
        }
    }
}
