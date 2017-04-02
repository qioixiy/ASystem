package com.xxx.misc.json.Struts2;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.xxx.misc.json.ResponseJsonUtils;

/**
 * 在Struts2中返回JSON和JSONP
 */
public class JsonAction extends ActionSupport {
    private static final long serialVersionUID = 5391000845385666048L;

    /**
     * JSONP的回调函数
     */
    private String callback;

    /**
     * 返回JSON
     */
    public void json(){
        HttpServletResponse response = ServletActionContext.getResponse();

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("date", new Date());
        data.put("email", "accountwcx@qq.com");
        data.put("age", 30);
        data.put("name", "csdn");
        data.put("array", new int[]{1,2,3,4});

        ResponseJsonUtils.json(response, data);
    }

    /**
     * 返回JSONP
     */
    public void jsonp(){
        HttpServletResponse response = ServletActionContext.getResponse();

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

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}
